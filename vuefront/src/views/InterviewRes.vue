<template>
  <div class="container">
    <div class="interRestitle">
            <blockquote class="blockquote-interRes"><b>
                <p>면접결과 관리</p></b>
            </blockquote>
            </div>
    <hr>
    <div class="interviewRes-tab row">
      <div @click="pagechage(1)" class="col-1" :class="{'interviewRes-lbutton': true, 'interviewactive': activePage === 1}">인성면접결과</div>
      <div @click="pagechage(2)" class="col-1" :class="{'interviewRes-button': true, 'interviewactive': activePage === 2}">직무면접결과</div>
    </div>
    <div class="interviewRes-con">
      <div v-if="page === 1" class="interviewRes-counsel">
        <div class="interviewRes-imgbox">
          <img src="../../public/img/noimage.png" class="interviewRes-img">
        </div>
        <div class="interviewRes-text">
          <h5 class="interviewRes-h5">담당 컨설턴트가 없습니다</h5>
          <p>직무면접 컨설팅을 받기 위해서는 담당 컨설턴트를 선택하셔야 합니다</p>
          <router-link to="ConsultantInfo">
            <button class="btn btn-pre interviewRes-btn">담당 컨설턴트 선택하기</button>&nbsp;
          </router-link>
        </div>
      </div>
      <div v-if="page === 2" class="interviewRes-counsel">
        <div class="interviewRes-imgbox">
          <img src="../../public/img/noimage.png" class="interviewRes-img">
        </div>
        <div class="interviewRes-text">
          <h5 class="interviewRes-h5">담당 컨설턴트 : 분노맨</h5>
          <router-link to="OneToOne">
            <button class="btn btn-pre interviewRes-btntwo">1:1상담</button>
          </router-link>&nbsp;
          <button class="btn btn-del interviewRes-btntwo">삭제</button>
        </div>
        <div class="interviewRes-review">
          <p class="interviewRes-p"><i class="bi bi-star-half"></i>&nbsp;평점:</p>
          <h3 class="interviewRes-point">4.6</h3>
        </div>
      </div>
      <div :class="{'display-block': content === 1, 'display-none': content !== 1}" class="row scrollable-div interviewRes-display">
        <div v-for="(e, index) in attitude" :key="index" class="interviewRes-displaytwo">
          <table v-show="e !== null" class="interviewRes-table">
            <tr>
              <td class="interviewRes-list" @click="showModal_personal">
                <p>{{e}}</p>
              </td>
            </tr>
          </table>
        </div>
        <div v-show="attitude.length === 0" class="interviewRes-empty">
          <div class="interviewRes-empty-content">
            <img src="../../public/img/InterviewRes_image/nopersonal.png" class="interviewRes-empty-image">
            <div class="interviewRes-empty-button">
              <button class="btn btn-next interviewRes-empty-btn">AI면접보러 가기</button>
            </div>
          </div>
        </div>
      </div>
      <div :class="{'display-block': content === 2, 'display-none': content !== 2}" class="row scrollable-div interviewRes-display">
        <div v-for="(e, index) in career" :key="index" class="interviewRes-displaytwo">
          <table v-show="e !== null" class="interviewRes-table">
            <tr>
              <td class="interviewRes-list" @click="showModal_duty">
                <p>{{e}}</p>
              </td>
            </tr>
          </table>
        </div>
        <div v-show="career.length === 0" class="interviewRes-empty">
          <div class="interviewRes-empty-content">
            <img src="../../public/img/InterviewRes_image/noduty.png" class="interviewRes-empty-image">
            <div class="interviewRes-empty-button">
              <button class="btn btn-next interviewRes-empty-btn">AI 모의면접 보러 가기</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <InterviewResPersenalModal v-if="popState_personal" @close="closeModal_personal"></InterviewResPersenalModal>
  <InterviewResDutyModal v-if="popState_duty" @close="closeModal_duty"></InterviewResDutyModal>
  <OneToOne v-if="popState_one" @close="closeModal_one"></OneToOne>
</template>

<script>
import InterviewResPersenalModal from "../components/InterviewResModal_persenal.vue";
import InterviewResDutyModal from "../components/InterviewResModal_duty.vue";

export default {
  components: {
    InterviewResPersenalModal,
    InterviewResDutyModal,
  },
  data() {
    return {
      link: '/InterviewRes',
      activePage: 1,
      page: 1,
      attitude: [
        '<인성면접> 침착맨님 면접결과 24.07.07',
        '<인성면접> 침착맨님 면접결과 24.07.08',
        '<인성면접> 침착맨님 면접결과 24.07.09',
        '<인성면접> 침착맨님 면접결과 24.07.10',
        '<인성면접> 침착맨님 면접결과 24.07.11',
        '<인성면접> 침착맨님 면접결과 24.07.12',
        '<인성면접> 침착맨님 면접결과 24.07.13',
        '<인성면접> 침착맨님 면접결과 24.07.13',
        '<인성면접> 침착맨님 면접결과 24.07.13',
        '<인성면접> 침착맨님 면접결과 24.07.13',
        '<인성면접> 침착맨님 면접결과 24.07.13',
        '<인성면접> 침착맨님 면접결과 24.07.13',
        '<인성면접> 침착맨님 면접결과 24.07.13',
        '<인성면접> 침착맨님 면접결과 24.07.13',
        '<인성면접> 침착맨님 면접결과 24.07.13',
        '<인성면접> 침착맨님 면접결과 24.07.13',
        '<인성면접> 침착맨님 면접결과 24.07.13',
      ],
      career: [
        '<직무면접> 침착맨님 면접결과 24.07.07',
        '<직무면접> 침착맨님 면접결과 24.07.08',
        '<직무면접> 침착맨님 면접결과 24.07.09',
        '<직무면접> 침착맨님 면접결과 24.07.10',
        '<직무면접> 침착맨님 면접결과 24.07.11',
        '<직무면접> 침착맨님 면접결과 24.07.12',
        '<직무면접> 침착맨님 면접결과 24.07.13',
      ],
      content: 1,
      popState_personal: false,
      popState_duty: false,
    };
  },
  methods: {
    pagechage(num) {
      this.activePage = num;
      this.page = num;
      this.content = num;
    },
    showModal_personal() {
      this.popState_personal = true;
    },
    closeModal_personal() {
      this.popState_personal = false;
    },
    showModal_duty() {
      this.popState_duty = true;
    },
    closeModal_duty() {
      this.popState_duty = false;
    },
  },
};
</script>
