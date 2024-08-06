<template>
  <div class="mains-containers">
    <div class="mains-header-top">
      <p class="mains-header-title">ONLINE AI INTERVIEW REPORT</p>
    </div>
    <div class="mains-header-container">
      <div class="mains-headers-left">
        <div class="mains-headers-lefts">
          <div>
            <img src="/img/consul1.jpg" class="center-img" />
          </div>
          <div>
            <p class="mains-headers-p">{{ interviewReport.mname }}</p>
          </div>
          <div>
            <table class="mains-headers-table">
              <tr>
                <td class="mains-headers-td">지원분야</td>
                <td class="mains-headers-td-a">
                  {{ interviewReport.applpart }}
                </td>
              </tr>
              <tr>
                <td class="mains-headers-td">핸 &nbsp;드&nbsp; 폰</td>
                <td class="mains-headers-td-a">
                  {{ interviewReport.mphoneno }}
                </td>
              </tr>
              <tr>
                <td class="mains-headers-td">이&nbsp; 메&nbsp; 일</td>
                <td class="mains-headers-td-a">{{ interviewReport.memail }}</td>
              </tr>
              <tr>
                <td class="mains-headers-td">거주지역</td>
                <td class="mains-headers-td-a">
                  {{ interviewReport.prefarea }}
                </td>
              </tr>
            </table>
          </div>
        </div>
        <div class="mains-headers-middle">
          <div class="mains-headers-middle-top">
            <div style="display: flex; flex-direction: row">
              <p class="header-evaluation">종합평가</p>
              <div>
                <hr class="evaluation-line" />
                <p class="evaluation-text">{{ interviewReport.rating }}</p>
              </div>
            </div>
            <div style="display: flex">
              <p class="evaluation-letter">{{ interviewReport.rating }}</p>
              <div style="margin-top: -18px">
                <p class="evaluation-info">{{ interviewReport.analysisres }}</p>
                <p class="evaluation-subinfo">
                  {{ interviewReport.scoretot }}점
                </p>
                <p class="evaluation-subinfo">
                  {{ interviewReport.rank }}등/{{
                    interviewReport.totalcnt
                  }}명({{ interviewReport.percentile }}%)
                </p>
              </div>
            </div>
          </div>
          <div class="mains-headers-middle-bottom">
            <div>
              <div class="mains-result-head">핵심 키워드</div>
              <div style="margin-left: 20px; margin-top: 10px">
                <div style="display: flex; margin-bottom: 25px">
                  <div style="display: flex">
                    <img src="img/res_stress.png" class="res-img" />
                    <p class="res-text">{{ interviewReport.stressLevel }}</p>
                  </div>
                  <div style="display: flex; margin-left: 20px">
                    <img src="img/ress_audio.png" class="res-img" />
                    <p class="res-text">{{ interviewReport.voiceStability }}</p>
                  </div>
                </div>
                <div style="display: flex">
                  <div style="display: flex">
                    <img src="img/res_po.png" class="res-img" />
                    <p class="res-text">{{ interviewReport.postureBalance }}</p>
                  </div>
                  <div style="display: flex; margin-left: 20px">
                    <img src="img/res_resume.png" class="res-img" />
                    <p class="res-text">{{ interviewReport.consultantMsg }}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="mains-headers-right">
          <div class="mains-headers-right-top">
            <div class="analysis-head">분석결과</div>
            <div style="display: flex">
              <div>
                <div class="analysis-left">
                  스트레스
                  <div class="progress-container">
                    <div
                      class="progress-bar"
                      :data-value="interviewReport.scoree"
                    ></div>
                  </div>
                  <div class="analysis-rate">{{ interviewReport.scoree }}%</div>
                </div>
                <div class="analysis-left">
                  음성분석
                  <div class="progress-container">
                    <div
                      class="progress-bar"
                      :data-value="interviewReport.scorev"
                    ></div>
                  </div>
                  <div class="analysis-rate">{{ interviewReport.scorev }}%</div>
                </div>
                <div class="analysis-left">
                  자세분석
                  <div class="progress-container">
                    <div
                      class="progress-bar"
                      :data-value="interviewReport.scorep"
                    ></div>
                  </div>
                  <div class="analysis-rate">{{ interviewReport.scorep }}%</div>
                </div>
                <div class="analysis-left">
                  컨설턴트 평가
                  <div class="progress-container" style="margin-left: 8.5px">
                    <div
                      class="progress-bar"
                      :data-value="interviewReport.consultantScore"
                    ></div>
                  </div>
                  <div class="analysis-rate">
                    {{ interviewReport.consultantScore }}점
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="mains-headers-right-bottom">
            <div class="main-con-head">My Consultant</div>
            <div style="display: flex">
              <img src="/img/user1.png" class="center-img2" />
              <div class="main-con-name" style="display: flex">
                컨설턴트: {{ interviewReport.consultantName }}
                <div>
                  <img src="/img/consul2.svg" class="cons-svg" />
                </div>
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
        <div @click="activateSection('schedule-management', $event)">
          일정관리
        </div>
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
      <div class="mains-content" style="display: flex; gap: 20px">
        <div>
          <!-- 직무면접 1번 -->
          <div class="box10">
            <div style="height: 120px">
              <p class="box-text3">직무면접 1번</p>
              <p class="interview-q">
                {{ interviewReport.jobquestion1 || "질문 없음" }}
              </p>
            </div>
            <div>
              <p class="box-text2">답변</p>
              <hr class="box-line" />
              <p class="interview-a">
                {{ interviewReport.jobanswer1 || "답변 없음" }}
              </p>
            </div>
          </div>

          <!-- 직무면접 2번 -->
          <div class="box10">
            <div style="height: 120px">
              <p class="box-text3">직무면접 2번</p>
              <p class="interview-q">
                {{ interviewReport.jobquestion2 || "질문 없음" }}
              </p>
            </div>
            <div>
              <p class="box-text2">답변</p>
              <hr class="box-line" />
              <p class="interview-a">
                {{ interviewReport.jobanswer2 || "답변 없음" }}
              </p>
            </div>
          </div>
        </div>

        <div>
          <!-- 피드백 1 -->
          <div>
            <div class="box11">
              <p class="box-text">피드백</p>
              <hr class="box-line" />
              <p class="interview-a-p">
                {{ interviewReport.consultantfeedback1 || "피드백 없음" }}
              </p>
            </div>
            <!-- 피드백 2 -->
            <div class="box11">
              <p class="box-text">피드백</p>
              <hr class="box-line" />
              <p class="interview-a-p">
                {{ interviewReport.consultantfeedback2 || "피드백 없음" }}
              </p>
            </div>
          </div>
        </div>
      </div>

      <!-- 직무면접 종합분석 -->
      <div class="box12">
        <p class="box-text">직무면접 종합분석</p>
        <hr class="box-line" />
        <p class="interview-a-p">
          {{ interviewReport.consultanttotalfeedback || "종합분석 내용 없음" }}
        </p>
      </div>
    </div>

    <!-- 일정관리 탭 삭제 : 사이드 바로 이동 -->

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
import { ref, defineComponent, onMounted, watch, computed } from "vue";
import Highcharts from "highcharts";
import Boost from "highcharts/modules/boost";
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import interactionPlugin from "@fullcalendar/interaction";
import axios from "axios";

