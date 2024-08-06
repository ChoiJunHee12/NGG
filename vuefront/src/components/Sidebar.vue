<template>
  <transition name="slide">
    <div class="sidebar" v-if="isVisible">
      <div>
        <div>
          <img
            src="../../public/img/noimage.png"
            style="width: 50%; radius: 100"
          />
          <h3>침착맨 님</h3>
        </div>
      </div>
      <hr />
      <div>
        <router-link to="/Mypage" class="main-router"
          ><p class="bar-p">마이 페이지</p></router-link
        >
        <router-link to="/resumelist" class="main-router"
          ><p class="bar-p">이력서 관리</p></router-link
        >
        <router-link to="ConsultantInfo" class="main-router"
          ><p class="bar-p">컨설턴트 101</p></router-link
        >
        <router-link to="/AISetting" class="main-router"
          ><p class="bar-p">AI면접</p></router-link
        >
        <router-link to="/InterviewRes" class="main-router"
          ><p class="bar-p">면접결과</p></router-link
        >
        <router-link to="/OneToOne" class="main-router"
          ><p class="bar-p">컨설턴트 1:1 상담</p></router-link
        >
        <router-link to="/Calendar" class="main-router"
          ><p class="bar-p">일정관리</p></router-link
        >
        <hr />
        <router-link to="/personalCol_Test" class="main-router"
          ><p class="bar-p">퍼스널컬러</p></router-link
        >
        <i class="bi bi-power power-icon"></i>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  props: {
    isVisible: {
      type: Boolean,
      required: true,
    },
  },
};
</script>

<style scoped>
.sidebar {
  position: fixed;
  top: 0px; /* 헤더 아래에 위치 */
  left: 0;
  width: 300px;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  padding: 20px;
  padding-top: 60px;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.5);
}

.slide-enter-active,
.slide-leave-active {
  transition: transform 0.3s ease;
}

.slide-enter,
.slide-leave-to {
  transform: translateX(-100%);
}
.bar-p {
  font-size: 18px;
}
.power-icon {
  position: absolute;
  bottom: 20px; /* 하단에서 20px 떨어진 위치 */
  left: 130px; /* 오른쪽에서 20px 떨어진 위치 */
  font-size: 30px; /* 아이콘 크기 조정 */
  cursor: pointer; /* 클릭 가능한 표시 */
}
</style>
