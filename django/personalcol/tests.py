from django.test import TestCase

# Create your tests here.
import dlib
import numpy as np
from sklearn.metrics.pairwise import euclidean_distances
from personalcol import models
import cv2
import os

rPath = 'personalcol/static'  # 실제 경로로 바꿔주세요
landmark_predictor_Path ="D:/ICTEDU/Django/ngg/personalcol/static/models/shape_predictor_68_face_landmarks.dat"
# 파일이 존재하는지 확인
if not os.path.isfile(landmark_predictor_Path):
    raise FileNotFoundError(f"File not found: {landmark_predictor_Path}")
landmark_predictor = dlib.shape_predictor(landmark_predictor_Path)

imgPath='D:/ICTEDU/Django/ngg/personalcol/static/img/0b82f1de-38d9-4842-b673-17be67a5dade.jpg'
class PersonalColorAnalyzer:
    def __init__(self, face_detector, landmark_predictor):
        self.face_detector = face_detector
        self.landmark_predictor = landmark_predictor
        self.seasons = {
            "Spring": {
                "skin": [(251, 211, 168), (255, 202, 149), (253, 197, 161), (252, 204, 130)],
                "eye": [(179, 134, 48), (157, 92, 18)]
            },
            "Summer": {
                "skin": [(253, 231, 174), (255, 219, 192), (254, 217, 170), (254, 210, 122)],
                "eye": [(111, 86, 40), (145, 112, 28)]
            },
            "Autumn": {
                "skin": [(255, 221, 150), (247, 206, 152), (249, 201, 128), (212, 169, 101)],
                "eye": [(157, 114, 12), (134, 96, 3)]
            },
            "Winter": {
                "skin": [(255, 220, 147), (242, 206, 148), (247, 207, 121), (216, 173, 102)],
                "eye": [(157, 111, 10), (136, 101, 10)]
            }
        }
        self.skin_weight = 0.7
        self.eye_weight = 0.3

    def extract_colors(self, image_path):
        # 이미지 로드
        image = cv2.imread(image_path)
        image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)

        # 얼굴 감지
        faces = self.face_detector(image)
        if len(faces) == 0:
            raise ValueError("얼굴을 감지할 수 없습니다.")

        face = faces[0]
        landmarks = self.landmark_predictor(image, face)

        # 전체 얼굴 영역에서 피부색 추출
        face_mask = np.zeros(image.shape[:2], dtype=np.uint8)
        points = []
        for i in range(17):  # 얼굴 윤곽선
            points.append((landmarks.part(i).x, landmarks.part(i).y))
        for i in range(26, 16, -1):  # 얼굴 윤곽선 (반대 방향)
            points.append((landmarks.part(i).x, landmarks.part(i).y))

        points = np.array(points, dtype=np.int32)
        cv2.fillPoly(face_mask, [points], 255)

        # 눈, 눈썹, 입 영역 제외
        eyes_mouth_mask = np.zeros(image.shape[:2], dtype=np.uint8)
        for i in range(36, 48):  # 눈
            cv2.circle(eyes_mouth_mask, (landmarks.part(i).x, landmarks.part(i).y), 5, 255, -1)
        for i in range(48, 68):  # 입
            cv2.circle(eyes_mouth_mask, (landmarks.part(i).x, landmarks.part(i).y), 5, 255, -1)
        for i in range(17, 27):  # 눈썹
            cv2.circle(eyes_mouth_mask, (landmarks.part(i).x, landmarks.part(i).y), 5, 255, -1)

        face_mask = cv2.subtract(face_mask, eyes_mouth_mask)
        skin_color = cv2.mean(image, mask=face_mask)[:3]

        # 눈동자색 추출
        left_eye = np.mean(image[landmarks.part(37).y:landmarks.part(41).y,
                                 landmarks.part(36).x:landmarks.part(39).x], axis=(0, 1))
        right_eye = np.mean(image[landmarks.part(43).y:landmarks.part(47).y,
                                  landmarks.part(42).x:landmarks.part(45).x], axis=(0, 1))
        eye_color = np.mean([left_eye, right_eye], axis=0)

        return skin_color, eye_color, image, face, face_mask

    def calculate_season(self, skin_color, eye_color):
        distances = {}
        for season, colors in self.seasons.items():
            skin_distances = [euclidean_distances([skin_color], [s])[0][0] for s in colors["skin"]]
            eye_distances = [euclidean_distances([eye_color], [e])[0][0] for e in colors["eye"]]
            distances[season] = self.skin_weight * min(skin_distances) + self.eye_weight * min(eye_distances)
        return min(distances, key=distances.get)

    def calculate_season_probabilities(self, skin_color, eye_color):
        distances = {}
        for season, colors in self.seasons.items():
            skin_distances = [euclidean_distances([skin_color], [s])[0][0] for s in colors["skin"]]
            eye_distances = [euclidean_distances([eye_color], [e])[0][0] for e in colors["eye"]]
            distances[season] = self.skin_weight * min(skin_distances) + self.eye_weight * min(eye_distances)
        total = sum(1/d for d in distances.values())
        probabilities = {season: (1/d)/total * 100 for season, d in distances.items()}
        return probabilities

    def analyze(self, image_path):
        skin_color, eye_color, image, face, face_mask = self.extract_colors(image_path)
        season = self.calculate_season(skin_color, eye_color)
        probabilities = self.calculate_season_probabilities(skin_color, eye_color)
        return season, probabilities, skin_color, eye_color, image, face, face_mask


face_detector = dlib.get_frontal_face_detector()
analyzer = PersonalColorAnalyzer(face_detector, landmark_predictor)
season, probabilities, skin_color, eye_color, image, face, face_mask = analyzer.analyze(imgPath)
print(f"계산된 시즌: {season}")
print("시즌별 확률:")
for season, prob in probabilities.items():
    print(f"{season}: {prob:.1f}%")