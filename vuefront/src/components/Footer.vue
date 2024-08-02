<template>
    <div class="footer" :class="{ hidden: isHidden }">
    <p style="font-size: 15px; color:white; margin-bottom:0px;">
      &copy 치타 : 배 회장, 서 이사, 안 사장, 복 팀장, 최 부장, 추 차장, 방 과장 / 2024.07.25 (금일 완)</p>
    </div>
</template>

<script>
export default {
  data() {
    return {
      isHidden: false,
    };
  },
  methods: {
    handleScroll() {
      const scrollTop = window.scrollY;
      const windowHeight = window.innerHeight;
      const documentHeight = document.documentElement.scrollHeight;
      // 스크롤이 가장 아래일 때 푸터를 표시
      this.isHidden = !(scrollTop + windowHeight >= documentHeight);
    },
  },
  mounted() {
    window.addEventListener('scroll', this.handleScroll);
    this.handleScroll(); // 초기 로드 시 스크롤 상태 확인
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScroll);
  },
};
</script>

<style scoped>
.footer {
  position: fixed;
  bottom: 10px;
  left: 0;
  width: 100%;
  background-color: rgba(255, 255, 255, 0); /* 반투명 배경색 */
  color: #333;
  padding-top: 30px;;
}
.footer.hidden {
  opacity: 0; /* 푸터 숨기기 */
  pointer-events: none; /* 푸터가 숨겨졌을 때 클릭 이벤트 비활성화 */
}
</style>