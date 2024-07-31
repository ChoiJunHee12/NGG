<template>
  <div id="content" class="AI-Setting">
    <!-- Progress bar -->
    <div class="progress-bar">
      <div class="progress" :style="{ width: progress + '%' }"></div>
    </div>
    
    <!-- Progress steps -->
    <div class="progress-text">
      <span :class="{ active: currentStep === 1 }">01. 환경점검</span>
      <span :class="{ active: currentStep === 2 }">02. 사용자 등록</span>
      <span :class="{ active: currentStep === 3 }">03. 지원분야 선택</span>
      <span :class="{ active: currentStep === 4 }">04. 인성면접</span>
      <span :class="{ active: currentStep === 5 }">05. 직무면접</span>
    </div>

    <!-- Interview section -->
    <div class="device-check">
      <h3 class="AI-interview-title">음성 인식을 시작합니다</h3>
      <h5>응시자의 음성을 인식하는 단계입니다.</h5>
      <h5>
        <strong style="color: mediumblue">아래 녹음하기 버튼</strong>을 누른 뒤,
        <strong style="color: mediumblue"
          >"만나서 반가워요. 저는 지원자 내가꿈입니다"</strong
        >라고 말씀해 주세요.
      </h5>

      <!-- Webcam section -->
      <div class="webcam-container">
        <div class="webcam-frame">
          <video ref="video" autoplay muted></video>
          <div class="recognition-status">
            {{ recognitionStatus }}
          </div>
          <div class="image-container" v-if="isRecording">
            <img src="img/mictest.png" class="mic-test-gif" />
          </div>

          <!-- Buttons -->
          <button
            class="btn btn-recognition"
            :class="{
              'btn-recording': isRecording,
              'btn-success': isRecognitionComplete,
              'btn-retry':
                !isRecording && !isRecognitionComplete && hasAttempted,
            }"
            @click="toggleRecording"
            :disabled="isRecognitionComplete"
          >
            {{ buttonText }}
          </button>
        </div>
      </div>
      <div class="device-check-btn">
        <button class="btn btn-pre" @click="handleBack">< 이전</button>
        <transition name="fade" mode="out-in">
          <button
            v-if="isRecognitionComplete"
            key="next"
            class="btn btn-next"
            @click="handleNext"
          >
            다음 >
          </button>
        </transition>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      progress: 40,
      currentStep: 2,
      isRecognizing: false,
      isRecognitionComplete: false,
      isRecording: false,
      recognitionStatus: "",
      buttonText: "녹음하기",
      hasAttempted: false,
    };
  },
  methods: {
    handleBack() {
      this.$router.push({ name: "AIFaceTest" });
    },
    handleNext() {
      this.$router.push({ name: "AIInterviewChoice" });
    },
    toggleRecording() {
      if (this.isRecognitionComplete) return; // 이미 완료된 경우 함수 실행 중지

      if (this.isRecording) {
        this.stopRecording();
      } else {
        this.startRecording();
      }
    },
    startRecording() {
      this.isRecording = true;
      this.buttonText = "녹음 중지";
      this.recognitionStatus = "음성 인식 중...";
      this.hasAttempted = true;
      // 여기에 실제 음성 인식 로직을 구현합니다.

      // 예시: 6초 후 인식 완료
      setTimeout(() => {
        this.isRecognitionComplete = true; // 인식 성공 시 상태 변경
        this.stopRecording();
      }, 6000);
    },
    stopRecording() {
      this.isRecording = false;
      if (this.isRecognitionComplete) {
        this.buttonText = "음성 인식 성공";
        this.recognitionStatus = "음성 인식 성공";
      } else {
        this.buttonText = "다시하기";
        this.recognitionStatus = "음성 인식 실패";
      }
    },
  },
};
</script>

<style scoped>
.progress-bar {
  width: 100%;
  height: 10px;
  background-color: #e0e0e0;
  border-radius: 5px;
  margin-bottom: 10px;
}

.progress {
  height: 100%;
  background-color: #07d0a9;
  border-radius: 5px;
  transition: width 0.5s ease-in-out;
}

.progress-text {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
  padding: 0 10px;
}

.progress-text span {
  flex: 1;
  text-align: center;
  font-size: 1.1rem;
  font-weight: bold;
  color: #6c757d;
  transition: color 0.3s ease;
}

.progress-text span.active {
  color: #ffffff;
}

.AI-Setting {
  max-width: 1280px;
  margin: 0 auto;
  padding: 20px;
  background-color: #0c1b49;
}

.AI-interview-title {
  color: #ffffff;
  background-color: #1a2b3c;
  padding: 10px;
  margin-bottom: 20px;
  border: none;
  border-radius: 5px;
}

.device-check {
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  color:black;
}

.webcam-container {
  width: 640px;
  margin: 0 auto;
}

.webcam-frame {
  position: relative;
  width: 100%;
  padding-top: 75%;
  overflow: hidden;
  background-color: #000;
}

.webcam-frame video {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.recognition-status {
  position: absolute;
  top: 10px;
  left: 10px;
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  padding: 5px 10px;
  border-radius: 5px;
}

.image-container {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.mic-test-gif {
  max-width: 100%;
  max-height: 100%;
}

.device-check-btn {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.btn {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-weight: bold;
  width: 200px;
  height: 50px;
  font-size: 1.1rem;
  transition: background-color 0.5s, color 0.5s;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
}

.btn-pre {
  background-color: #07d0a9;
  color: #ffffff;
}

.btn-pre:hover {
  background-color: #004e4a;
}

.btn-next {
  background-color: #1659de;
  color: #ffffff;
}

.btn-next:hover {
  background-color: #003d8c;
}

.btn-recognition {
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
  background-color: #1659de;
  color: #ffffff;
  transition: background-color 0.3s ease;
}

.btn-recognition:hover {
  background-color: #003d8c;
}

.btn-recording {
  background-color: #ff0000 !important;
  color: #ffffff !important;
}

.btn-recording:hover {
  background-color: #cc0000 !important;
}

.btn-success {
  background-color: #32cd32 !important;
  color: #ffffff !important;
}

.btn-success:hover {
  background-color: #2ab02a !important;
}

.btn-retry {
  background-color: #ffa500 !important;
  color: #ffffff !important;
}

.btn-retry:hover {
  background-color: #ff8c00 !important;
}

.btn-recognition:disabled {
  background-color: #32cd32;
  color: #ffffff;
  cursor: not-allowed;
  opacity: 0.9;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s, transform 0.5s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
  transform: translateY(10px);
}
</style>