// Highcharts Boost 모듈 초기화
Boost(Highcharts);

export default defineComponent({
  components: {
    FullCalendar,
  },
  setup() {
    const activeSection = ref("ai-analysis");
    const interviewReport = ref({});
    const isLoading = ref(true);
    const error = ref(null);

    // 면접 보고서 데이터를 백엔드에서 가져오는 함수
    const fetchInterviewReport = async (id) => {
      try {
        isLoading.value = true;
        const response = await axios.get(
          `${process.env.VUE_APP_BACK_END_URL}/mainpage?id=${id}`
        );
        console.log("API 응답 데이터:", response.data);
        interviewReport.value = response.data;

        // 캘린더 이벤트 데이터 설정
        calendarOptions.value.events = response.data.events.map((event) => ({
          title: event.title,
          start: event.start,
          end: event.end || event.start, // end가 없으면 start를 사용
        }));

        // 차트 데이터 업데이트
        updateCharts(response.data);
      } catch (err) {
        console.error("면접 보고서를 가져오는 중 오류 발생:", err);
        error.value = "데이터를 불러오는 데 실패했습니다.";
      } finally {
        isLoading.value = false;
      }
    };

    const updateCharts = (data) => {
      // 차트 1: 감정 분석 (파이 차트)
      Highcharts.chart("chart-1", {
        chart: {
          type: "pie",
          backgroundColor: null,
        },
        title: { text: "", align: "center" },
        tooltip: {
          pointFormat: "{series.name}: <b>{point.percentage:.1f}%</b>",
        },
        plotOptions: {
          pie: {
            allowPointSelect: true,
            borderWidth: 2,
            cursor: "pointer",
            dataLabels: {
              enabled: true,
              format: "<b>{point.name}</b><br>{point.percentage:.1f}%",
              distance: 20,
            },
          },
        },
        series: [
          {
            name: "감정 분석",
            colorByPoint: true,
            data: data.pieChartData.map((item) => ({
              name: item.emotion,
              y: item.emotionrate,
            })),
          },
        ],
        credits: { enabled: false },
      });

      // 차트 2: 음성 분석 (로그 차트)
      Highcharts.chart("chart-2", {
        chart: {
          type: "line", // 로그 차트에 적합한 타입으로 수정
          backgroundColor: null,
        },
        title: { text: "" },
        xAxis: {
          categories: data.logChartData.map((item) => item.question), // 질문을 x축 카테고리로 설정
        },
        yAxis: {
          type: "logarithmic", // y축을 로그 스케일로 설정
          title: { text: "안정도" },
        },
        tooltip: {
          headerFormat: "<b>{series.name}</b><br />",
          pointFormat: "{point.y} points",
        },
        series: [
          {
            name: "음성 분석",
            data: data.logChartData.map((item) => ({
              y: item.voiceStabilityscore, // y값으로 안정도 점수를 사용
              color: item.color, // 각 데이터 포인트의 색상을 지정
            })),
          },
        ],
        credits: { enabled: false },
      });

      // 차트 3: 자세 분석 (더블 축)
      Highcharts.chart("chart-3", {
        chart: {
          type: "column", // 기본 차트 타입
          backgroundColor: null,
        },
        title: { text: "" },
        xAxis: {
          categories: data.detailedAnalysisData.map((item) => item.question),
        },
        yAxis: [
          {
            // 바 그래프를 위한 좌측 Y축
            title: { text: "총점" },
            labels: {
              format: "{value}",
            },
            max: 100, // 총점 최대값 설정
          },
          {
            // 선형 그래프를 위한 우측 Y축
            title: { text: "평균 점수" },
            labels: {
              format: "{value}",
            },
            opposite: true,
          },
        ],
        tooltip: {
          shared: true,
          formatter: function () {
            let tooltip = `<b>${this.x}</b><br/>`;
            this.points.forEach(function (point) {
              tooltip += `<span style="color:${point.series.color}">\u25CF</span> ${point.series.name}: <b>${point.y}</b><br/>`;
            });
            return tooltip;
          },
        },
        series: [
          {
            name: "총점",
            type: "column",
            data: data.detailedAnalysisData.map(
              (item) => item.postureBalancescore
            ),
            color: "#7cb5ec",
            yAxis: 0, // 좌측 Y축
          },
          {
            name: "평균 점수",
            type: "line",
            data: data.detailedAnalysisData.map(
              (item) => item.postureBalanceavgscore
            ),
            color: "#f15c80",
            yAxis: 1, // 우측 Y축
          },
        ],
        credits: { enabled: false },
      });

      // 차트 4: 일관성 분석 (날짜별 피드백 점수)
      Highcharts.chart("chart-4", {
        chart: {
          type: "column",
          backgroundColor: null,
        },
        title: { text: "" },
        xAxis: {
          categories: data.consistencyAnalysisData.map((item) => item.date), // 날짜를 x축 카테고리로 설정
        },
        yAxis: {
          title: { text: "피드백 점수" },
          max: 100, // 총점이 100점이므로 y축 최대값을 100으로 설정
        },
        tooltip: {
          pointFormat: "{series.name}: <b>{point.y:.1f}</b>",
        },
        series: [
          {
            name: "컨설턴트 평가점수",
            data: data.consistencyAnalysisData.map(
              (item) => item.feedbackscore
            ),
            color: "#7cb5ec",
          },
        ],
        credits: { enabled: false },
      });
    };

    onMounted(async () => {
      await fetchInterviewReport(1);

      // 프로그레스 바 초기화
      const progressBars = document.querySelectorAll(".progress-bar");
      progressBars.forEach((bar) => {
        const value = bar.getAttribute("data-value");
        bar.style.width = `${value}%`;
      });
    });

    // 특정 섹션을 활성화하는 함수
    const activateSection = (sectionId, event) => {
      event.preventDefault();
      activeSection.value = sectionId;
    };

    // 달력의 날짜 클릭 이벤트 핸들러
    const handleDateClick = (info) => {
      const title = prompt("이벤트 제목을 입력하세요:");
      if (title) {
        calendarOptions.value.events.push({
          title: title,
          date: info.dateStr,
        });
      }
    };

    // 달력 설정 옵션
    const calendarOptions = ref({
      plugins: [dayGridPlugin, interactionPlugin],
      initialView: "dayGridMonth",
      events: [],
      dateClick: handleDateClick,
    });

    // 오늘 날짜
    const today = new Date();

    // D-Day 계산 및 리스트 생성
    const dDays = computed(() => {
      return interviewReport.value.events.map((event) => {
        const eventDate = new Date(event.start);
        const timeDiff = eventDate.getTime() - today.getTime();
        const dayDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));
        return {
          title: event.title,
          daysAway: dayDiff,
          isPast: dayDiff < 0,
        };
      });
    });

    // 활성화된 섹션을 감시하여 로그를 출력하는 watch
    watch(activeSection, (newValue) => {
      if (newValue === "schedule-management") {
        console.log("일정 관리 섹션이 활성화되었습니다.");
      }
    });

    return {
      activeSection,
      interviewReport,
      isLoading,
      error,
      activateSection,
      handleDateClick,
      calendarOptions,
      dDays,
      eventsList: computed(() => interviewReport.value.events),
    };
  },
});
</script>







