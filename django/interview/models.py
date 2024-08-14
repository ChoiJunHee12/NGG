import base64

from django.core.files.storage import FileSystemStorage
from django.conf import settings
import uuid
import cv2
import mediapipe as mp
from django.http import JsonResponse
from pydub import AudioSegment
from google.cloud import speech
import io
import os

from config.settings import BASE_DIR

# Mediapipe 솔루션 초기화
mp_face_detection = mp.solutions.face_detection
mp_drawing = mp.solutions.drawing_utils

# stt json
SERVICE_ACCOUNT_JSON = 'interview/static/json/myspeechtotext-431005-a07eb9df65a2.json'
os.environ['GOOGLE_APPLICATION_CREDENTIALS'] = SERVICE_ACCOUNT_JSON








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
    fs = FileSystemStorage(location=os.path.join(settings.BASE_DIR, 'media/videos'))

    # 기존 코드와 동일
    unique_file_name = f"{uuid.uuid4()}.{video_file.name.split('.')[-1]}"
    file_path = fs.save(unique_file_name, video_file)

    # 저장된 파일의 완전한 URL을 반환합니다
    file_url = f"http://192.168.0.221:9000/media/videos/{unique_file_name}"

    return file_url

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