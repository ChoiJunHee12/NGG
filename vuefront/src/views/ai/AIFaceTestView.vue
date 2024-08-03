<template>
  <div id="content" class="ai-face-AI-Setting">
    <!-- Progress bar -->
    <div class="ai-face-progress-bar">
      <div class="ai-face-progress" :style="{ width: progress + '%' }"></div>
    </div>

    <!-- Progress steps -->
    <div class="ai-face-progress-text">
      <span :class="{ active: currentStep === 1 }">01. 환경점검</span>
      <span :class="{ active: currentStep === 2 }">02. 사용자 등록</span>
      <span :class="{ active: currentStep === 3 }">03. 지원분야 선택</span>
      <span :class="{ active: currentStep === 4 }">04. 인성면접</span>
      <span :class="{ active: currentStep === 5 }">05. 직무면접</span>
    </div>

    <!-- Interview section -->
    <div class="ai-face-device-check">
      <h3 class="ai-face-AI-interview-title">얼굴 인식을 시작합니다</h3>
      <h5>응시자의 얼굴을 인식하는 단계입니다.</h5>
      <h5>
        <strong style="color: mediumblue">얼굴을 네모 박스 안에 위치</strong>시키고 아래
        <strong style="color: mediumblue">얼굴 인식 버튼을 누르고 </strong>조금
        기다려 주세요.
      </h5>

      <!-- Webcam section -->
      <div class="ai-face-webcam-container">
        <div
          class="ai-face-webcam-frame"
          :class="{
            'ai-face-recognition-active': isRecognizing,
            'ai-face-recognition-complete': isRecognitionComplete,
          }"
        >
          <div class="ai-face-image-container">
            <img
              src="img/webcambefore.png"
              class="ai-face-webcam-image"
              :class="{ 'ai-face-fade-out': isRecognizing }"
            />
            <img
              v-if="isRecognizing"
              src="img/webcamafter.png"
              class="ai-face-webcam-image ai-face-fade-in"
            />
          </div>
          <div class="ai-face-face-recognition-box"></div>
          <div class="ai-face-recognition-overlay"></div>

          <!-- Buttons -->
          <button
            class="ai-face-btn ai-face-btn-recognition"
            :class="{ 'btn-success': isRecognitionComplete }"
            @click="startFaceRecognition"
            :disabled="isRecognitionComplete"
          >
            {{ recognitionButtonText }}
          </button>
        </div>
      </div>
      <div class="ai-face-device-check-btn">
        <button class="ai-face-btn ai-face-btn-pre" @click="handleBack">< 이전</button>
        <transition name="fade" mode="out-in">
          <button
            v-if="isRecognitionComplete"
            key="next"
            class="ai-face-btn ai-face-btn-next"
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
      progress: 30,
      currentStep: 2,
      isRecognizing: false,
      isRecognitionComplete: false,
      recognitionButtonText: "얼굴 인식 시작",
    };
  },
  methods: {
    handleBack() {
      this.$router.push({ name: "AISetting" });
    },
    handleNext() {
      this.$router.push({ name: "AIMicTest" });
    },
    startFaceRecognition() {
      if (this.isRecognitionComplete) return; // 이미 완료된 경우 함수 실행 중지
      
      this.recognitionButtonText = "얼굴 인식 중...";
      this.isRecognizing = true;

      // 5초 후에 인식 완료
      setTimeout(() => {
        this.recognitionButtonText = "얼굴 인식 성공";
        this.isRecognitionComplete = true;
      }, 5000);
    },
  },
};
</script>

<style scoped>

</style>
