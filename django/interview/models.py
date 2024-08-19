import numpy as np
import base64
import subprocess
from django.core.files.storage import FileSystemStorage
from django.conf import settings
import uuid
import cv2
import mediapipe as mp
from django.http import JsonResponse
from pydub import AudioSegment
from google.cloud import speech
import os
import io

from config.settings import BASE_DIR

# Mediapipe 솔루션 초기화
mp_face_detection = mp.solutions.face_detection
mp_drawing = mp.solutions.drawing_utils

# stt json
SERVICE_ACCOUNT_JSON = 'interview/static/json/myspeechtotext-431005-a07eb9df65a2.json'
os.environ['GOOGLE_APPLICATION_CREDENTIALS'] = SERVICE_ACCOUNT_JSON

#ffpeg.exe 경로 설정 각 위치에 맞게 설정
AudioSegment.converter = 'D:/ICTEDU/...경로써주세요/static/ffmpeg/ffmpeg.exe'

# MediaPipe Pose 모델 초기화
mp_pose = mp.solutions.pose
pose = mp_pose.Pose(static_image_mode=True, min_detection_confidence=0.5)
mp_drawing = mp.solutions.drawing_utils






def stt_models(audio_file):
    print('stt입장')
    # 오디오 파일을 메모리 내에서 처리
    try:
        audio_file.seek(0)
        audio = AudioSegment.from_file(audio_file)
        print('오디오 변환 성공')
    except Exception as e:
        print(f"오디오 파일 로드 실패: {e}")
        return JsonResponse({'error': f"오디오 파일 로드 실패: {e}"}, status=400)
    # 16비트 샘플로 변환
    audio = audio.set_sample_width(2)  # 2 bytes per sample = 16 bit
    audio = audio.set_channels(1)
    print('채널1 완료')
    # 변환된 오디오 파일을 메모리 내에서 처리
    mono_audio_io = io.BytesIO()
    audio.export(mono_audio_io, format="wav")
    mono_audio_io.seek(0)
    print('1')
    # Google Cloud Speech-to-Text 클라이언트 생성
    client = speech.SpeechClient()
    print('2')
    sample_rate = audio.frame_rate
    print('sample_rate',sample_rate)
    # 음성 파일을 바이너리 데이터로 읽기
    content = mono_audio_io.read()
    print('3')

    # 오디오 설정 (이 부분은 필요한 대로 변경 가능)
    audio = speech.RecognitionAudio(content=content)
    print('4')
    config = speech.RecognitionConfig(
        encoding=speech.RecognitionConfig.AudioEncoding.LINEAR16,
        sample_rate_hertz=sample_rate,
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

    return transcript

def extract_audio_to_memory(video_file_path):
    # ffmpeg를 사용하여 오디오를 추출하고, 이를 stdout으로 전달
    command = [
        'ffmpeg',
        '-i', video_file_path,
        '-vn',  # 비디오를 제외하고 오디오만 추출
        '-f', 'wav',
        '-acodec', 'pcm_s16le',
        '-ar', '16000',  # 오디오 샘플링 레이트를 16kHz로 설정 (Google STT 요구 사항)
        '-ac', '1',  # 모노 오디오로 변환
        'pipe:1'  # stdout으로 출력
    ]

    # subprocess를 통해 명령 실행
    process = subprocess.Popen(command, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    audio_data, _ = process.communicate()

    # BytesIO로 오디오 데이터를 메모리에 로드
    return io.BytesIO(audio_data)

def stt_models_from_video_path(video_file_path):
    try:
        # 비디오 파일에서 오디오를 추출하여 메모리에 저장
        audio_io = extract_audio_to_memory(video_file_path)

        # stt_models 함수 사용
        transcript = stt_models(audio_io)
        return transcript

    except Exception as e:
        print(f"오디오 변환 실패: {e}")
        return JsonResponse({'error': f"오디오 변환 실패: {e}"}, status=400)

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

    # 출력 디렉토리가 없으면 생성
    output_dir = os.path.dirname(output_path)
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

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


def get_line_angle(point1, point2):
    # 두 점 사이의 선의 각도 계산
    x1, y1 = point1
    x2, y2 = point2
    return np.degrees(np.arctan2(y2 - y1, x2 - x1))


def analyze_pose(frame):
    # MediaPipe Pose 모델에 이미지 프레임을 전달
    image_rgb = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
    results = pose.process(image_rgb)


    badcount = 0

    if results.pose_landmarks:
        # 랜드마크 좌표를 NumPy 배열로 변환
        landmarks = results.pose_landmarks.landmark
        landmark_coords = np.array([(landmark.x, landmark.y) for landmark in landmarks])

        # 어깨와 눈 랜드마크 인덱스
        LEFT_SHOULDER = 11
        RIGHT_SHOULDER = 12
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


            # 기준 2: 어깨가 수평에서 너무 기울어져 있는 경우 (비대칭 어깨)
            if shoulder_angle < 173:  # 어깨 각도 임계값 설정
                print('수평에서 어깨가 너무 기울어져 있는 경우 => ', shoulder_angle)
                badcount += 1

            # 기준 3: 고개가 한쪽으로 기울어진 경우
            if abs(left_eye[1] - right_eye[1]) > 0.01:  # 눈의 높이 차이 임계값 설정
                print('고개가 한쪽으로 기울어진 경우 => ', abs(left_eye[1] - right_eye[1]))
                badcount += 1



    return badcount


