<template>
  <div class="container">
    <div class="mainpage-title">
      <blockquote class="mainpage-profile">
        <b> <p>ONLINE AI INTERVIEW REPORT</p></b>
      </blockquote>
    </div>
    <div class="mains-header-container">
      <div class="mains-headers-left">
        <div class="mains-headers-lefts">
          <div
            style="display: flex; justify-content: center; align-items: center"
          >
            <img
              :src="profileImageUrl"
              class="center-img"
              alt="Profile Image"
              @error="handleImageError"
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
          <!-- 컨설턴트 정보 -->
          <div class="mains-headers-right-bottom">
            <div class="main-con-head">My Consultant</div>
            <div
              v-if="consultantDetail && consultantDetail.consultant"
              style="
                display: flex;
                align-items: center;
                justify-content: space-evenly;
              "
            >
              <img
                :src="ConsultantImageUrl"
                class="center-img2"
                alt="Consultant Image"
                @error="handleImageError"
              />
              <div class="main-con-name" @click="goToConsultantChat">
                {{ consultantDetail.consultant.name }} 컨설턴트
                <div class="chat">➕</div>
                <p
                  style="
                    width: 100px;
                    position: absolute;
                    margin-left: -30px;
                    margin-top: -14px;
                    font-size: 1.9rem;
                  "
                >
                  🪪
                </p>
                <p style="font-size: 0.8em; color: #6c757d; margin-left: 40px">
                  {{ consultantDetail.consultant.categcd }} 전문
                </p>
              </div>
            </div>
            <div v-else>
              <button @click="goToConsultantInfo" class="apply-button">
                컨설턴트 신청하러 가기
              </button>
            </div>
          </div>
          <!-- 컨설턴트 정보 -->
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
            <div id="chart-1" style="margin-top: -10px"></div>
          </div>
          <div class="box5">
            <p class="box-text">음성 분석 결과</p>
            <div id="chart-2" style="margin-top: -15px"></div>
          </div>
        </div>
        <div class="mains-floor-2">
          <div class="box3">
            <p class="box-text">자세 분석 결과</p>
            <div id="chart-3" style="margin-top: -10px"></div>
          </div>
          <div class="box6">
            <p class="box-text">감성, 음성, 자세 요약</p>
            <div id="chart-4" style="margin-top: -10px"></div>
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
  </div>
</template>

<script>
import { ref, onMounted, computed } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
import Highcharts from "highcharts";
import HighchartsMore from "highcharts/highcharts-more";

HighchartsMore(Highcharts);

