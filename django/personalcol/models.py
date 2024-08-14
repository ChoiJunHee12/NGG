import cv2
import numpy as np
import tensorflow as tf
import mediapipe as mp


rPath = 'personalcol/static'  # 실제 경로로 바꿔주세요

mp_face_detection = mp.solutions.face_detection
mp_face_mesh = mp.solutions.face_mesh


def predict(img1, img2):
    tf.compat.v1.disable_eager_execution()

    with tf.compat.v1.Session() as sess:
        # 모델 경로 설정
        model_path = rPath + "/models"
        print(model_path)
        meta_file_path = f"{model_path}/model.meta"

        # 메타 그래프 파일을 import_meta_graph로 불러오기
        saver = tf.compat.v1.train.import_meta_graph(meta_file_path)

        # 최신 체크포인트 파일 경로 확인
        checkpoint = tf.compat.v1.train.latest_checkpoint(model_path)
        if checkpoint is None:
            raise ValueError(f"No checkpoint found in directory: {model_path}")

        saver.restore(sess, checkpoint)
        graph = tf.compat.v1.get_default_graph()

        # 그래프에서 텐서 가져오기
        X = graph.get_tensor_by_name('X:0')
        Y = graph.get_tensor_by_name('Y:0')
        Xs = graph.get_tensor_by_name('generator/xs:0')

        # 이미지 형식 확인과 변환 (BGR을 RGB로)
        img1 = cv2.cvtColor(img1, cv2.COLOR_BGR2RGB)
        img2 = cv2.cvtColor(img2, cv2.COLOR_BGR2RGB)

        # 얼굴 정렬 함수 호출 (align_faces 함수는 따로 정의되어 있는 것으로 가정)
        img1_faces = align_faces(img1)
        img2_faces = align_faces(img2)

        # 얼굴 검출 실패 시 예외 처리
        if not img1_faces or not img2_faces:
            raise ValueError("Face detection failed for one or both images.")

        # 첫 번째 얼굴 이미지 선택
        src_img = img1_faces[0]
        ref_img = img2_faces[0]

        # 이미지 크기 조정
        src_img = cv2.resize(src_img, (256, 256))
        ref_img = cv2.resize(ref_img, (256, 256))

        # 전처리 함수 호출
        X_img = preprocess(src_img)
        X_img = np.expand_dims(X_img, axis=0)

        Y_img = preprocess(ref_img)
        Y_img = np.expand_dims(Y_img, axis=0)

        # 모델 예측 수행
        output = sess.run(Xs, feed_dict={
            X: X_img,
            Y: Y_img
        })

        # 후처리 함수 호출하여 출력 이미지 반환
        output_img = postprocess(output[0])

        return output_img


def align_faces(img):  # function to align faces using MediaPipe
    with mp_face_detection.FaceDetection(model_selection=1, min_detection_confidence=0.5) as face_detection:
        with mp_face_mesh.FaceMesh(static_image_mode=True, max_num_faces=1, refine_landmarks=True,
                                   min_detection_confidence=0.5) as face_mesh:
            results = face_detection.process(img)
            if results.detections:
                for detection in results.detections:
                    bboxC = detection.location_data.relative_bounding_box
                    ih, iw, _ = img.shape
                    x, y, w, h = int(bboxC.xmin * iw), int(bboxC.ymin * ih), int(bboxC.width * iw), int(
                        bboxC.height * ih)

                    # Adjust the crop coordinates to stay within image boundaries
                    x1 = max(x - 50, 0)
                    y1 = max(y - 50, 0)
                    x2 = min(x + w + 50, iw)
                    y2 = min(y + h + 50, ih)

                    cropped_face = img[y1:y2, x1:x2]
                    face_roi_resized = cv2.resize(cropped_face, (256, 256))  # Resize to 256x256
                    # face_roi_resized_rgb = cv2.cvtColor(face_roi_resized, cv2.COLOR_BGR2RGB)  # Convert to RGB
                    return [face_roi_resized]
    return []




def preprocess(img):
    return img.astype(np.float32) / 127.5 - 1


def postprocess(img):
    return ((img + 1.) * 127.5).astype(np.uint8)





def detect_and_crop_face(image):
    mp_face_detection = mp.solutions.face_detection
    with mp_face_detection.FaceDetection(model_selection=1, min_detection_confidence=0.5) as face_detection:
        results = face_detection.process(cv2.cvtColor(image, cv2.COLOR_BGR2RGB))
        if not results.detections:
            return None
        for detection in results.detections:
            bboxC = detection.location_data.relative_bounding_box
            ih, iw, _ = image.shape
            x, y, w, h = (bboxC.xmin * iw, bboxC.ymin * ih, bboxC.width * iw, bboxC.height * ih)
            x, y, w, h = int(x), int(y), int(w), int(h)
            # Adjust the crop coordinates to stay within image boundaries
            x1 = max(x - 50, 0)
            y1 = max(y - 50, 0)
            x2 = min(x + w + 50, iw)
            y2 = min(y + h + 50, ih)
            cropped_face = image[y1:y2, x1:x2]
            return cropped_face
    return None


def imread_unicode(filepath):
    stream = open(filepath.encode("utf-8"), "rb")
    bytes = bytearray(stream.read())
    np_array = np.asarray(bytes, dtype=np.uint8)
    return cv2.imdecode(np_array, cv2.IMREAD_UNCHANGED)
