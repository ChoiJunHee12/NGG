import numpy as np
import base64
import subprocess
from django.core.files.storage import FileSystemStorage
from django.conf import settings
import uuid
import cv2
import mediapipe as mp
from google.cloud import speech
import os

from config.settings import BASE_DIR

# Mediapipe 솔루션 초기화
mp_face_detection = mp.solutions.face_detection
mp_drawing = mp.solutions.drawing_utils

# stt json
SERVICE_ACCOUNT_JSON = 'interview/static/json/your_key_path.json'
os.environ['GOOGLE_APPLICATION_CREDENTIALS'] = SERVICE_ACCOUNT_JSON

# MediaPipe Pose 모델 초기화
mp_pose = mp.solutions.pose
pose = mp_pose.Pose(static_image_mode=True, min_detection_confidence=0.5)
mp_drawing = mp.solutions.drawing_utils






def stt_models(audio_file):
    print('1')
    # Google Cloud Speech-to-Text 클라이언트 생성
    client = speech.SpeechClient()
    print('2')

    # 음성 파일을 바이너리 데이터로 읽기
    content = audio_file.read()
    print('3')

    # 오디오 설정 (이 부분은 필요한 대로 변경 가능)
    audio = speech.RecognitionAudio(content=content)
    print('4')
    config = speech.RecognitionConfig(
        encoding=speech.RecognitionConfig.AudioEncoding.LINEAR16,
        sample_rate_hertz=48000,
        language_code="ko-KR",  # 한국어 설정 (필요한 언어로 변경 가능)
        enable_automatic_punctuation=True  # 자동 구두점 추가
    )
    print('5')

    # Google Cloud Speech-to-Text API 호출
    try:
        response = client.recognize(config=config, audio=audio)
    except Exception as e:
        print(f"API 요청 실패: {e}")
    print('6')
    # 응답에서 텍스트 추출
    transcript = ""
    for result in response.results:
        transcript += result.alternatives[0].transcript
        print('x')
    print('transcript',transcript)

    return transcript;



def perform_face_detection(image_np):
    # Mediapipe 얼굴 검출 솔루션 초기화
    with mp_face_detection.FaceDetection(min_detection_confidence=0.5) as face_detection:
        # Mediapipe는 RGB 이미지로 작동하므로, 이미지를 RGB로 변환
        rgb_image = cv2.cvtColor(image_np, cv2.COLOR_BGR2RGB)

        # Mediapipe를 사용하여 얼굴 검출
        results = face_detection.process(rgb_image)

        # 결과에서 얼굴 검출 여부 확인
        if results.detections:
            return True  # 얼굴이 검출된 경우
        else:
            return False  # 얼굴이 검출되지 않은 경우


def save_video(video_file):
    print(os.path.join(BASE_DIR, 'media'))
    fs = FileSystemStorage(location=os.path.join(settings.BASE_DIR, 'media/videos/webm'))

    # 기존 코드와 동일
    unique_file_name = f"{uuid.uuid4()}.{video_file.name.split('.')[-1]}"
    print(unique_file_name.split('.')[0])
    file_path = fs.save(unique_file_name, video_file)

    # 저장된 파일의 완전한 URL을 반환합니다
    mp4_url = f"http://192.168.0.221:9000/media/videos/mp4/{unique_file_name.split('.')[0]}.mp4"

    return mp4_url, unique_file_name.split('.')[0]

def image_to_base64(file_path):
    with open(file_path, "rb") as image_file:
        encoded_string = base64.b64encode(image_file.read()).decode('utf-8')
    return encoded_string

def get_encoded_image():
    fs = FileSystemStorage(location=os.path.join(settings.BASE_DIR, 'interview/static/img'))
    file_path = fs.path('voice1.png')
    encoded_image = image_to_base64(file_path)
    return encoded_image

def get_image_url():


    file_url = "http://192.168.0.221:9000/media/img/voice1.png"

    return file_url


def convert_webm_to_mp4(input_path, output_path):
    """
    .webm 파일을 .mp4로 변환합니다.

    :param input_path: 입력 파일 경로
    :param output_path: 출력 파일 경로
    """

    command = ['ffmpeg', '-i', input_path, output_path]


    try:
        subprocess.run(command, check=True, capture_output=True, text=True)
    except subprocess.CalledProcessError as e:
        print(f"Error during conversion: {e}")
        raise



def extract_frames_from_video(video_path):
    # 비디오 파일 열기
    video_capture = cv2.VideoCapture(video_path)
    if not video_capture.isOpened():
        raise ValueError("Could not open video file.")

    # FPS와 비디오 프레임 수 확인
    fps = video_capture.get(cv2.CAP_PROP_FPS)
    print(f"FPS: {fps}")

    # 프레임 번호 초기화
    frame_number = 0
    frames = []

    while True:
        ret, frame = video_capture.read()
        if not ret:
            break

        # 프레임을 1초마다 처리
        if frame_number % int(fps) == 0:
            # 프레임을 NumPy 배열로 저장
            frames.append(frame)

        frame_number += 1

    # 비디오 캡처 객체에서 리소스 해제
    video_capture.release()

    return frames


def get_line_angle(p1, p2):
    """두 점을 연결하는 선의 기울기(각도)를 계산합니다."""
    delta_y = p2[1] - p1[1]
    delta_x = p2[0] - p1[0]
    angle = np.arctan2(delta_y, delta_x) * (180.0 / np.pi)
    return angle

def analyze_pose(frame):
    # MediaPipe Pose 모델에 이미지 프레임을 전달
    image_rgb = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
    results = pose.process(image_rgb)

    # 랜드마크를 그리기 위해 원본 이미지 복사
    annotated_frame = frame.copy()
    badcount = 0

    if results.pose_landmarks:
        # 랜드마크 좌표를 NumPy 배열로 변환
        landmarks = results.pose_landmarks.landmark
        landmark_coords = np.array([(landmark.x, landmark.y) for landmark in landmarks])

        # 어깨와 눈 랜드마크 인덱스
        LEFT_SHOULDER = 5
        RIGHT_SHOULDER = 2
        LEFT_EYE = 1
        RIGHT_EYE = 4

        if len(landmark_coords) > max(LEFT_SHOULDER, RIGHT_SHOULDER, LEFT_EYE, RIGHT_EYE):
            # 어깨와 눈 좌표 가져오기
            left_shoulder = (landmark_coords[LEFT_SHOULDER][0], landmark_coords[LEFT_SHOULDER][1])
            right_shoulder = (landmark_coords[RIGHT_SHOULDER][0], landmark_coords[RIGHT_SHOULDER][1])
            left_eye = (landmark_coords[LEFT_EYE][0], landmark_coords[LEFT_EYE][1])
            right_eye = (landmark_coords[RIGHT_EYE][0], landmark_coords[RIGHT_EYE][1])

            # 어깨와 눈을 연결하는 선의 각도 계산
            shoulder_angle = abs(get_line_angle(left_shoulder, right_shoulder))
            eye_angle = abs(get_line_angle(left_eye, right_eye))

            # 선이 평행하지 않은 경우
            if abs(shoulder_angle - eye_angle) < 170:  # 각도 차이 임계값 설정
                badcount += 1

            # 랜드마크를 시각화
            mp_drawing.draw_landmarks(annotated_frame, results.pose_landmarks, mp_pose.POSE_CONNECTIONS)

    return annotated_frame, badcount