export default {
  props: {
    activeSection: String,
  },
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
    const loading = ref(false);
    const error = ref(null);
    const consultantDetail = ref(null);
    const router = useRouter();

    // 이미지 로드 실패 시 대체 이미지 설정
    const handleImageError = (event) => {
      event.target.src = "/img/MainPage_image/noimg.png";
    };

    // 회원에게 매칭된 컨설턴트 없을 때, 컨설턴트 신청 페이지로 이동
    const goToConsultantInfo = () => {
      router.push("/ConsultantInfo");
    };

    // 컨설턴트 1대1 상담으로 가기
    const goToConsultantChat = () => {
      router.push("/OneToOne");
    };

    // 회원 데이터 변환 함수(희망직무, 거주지역)
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

    // 회원사진 가져오기
    const profileImageUrl = computed(() => {
      if (memberData.value && memberData.value.imgname) {
        return `/img/upimg/${memberData.value.imgname}`;
      }
      return "/img/MainPage_image/noimg.png"; // 기본 이미지 경로
    });

    // 회원정보 가져오기
    const fetchMemberData = async (memno) => {
      try {
        const response = await axios.get(
          `${process.env.VUE_APP_BACK_END_URL}/mainpage/memberDetail?memno=${memno}`
        );
        memberData.value = transformMemberData(response.data);
      } catch (error) {
        console.error("Error fetching member data:", error);
      }
    };

    // 스트레스율
    const fetchStressRate = async (intno, memno) => {
      try {
        const response = await axios.get(
          `${process.env.VUE_APP_BACK_END_URL}/mainpage/stressRate?intno=${intno}&memno=${memno}`
        );
        stressRate.value = response.data;
      } catch (error) {
        console.error("Error fetching stress rate:", error);
      }
    };
    // 음성분석
    const fetchVoiceRate = async (intno, memno) => {
      try {
        const response = await axios.get(
          `${process.env.VUE_APP_BACK_END_URL}/mainpage/voiceRate?intno=${intno}&memno=${memno}`
        );
        voiceRate.value = response.data;
      } catch (error) {
        console.error("Error fetching voice rate:", error);
      }
    };
    // 자세분석
    const fetchPostureBadCountRate = async (intno, memno) => {
      try {
        const response = await axios.get(
          `${process.env.VUE_APP_BACK_END_URL}/mainpage/postureBadCountRate?intno=${intno}&memno=${memno}`
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

    // 회원에게 매칭된 컨설턴트 정보 가져오기
    const fetchConsultantDetail = async (memno) => {
      try {
        const response = await axios.get(
          `${process.env.VUE_APP_BACK_END_URL}/mainpage/memberConsultantDetail?memno=${memno}`
        );
        const transformedData = transformConsultantData(
          response.data || { consultant: null }
        );
        consultantDetail.value = transformedData;
      } catch (error) {
        console.error("Error fetching consultant detail:", error);
        consultantDetail.value = { consultant: null }; // 오류 발생 시 안전한 기본값 설정
      }
    };
    // 컨설턴트 사진 URL 가져오기
    const ConsultantImageUrl = computed(() => {
      if (
        consultantDetail.value &&
        consultantDetail.value.consultant &&
        consultantDetail.value.consultant.imgname
      ) {
        return `/img/upimg/${consultantDetail.value.consultant.imgname}`;
      }
      return "/img/MainPage_image/noimg.png"; // 기본 이미지 경로
    });

    // 컨설턴트 데이터 변환 함수(전문분야)
    const transformConsultantData = (data) => {
      if (data && data.consultant) {
        return {
          ...data,
          consultant: {
            ...data.consultant,
            categcd: categoryMap[data.consultant.categcd] || "알 수 없음",
          },
        };
      }
      return data; // 변환할 수 없는 경우 원본 데이터 반환
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
      await fetchStressRate(intno, memno);
      await fetchVoiceRate(intno, memno);
      await fetchPostureBadCountRate(intno, memno);
      await fetchConsultantScore(intno);
      await fetchMemberSchedules(memno);
      await fetchConsultantQuestions(intno, [6, 7]);
      await fetchConsultantFeedback(memno, cnsno, intno, [6, 7]);
      await fetchConsultantTotalFeedback(memno, intno);
      await fetchConsultantDetail(memno);

      // 프로그레스 바 초기화
      const progressBars = document.querySelectorAll(".progress-bar");
      progressBars.forEach((bar) => {
        const value = bar.getAttribute("data-value");
        bar.style.width = `${value}%`;
      });

      // 버블차트 시작
      Highcharts.chart("chart-1", {
        chart: {
          type: "packedbubble",
          height: "65%",
        },
        title: {
          text: "",
          align: "left",
        },
        subtitle: {
          text: "최근 5회 인성면접 결과",
          align: "left",
        },
        tooltip: {
          useHTML: true,
          pointFormat: "<b>{point.name}:</b> {point.value}",
        },
        plotOptions: {
          packedbubble: {
            minSize: "50%",
            maxSize: "100%",
            zMin: 0,
            zMax: 1000,
            layoutAlgorithm: {
              gravitationalConstant: 0.05,
              splitSeries: true,
              seriesInteraction: false,
              dragBetweenSeries: true,
              parentNodeLimit: true,
            },
            dataLabels: {
              enabled: true,
              format: "{point.name}",
              filter: {
                property: "y",
                operator: ">",
                value: 250,
              },
              style: {
                color: "black",
                textOutline: "none",
                fontWeight: "normal",
              },
            },
          },
        },
        series: [
          {
            name: "8/5",
            data: [
              { name: "긍정", value: 60 },
              { name: "부정", value: 50 },
              { name: "중립", value: 13 },
            ],
          },
          {
            name: "8/6",
            data: [
              { name: "긍정", value: 3 },
              { name: "부정", value: 7 },
              { name: "중립", value: 0 },
            ],
          },
          {
            name: "8/7",
            data: [
              { name: "긍정", value: 50 },
              { name: "부정", value: 13 },
              { name: "중립", value: 17 },
            ],
          },
          {
            name: "8/8",
            data: [
              { name: "긍정", value: 80 },
              { name: "부정", value: 30 },
              { name: "중립", value: 10 },
            ],
          },
          {
            name: "8/9",
            data: [
              { name: "긍정", value: 30 },
              { name: "부정", value: 30 },
              { name: "중립", value: 40 },
            ],
          },
        ],
      });
      // 버블차트 끝

      // 라인차트 시작
      Highcharts.chart("chart-2", {
        title: {
          text: "",
          align: "left",
        },

        subtitle: {
          text: "최근 5회 인성면접 결과",
          align: "left",
        },

        yAxis: {
          title: {
            text: "음성 점수",
          },
        },

        xAxis: {
          categories: ["8/5", "8/6", "8/7", "8/8", "8/9"],
        },

        legend: {
          layout: "vertical",
          align: "right",
          verticalAlign: "middle",
        },
        series: [
          {
            name: "Q1",
            data: [10, 20, 30, 40, 50],
          },
          {
            name: "Q2",
            data: [20, 10, 10, 10, 10],
          },
          {
            name: "Q3",
            data: [30, 20, 20, 20, 20],
          },
          {
            name: "Q4",
            data: [40, 30, 30, 30, 30],
          },
          {
            name: "Q5",
            data: [50, 40, 40, 40, 40],
          },
        ],

        responsive: {
          rules: [
            {
              condition: {
                maxWidth: 500,
              },
              chartOptions: {
                legend: {
                  layout: "horizontal",
                  align: "center",
                  verticalAlign: "bottom",
                },
              },
            },
          ],
        },
      });
      // 라인차트 끝
      // 바 차트 시작
      const chart = Highcharts.chart("chart-3", {
        title: {
          text: "",
          align: "left",
        },
        subtitle: {
          text: "최근 5회 인성면접 결과",
          align: "left",
        },
        colors: ["#16d4e6", "#0dd9db", "#03dfd0", "#00e4c5", "#00e9ba"],
        xAxis: {
          categories: ["8/5", "8/6", "8/7", "8/8", "8/9"],
        },
        yAxis: {
          title: {
            text: "자세 점수",
          },
        },
        series: [
          {
            type: "column",
            name: "Unemployed",
            borderRadius: 5,
            colorByPoint: true,
            data: [38, 38, 38, 38, 38],
            showInLegend: false,
          },
        ],
      });
      // 바 차트 끝

      // 멀티 바 차트 시작
      Highcharts.chart("chart-4", {
        chart: {
          type: "column",
        },
        title: {
          text: "",
        },
        subtitle: {
          text: "최근 5회 인성면접 결과",
          align: "left",
        },
        yAxis: {
          title: {
            text: "점수",
          },
        },
        xAxis: {
          categories: ["감성", "음성", "자세"],
        },
        credits: {
          enabled: false,
        },
        plotOptions: {
          column: {
            borderRadius: "25%",
          },
        },
        series: [
          {
            name: "8/5",
            data: [2, 2, 2],
          },
          {
            name: "8/6",
            data: [3, 3, 3],
          },
          {
            name: "8/7",
            data: [4, 4, 4],
          },
          {
            name: "8/8",
            data: [4, 4, 4],
          },
          {
            name: "8/9",
            data: [4, 4, 4],
          },
        ],
      });

      // 멀티 바 차트 끝
    });

    // 탭 활성화
    const activateSection = (sectionId, event) => {
      event.preventDefault();
      activeSection.value = sectionId;
    };

    return {
      memberData,
      interviewReport,
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
      loading,
      error,
      consultantDetail,
      handleImageError,
      goToConsultantInfo,
      goToConsultantChat,
      profileImageUrl,
      ConsultantImageUrl,
    };
  },
};
</script>