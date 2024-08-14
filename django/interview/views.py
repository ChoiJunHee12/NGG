import json
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
import base64
import io
from PIL import Image
import numpy as np
from interview.models import perform_face_detection
from interview.models import stt_models
from interview.models import video_to_audio

@csrf_exempt
def face_detect(request):
    if request.method == 'POST':
        data = json.loads(request.body)
        image_data = data.get('image')
        if image_data:
            image_data = image_data.split(",")[1]
            image = Image.open(io.BytesIO(base64.b64decode(image_data)))
            image_np = np.array(image)
            print(image_np)
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
    if request.method == 'POST' and 'audio' in request.FILES:
        # speach to text 구현
        movie_file = request.FILES['movie']

        print('도착')
        audio_io = video_to_audio(movie_file)
        stt_result=stt_models(audio_io)
        print(stt_result)

        return JsonResponse({'result': stt_result })

    return JsonResponse({'error': 'Invalid request'}, status=400)