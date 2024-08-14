import json
import random

from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
import base64
import io
from PIL import Image
import cv2
import numpy as np
import tensorflow as tf
from pydub import AudioSegment

from interview.models import perform_face_detection, stt_models, save_video, get_encoded_image, get_image_url

rPath = 'interview/static'
model_path = rPath + '/models/vgg16_Face_model.h5'

@csrf_exempt
def face_detect(request):
    if request.method == 'POST':
        print('왔어요?')
        data = json.loads(request.body)
        image_data = data.get('image')

        if image_data:
            image_data = image_data.split(",")[1]
            image = Image.open(io.BytesIO(base64.b64decode(image_data)))
            image_np = np.array(image)

            # 얼굴 인식 처리
            face_detection_result = perform_face_detection(image_np)

            if face_detection_result :
                # 예시: 얼굴 인식 결과를 JSON으로 반환
                return JsonResponse({'result': 'Face detected!'})
            else:
                return JsonResponse({'result': 'Face detected fail'})

    return JsonResponse({'error': 'Invalid request'}, status=400)


@csrf_exempt
def speach_text(request):
    if request.method == 'POST':
        if 'audio' not in request.FILES:
            return JsonResponse({'error': 'No audio file provided'}, status=400)
        # speach to text 구현
        print('왔나요~')
        audio_file = request.FILES['audio']

        stt_result=stt_models(audio_file);
        print('오류발생')
        if stt_result=='':
            stt_result='오류발생';



        return JsonResponse({'result': stt_result })

    return JsonResponse({'error': 'Invalid request method'}, status=405)

@csrf_exempt
def question_detail(request):
    if request.method == 'POST':
        # 업로드된 비디오 파일을 가져옵니다.
        video_file = request.FILES.get('video')
        video_url=save_video(video_file)
        print(video_file)
        print(type(video_file))
    json_q_detail = {
        "answer": "질문에 대한 면접자의 답변입니다!!",
        "emotion": {
            'escore': 67,
        },
        "position": {
            "pbadcnt": 22,
            "pscore": 38,
        },
        "voice": {
            "vhertz": 17.42,
            "vamplit": 55.18,
            "vempty": 12.7,
            "vscore": 87,
            "vhertzimg": "base64로 인코딩(String타입)",
            "vamplitimg": "(주파수만으로 충분할것같으면 생략)",
        },
        "video_url": video_url,  # 비디오 파일의 URL을 추가합니다
        "aifeedback": "ai가 피드백 해준 문장입니다."
    }

    # for문을 사용해 emotion 안에 1sec, 2sec, ..., 59sec 데이터를 추가
    # 1초부터 89초까지의 감정을 랜덤하게 할당
    for i in range(1, 90):
        json_q_detail["emotion"][f"{i}sec"] = random.choice([-1, 0, 1])

    # 각각의 값(-1, 0, 1)을 count
    ecntbad = list(json_q_detail["emotion"].values()).count(-1)
    ecntsoso = list(json_q_detail["emotion"].values()).count(0)
    ecntgood = list(json_q_detail["emotion"].values()).count(1)

    # count 결과를 json_q_detail에 추가
    json_q_detail["emotion"]["ecntbad"] = ecntbad
    json_q_detail["emotion"]["ecntsoso"] = ecntsoso
    json_q_detail["emotion"]["ecntgood"] = ecntgood




    json_q_detail["voice"]["vhertzimg"] = get_image_url()

    # 결과 확인
    print(json_q_detail)

    return JsonResponse(json_q_detail)


@csrf_exempt
def interview_result(request):
    if request.method == 'POST':
        print('왔다')
        data = json.loads(request.body)
        print(data)
        # 데이터 처리 로직 추가
        response_data = {
            'sttresult1': '전체 질문에 대한 면접자 답변의 종합평가 문장입니다1',
            'sttresult2': '전체 질문에 대한 면접자 답변의 종합평가 문장입니다2'
        }
        return JsonResponse(response_data, status=200)

    return JsonResponse({'error': 'Invalid request method'}, status=405)