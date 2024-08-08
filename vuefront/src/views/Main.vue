<template>
  <div class="mains-containers">
    <div class="mains-header-top">
      <p class="mains-header-title">ONLINE AI INTERVIEW REPORT</p>
    </div>
    <div class="mains-header-container">
      <div class="mains-headers-left">
        <div class="mains-headers-lefts">
          <div
            style="display: flex; justify-content: center; align-items: center"
          >
            <img
              :src="memberData.imgname"
              class="center-img"
              alt="Profile Image"
            />
          </div>
          <div>
            <p class="mains-headers-p">{{ memberData.name }}</p>
          </div>
          <div>
            <table class="mains-headers-table">
              <tr>
                <td class="mains-headers-td">지원분야</td>
                <td class="mains-headers-td-a">{{ memberData.categcd }}</td>
              </tr>
              <tr>
                <td class="mains-headers-td">핸 드 폰</td>
                <td class="mains-headers-td-a">{{ memberData.phonenum }}</td>
              </tr>
              <tr>
                <td class="mains-headers-td">이 메 일</td>
                <td class="mains-headers-td-a">{{ memberData.email }}</td>
              </tr>
              <tr>
                <td class="mains-headers-td">거주지역</td>
                <td class="mains-headers-td-a">{{ memberData.loccd }}</td>
              </tr>
            </table>
          </div>
        </div>
        <div class="mains-headers-middle">
          <div class="mains-headers-middle-top">
            <div class="analysis-head">분석 결과</div>
            <div style="display: flex">
              <div>
                <div class="analysis-left">
                  스트레스
                  <div class="progress-container">
                    <div class="progress-bar" :data-value="stressRate"></div>
                  </div>
                  <div class="analysis-rate">{{ stressRate }}%</div>
                </div>
                <div class="analysis-left">
                  음성분석
                  <div class="progress-container">
                    <div class="progress-bar" :data-value="voiceRate"></div>
                  </div>
                  <div class="analysis-rate">{{ voiceRate }}%</div>
                </div>
                <div class="analysis-left">
                  자세분석
                  <div class="progress-container">
                    <div
                      class="progress-bar"
                      :data-value="postureBadCountRate"
                    ></div>
                  </div>
                  <div class="analysis-rate">{{ postureBadCountRate }}%</div>
                </div>
                <div class="analysis-left">
                  컨설턴트 평가
                  <div class="progress-container" style="margin-left: 8.5px">
                    <div
                      class="progress-bar"
                      :data-value="interviewReport.cnsscore"
                    ></div>
                  </div>
                  <div class="analysis-rate">
                    {{ interviewReport.cnsscore }}점
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- 핵심 키워드 부분 -->
          <div class="mains-headers-middle-bottom">
            <div>
              <div class="mains-result-head">핵심 키워드</div>
              <div style="margin-left: 30px; margin-top: 10px">
                <div style="display: flex; margin-bottom: 25px">
                  <div style="display: flex">
                    <img src="img/res_stress.png" class="res-img" />
                    <p class="res-text">{{ keywordStressLevel }}</p>
                  </div>
                  <div style="display: flex; margin-left: 20px">
                    <img src="img/ress_audio.png" class="res-img" />
                    <p class="res-text">{{ keywordVoiceStability }}</p>
                  </div>
                </div>
                <div style="display: flex">
                  <div style="display: flex">
                    <img src="img/res_po.png" class="res-img" />
                    <p class="res-text">{{ keywordPostureBalance }}</p>
                  </div>
                  <div style="display: flex; margin-left: 20px">
                    <img src="img/res_resume.png" class="res-img" />
                    <p class="res-text">{{ keywordConsultantMsg }}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="mains-headers-right">
          <div class="mains-headers-right-top">
            <div class="mains-result-head">Dates to Remember</div>
            <div style="margin-left: 20px; margin-top: 10px; font-size: 1.1rem">
              <div
                v-for="schedule in upcomingSchedules"
                :key="schedule.schno"
                style="display: flex; margin-bottom: 15px; align-items: center"
              >
                <div
                  style="
                    width: 60px;
                    text-align: center;
                    font-weight: bold;
                    color: #007bff;
                  "
                >
                  D-{{ schedule.dday }}
                </div>
                <div>
                  <div style="font-weight: bold">
                    {{ schedule.title }}
                    <span
                      style="
                        font-size: 0.9em;
                        color: #6c757d;
                        margin-left: 10px;
                      "
                      >{{ schedule.content }}</span
                    >
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="mains-headers-right-bottom">
            <div class="main-con-head">My Consultant</div>
            <div
              style="
                display: flex;
                align-items: center;
                justify-content: space-evenly;
              "
            >
              <img
                :src="consultantDetail.imgname"
                class="center-img2"
                alt="Consultant Image"
              />
              <div class="main-con-name">
                {{ consultantDetail.name }} 컨설턴트
                <p style="font-size: 0.8em; color: #6c757d; margin-left: 10px">
                  <img src="/img/consul2.svg" class="cons-svg" />{{
                    consultantDetail.categcd
                  }}
                  &nbsp;전문
                </p>
                <div></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div>
      <div style="display: flex; gap: 20px" class="mains-tap">
        <div @click="activateSection('ai-analysis', $event)">AI종합분석</div>
        <div @click="activateSection('consulting', $event)">컨설팅</div>
      </div>
      <div class="mains-header"></div>
    </div>

    <!-- AI종합분석탭 -->
    <div
      id="ai-analysis"
      class="tab-content"
      v-if="activeSection === 'ai-analysis'"
    >
      <div class="mains-content">
        <div class="mains-floor-1">
          <div class="box2">
            <p class="box-text">감정 분석 결과</p>
            <hr class="box-line" />
            <div id="chart-1" style="height: 300px"></div>
          </div>
          <div class="box5">
            <p class="box-text">음성 분석 결과</p>
            <hr class="box-line" />
            <div id="chart-2" style="height: 300px"></div>
          </div>
        </div>
        <div class="mains-floor-2">
          <div class="box3">
            <p class="box-text">자세 분석 결과</p>
            <hr class="box-line" />
            <div id="chart-3" style="height: 300px"></div>
          </div>
          <div class="box6">
            <p class="box-text">컨설턴트 평가 결과</p>
            <hr class="box-line" />
            <div id="chart-4" style="height: 300px"></div>
          </div>
        </div>
      </div>
    </div>

    <!-- 컨설팅 탭 -->
    <div
      id="consulting"
      class="tab-content"
      v-if="activeSection === 'consulting'"
    >
      <div
        class="mains-content"
        style="
          display: flex;
          gap: 20px;
          align-items: center;
          justify-content: space-evenly;
        "
      >
        <div>
          <!-- 직무면접 1번 -->
          <div class="box10-2">
            <div class="question-container">
              <p class="box-text3">직무면접 1번</p>
              <p class="interview-q">
                {{ interviewReport.jobquestion1 || "질문 없음" }}
              </p>
            </div>
            <div class="answer-container">
              <p class="box-text2">나의 답변</p>
              <p class="interview-a">
                {{ interviewReport.jobanswer1 || "답변 없음" }}
              </p>
            </div>
          </div>

          <!-- 직무면접 2번 -->
          <div class="box10-2">
            <div class="question-container">
              <p class="box-text3">직무면접 2번</p>
              <p class="interview-q">
                {{ interviewReport.jobquestion2 || "질문 없음" }}
              </p>
            </div>
            <div class="answer-container">
              <p class="box-text2">나의 답변</p>
              <p class="interview-a">
                {{ interviewReport.jobanswer2 || "답변 없음" }}
              </p>
            </div>
          </div>
        </div>

        <div>
          <!-- 피드백 1 -->
          <div class="box10-2">
            <div class="question-container">
              <p class="box-text3">컨설턴트 피드백</p>
              <hr class="box-line" />
              <p class="interview-f">
                {{ consultantfeedback.feedback1 || "피드백 없음" }}
              </p>
            </div>
          </div>
          <!-- 피드백 2 -->
          <div class="box10-2">
            <div class="question-container">
              <p class="box-text3">컨설턴트 피드백</p>
              <hr class="box-line" />
              <p class="interview-f">
                {{ consultantfeedback.feedback2 || "피드백 없음" }}
              </p>
            </div>
          </div>
        </div>
      </div>

      <!-- 직무면접 종합분석 -->
      <div class="box9-2">
        <p class="box-text">컨설턴트 평가 종합 요약</p>
        <hr class="box-line" />
        <p class="interview-a-p">
          {{ consultantTotalFeedback || "종합 요약 내용 없음" }}
        </p>
      </div>
    </div>

    <div>
      <p
        style="
          margin-top: 40px;
          font-size: 1.3rem;
          font-weight: bold;
          margin-left: 1180px;
        "
      ></p>
    </div>
  </div>
