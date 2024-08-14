import cv2
import mediapipe as mp
from pydub import AudioSegment
from google.cloud import speech
import io
import os
from moviepy.editor import VideoFileClip # pip install moviepy
# Mediapipe 솔루션 초기화
mp_face_detection = mp.solutions.face_detection
mp_drawing = mp.solutions.drawing_utils
# stt json
SERVICE_ACCOUNT_JSON = 'interview/static/json/your_key_json.json'
os.environ['GOOGLE_APPLICATION_CREDENTIALS'] = SERVICE_ACCOUNT_JSON

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


def video_to_audio(video_file):
    # 영상 파일에서 오디오 트랙 추출
    video = VideoFileClip(video_file)
    audio = video.audio

    # 오디오를 메모리 내에서 처리
    audio_io = io.BytesIO()
    audio.write_audiofile(audio_io, codec='pcm_s16le', fps=16000)
    audio_io.seek(0)

    return audio_io

def stt_models(audio_file):
    print('1')
    # 오디오 파일을 메모리 내에서 처리
    audio = AudioSegment.from_file(audio_file)
    print('1.5')
    audio = audio.set_channels(1)
    print('2')

    # 변환된 오디오 파일을 메모리 내에서 처리
    mono_audio_io = io.BytesIO()
    audio.export(mono_audio_io, format="wav")
    mono_audio_io.seek(0)

    print('3')
    # Google Speech-to-Text API를 사용한 오디오 파일 변환
    client = speech.SpeechClient()

    print('4')
    # 모노로 변환된 오디오 파일을 메모리 내에서 로드
    content = mono_audio_io.read()
    print('5')
    # 샘플링 레이트 추출
    sample_rate = audio.frame_rate
    print('6')
    # 오디오 설정
    audio_config = speech.RecognitionAudio(content=content)
    print('7')
    config = speech.RecognitionConfig(
        encoding=speech.RecognitionConfig.AudioEncoding.LINEAR16,
        sample_rate_hertz=sample_rate,
        language_code='ko-KR',  # 한국어로 설정
    )
    print('8')
    # STT 요청
    response = client.recognize(config=config, audio=audio_config)
    transcripts = [result.alternatives[0].transcript for result in response.results]
    print('9')
    print('transcripts', transcripts)
    # 텍스트 변환 결과 출력
    return transcripts