</template>

<script>
import { ref, onMounted, computed } from "vue";
import axios from "axios";

export default {
  setup() {
    const memberData = ref({});
    const categoryMap = {
      1: "IT/개발",
      2: "교육",
      3: "영업/마케팅",
      4: "기획/전략",
      5: "경영",
    };

    const locationMap = {
      1: "서울",
      2: "경기도",
      3: "충청도",
      4: "전라도",
      5: "경상도",
      6: "강원도",
      7: "제주도",
    };
    const interviewReport = ref({
      jobquestion1: "",
      jobanswer1: "",
      jobquestion2: "",
      jobanswer2: "",
    });
    const consultantDetail = ref({});
    const stressRate = ref(0);
    const voiceRate = ref(0);
    const postureBadCountRate = ref(0);
    const memberSchedules = ref([]);
    const activeSection = ref("ai-analysis");
    const consultantfeedback = ref({
      feedback1: "",
      feedback2: "",
    });
    const consultantTotalFeedback = ref("");

    // 데이터 변환 함수(회원 정보 희망분야, 거주지역)
    const transformMemberData = (data) => {
      if (data) {
        return {
          ...data,
          categcd: categoryMap[data.categcd] || "알 수 없음",
          loccd: locationMap[data.loccd] || "알 수 없음",
        };
      }
      return null;
    };

    // 회원정보 가져오기
    const fetchMemberData = async (memno) => {
      try {
        const response = await axios.get(
          `${process.env.VUE_APP_BACK_END_URL}/mainpage/memberDetail?memno=${memno}`
        );
        memberData.value = transformMemberData(response.data);
        console.log(memberData.value);
      } catch (error) {
        console.error("Error fetching member data:", error);
      }
    };
    // 스트레스율
    const fetchStressRate = async (intno) => {
      try {
        const response = await axios.get(
          `${process.env.VUE_APP_BACK_END_URL}/mainpage/stressRate?intno=${intno}`
        );
        stressRate.value = response.data;
      } catch (error) {
        console.error("Error fetching stress rate:", error);
      }
    };
    // 음성분석
    const fetchVoiceRate = async (intno) => {
      try {
        const response = await axios.get(
          `${process.env.VUE_APP_BACK_END_URL}/mainpage/voiceRate?intno=${intno}`
        );
        voiceRate.value = response.data;
      } catch (error) {
        console.error("Error fetching voice rate:", error);
      }
    };
    // 자세분석
    const fetchPostureBadCountRate = async (intno) => {
      try {
        const response = await axios.get(
          `${process.env.VUE_APP_BACK_END_URL}/mainpage/postureBadCountRate?intno=${intno}`
        );
        postureBadCountRate.value = response.data;
      } catch (error) {
        console.error("Error fetching posture bad count rate:", error);
      }
    };
    // 컨설턴트 평가점수
    const fetchConsultantScore = async (intno) => {
      try {
        const response = await axios.get(
          `${process.env.VUE_APP_BACK_END_URL}/mainpage/consultantScore?intno=${intno}`
        );
        interviewReport.value = response.data;
      } catch (error) {
        console.error("Error fetching consultant score:", error);
      }
    };
    // 컨설턴트 정보
    const fetchConsultantDetail = async (cnsno) => {
      try {
        const response = await axios.get(
          `${process.env.VUE_APP_BACK_END_URL}/mainpage/consultantDetail?cnsno=${cnsno}`
        );
        consultantDetail.value = response.data;
      } catch (error) {
        console.error("Error fetching consultant detail:", error);
      }
    };
    // 컨설턴트-회원 매칭정보
    const fetchMemberConsultantMapping = async (memno) => {
      try {
        const response = await axios.get(
          `${process.env.VUE_APP_BACK_END_URL}/mainpage/memberConsultantMapping?memno=${memno}`
        );
        const cnsno = response.data.cnsno;
        fetchConsultantDetail(cnsno);
      } catch (error) {
        console.error("Error fetching member-consultant mapping:", error);
      }
    };
    // 회원일정
    const fetchMemberSchedules = async (memno) => {
      try {
        const response = await axios.get(
          `${process.env.VUE_APP_BACK_END_URL}/mainpage/memberSchedules?memno=${memno}`
        );
        memberSchedules.value = response.data;
      } catch (error) {
        console.error("Error fetching member schedules:", error);
      }
    };
    // 직무면접 질문&답변
    const fetchConsultantQuestions = async (intno, qnos) => {
      try {
        const response = await axios.get(
          `${
            process.env.VUE_APP_BACK_END_URL
          }/mainpage/consultantQuestions?intno=${intno}&qnos=${qnos.join(
            "&qnos="
          )}`
        );
        const data = response.data;

        // 질문과 답변 매핑
        const questionMap = {
          6: { key: "jobquestion1", answerKey: "jobanswer1" },
          7: { key: "jobquestion2", answerKey: "jobanswer2" },
        };

        data.forEach((item) => {
          if (questionMap[item.qno]) {
            interviewReport.value[questionMap[item.qno].key] = item.question;
            interviewReport.value[questionMap[item.qno].answerKey] =
              item.answer;
          }
        });
      } catch (error) {
        console.error("Error fetching consultant questions:", error);
      }
    };
    // 직무면접 질문&답변별 피드백
    const fetchConsultantFeedback = async (memno, cnsno, intno, qnos) => {
      try {
        const response = await axios.get(
          `${process.env.VUE_APP_BACK_END_URL}/mainpage/consultantFeedback`,
          {
            params: {
              memno,
              cnsno,
              intno,
              qnos: qnos.join(","),
            },
          }
        );
        const data = response.data;

        // 피드백 매핑
        const feedbackMap = {
          6: { key: "feedback1" },
          7: { key: "feedback2" },
        };

        data.forEach((item) => {
          if (feedbackMap[item.qno]) {
            consultantfeedback.value[feedbackMap[item.qno].key] =
              item.qcnsfeedbk;
          }
        });
      } catch (error) {
        console.error("Error fetching consultant feedback:", error);
        // 에러 상세 정보 로깅
        if (error.response) {
          console.error("Response data:", error.response.data);
          console.error("Response status:", error.response.status);
          console.error("Response headers:", error.response.headers);
        }
      }
    };
    // 종합피드백
    const fetchConsultantTotalFeedback = async (memno, intno) => {
      try {
        const response = await axios.get(
          `${process.env.VUE_APP_BACK_END_URL}/mainpage/consultantTotalFeedback`,
          {
            params: { memno, intno },
          }
        );
        // console.log("API response:", response.data); // 응답 데이터 확인
        consultantTotalFeedback.value = response.data; // 응답 값을 직접 할당
      } catch (error) {
        console.error("Error fetching consultant total feedback:", error);
      }
    };

    // 회원 일정
    const calculateDday = (date) => {
      const today = new Date();
      const targetDate = new Date(date);
      const timeDiff = targetDate.getTime() - today.getTime();
      const dayDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));
      return dayDiff;
    };

    // 디데이
    const upcomingSchedules = computed(() => {
      return memberSchedules.value
        .map((schedule) => ({
          ...schedule,
          dday: calculateDday(schedule.startdt),
        }))
        .filter((schedule) => schedule.dday >= 0)
        .sort((a, b) => a.dday - b.dday)
        .slice(0, 3); // 최대 3개의 일정만 표시
    });

    // 핵심키워드 분석
    const keywordStressLevel = computed(() => {
      return stressRate.value > 40 ? "스트레스가 높음" : "스트레스 적정수준";
    });

    const keywordVoiceStability = computed(() => {
      return voiceRate.value > 70 ? "목소리가 불안정함" : "목소리가 안정적임";
    });

    const keywordPostureBalance = computed(() => {
      return postureBadCountRate.value > 50 ? "자세가 불균형함" : "균형 잡힘";
    });

    const keywordConsultantMsg = computed(() => {
      return interviewReport.value.cnsscore > 80
        ? "매우 우수함"
        : "개선이 필요함";
    });

    onMounted(async () => {
      const memno = 10; // 예시 memno 값
      const intno = 10; // 예시 intno 값
      const cnsno = 1001; // 예시 cnsno 값
      await fetchMemberData(memno);
      await fetchStressRate(intno);
      await fetchVoiceRate(intno);
      await fetchPostureBadCountRate(intno);
      await fetchConsultantScore(intno);
      await fetchMemberConsultantMapping(memno);
      await fetchMemberSchedules(memno);
      await fetchConsultantQuestions(intno, [6, 7]);
      await fetchConsultantFeedback(memno, cnsno, intno, [6, 7]);
      await fetchConsultantTotalFeedback(memno, intno);

      // 프로그레스 바 초기화
      const progressBars = document.querySelectorAll(".progress-bar");
      progressBars.forEach((bar) => {
        const value = bar.getAttribute("data-value");
        bar.style.width = `${value}%`;
      });
    });

    const activateSection = (sectionId, event) => {
      event.preventDefault();
      activeSection.value = sectionId;
    };

    return {
      memberData,
      interviewReport,
      consultantDetail,
      stressRate,
      voiceRate,
      postureBadCountRate,
      upcomingSchedules,
      activeSection,
      activateSection,
      keywordStressLevel,
      keywordVoiceStability,
      keywordPostureBalance,
      keywordConsultantMsg,
      consultantfeedback,
      consultantTotalFeedback,
    };
  },
};
</script>

<style>
</style>
