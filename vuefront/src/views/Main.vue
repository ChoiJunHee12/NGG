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
                <td class="mains-headers-td">선호지역</td>
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
                <p class="evaluation-subinfo">{{ interviewReport.rank }}등/{{ interviewReport.totalcnt }}명({{ interviewReport.percentile }}%)</p>
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
                  평가점수
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
            <div id="chart-2" style="height: 300px"></div>
          </div>
          <div class="box5">
            <p class="box-text">음성 분석 결과</p>
            <hr class="box-line" />
            <div id="chart-1" style="height: 300px"></div>
          </div>
        </div>
        <div class="mains-floor-2">
          <div class="box3">
            <p class="box-text">자세 분석 결과</p>
            <hr class="box-line" />
            <div id="chart-3" style="height: 300px"></div>
          </div>

          <div class="box6">
            <p class="box-text">일관성 분석 결과</p>
            <hr class="box-line" />
            <div id="chart-5" style="height: 300px"></div>
          </div>
        </div>
      </div>
    </div>

    <div
      id="consulting"
      class="tab-content"
      v-if="activeSection === 'consulting'"
    >
      <div class="mains-content" style="display: flex; gap: 20px">
        <div>
          <div class="box10">
            <div style="height: 120px">
              <p class="box-text3">직무면접 1번</p>
              <p class="interview-q">
                RESTful API의 원칙과 이를 구현할 때 고려해야 할 사항은
                무엇인가요?
              </p>
            </div>
            <div>
              <p class="box-text2">답변</p>
              <hr class="box-line" />
              <p class="interview-a">
                RESTful API는 클라이언트-서버 구조, 무상태성, 캐시 가능성, 계층
                구조, 인터페이스 일관성, 그리고 코드 온디맨드라는 6가지 원칙에
                기반합니다. 이를 구현할 때는 URL 구조의 일관성, HTTP 메서드의
                올바른 사용(GET, POST, PUT, DELETE 등), 그리고 적절한 응답
                코드(200, 404, 500 등)를 고려해야 합니다.
              </p>
            </div>
          </div>
          <div class="box10">
            <div style="height: 120px">
              <p class="box-text3">직무면접 2번</p>
              <p class="interview-q">
                데이터베이스 인덱스의 종류와 이를 사용하는 이유는 무엇인가요?
              </p>
            </div>
            <div>
              <p class="box-text2">답변</p>
              <hr class="box-line" />
              <p class="interview-a">
                데이터베이스 인덱스는 검색 속도를 높이기 위해 사용됩니다.
                인덱스의 종류에는 B-Tree 인덱스, 해시 인덱스, 비트맵 인덱스,
                그리고 전체 텍스트 인덱스 등이 있습니다. B-Tree 인덱스는 범위
                검색에 유리하고, 해시 인덱스는 정확한 값 검색에 적합합니다.
              </p>
            </div>
          </div>
        </div>
        <div>
          <div class="box11">
            <p class="box-text">피드백</p>
            <hr class="box-line" />
            <p class="interview-a-p">
              이 대답은 RESTful API의 주요 원칙을 잘 설명하고 있습니다. 그러나
              더 구체적으로 설명할 수 있는 기회를 놓친 것 같습니다. 예를 들어,
              클라이언트-서버 구조의 장점(서버와 클라이언트의 독립적 발전
              가능성)이나 무상태성의 이점(서버 확장성 향상)을 추가적으로
              언급하면 좋습니다. 또한, 실제 프로젝트에서 이러한 원칙을 어떻게
              적용했는지 구체적인 예시를 제공하면 더 설득력 있는 답변이 될 수
              있습니다.
            </p>
          </div>
          <div class="box11">
            <p class="box-text">피드백</p>
            <hr class="box-line" />
            <p class="interview-a-p">
              이 대답은 인덱스의 기본 개념과 주요 유형을 잘 설명하고 있습니다.
              그러나 인덱스 사용의 장단점, 예를 들어 인덱스가 검색 성능을 높이는
              동시에 쓰기 성능을 저하시킬 수 있다는 점도 언급하면 좋습니다.
              또한, 실제 경험에서 인덱스를 사용하여 성능을 최적화한 사례를 들어
              설명하면 면접관에게 더 깊은 인상을 줄 수 있습니다.
            </p>
          </div>
        </div>
      </div>
      <div class="box12">
        <p class="box-text">직무면접 종합분석</p>
        <hr class="box-line" />
        <p class="interview-a-p">
          당신의 답변은 기본적인 기술 개념을 잘 설명하고 있으며, RESTful API와
          데이터베이스 인덱스에 대한 주요 원칙과 종류를 명확하게 이해하고 있는
          것으로 보입니다. 이는 기술적 기초가 탄탄하다는 것을 보여주는 긍정적인
          신호입니다. 하지만, 이론적인 설명에 그치지 않고, 더 구체적인 예시나
          실제 경험을 통해 답변을 풍부하게 만들면 더 좋을 것입니다. 조언:
          구체적인 예시 추가: RESTful API와 데이터베이스 인덱스를 실제
          프로젝트에서 어떻게 적용했는지 구체적인 사례를 제공해 보세요. 예를
          들어, 특정 프로젝트에서 API 설계를 어떻게 했는지, 그리고 인덱스를
          사용하여 어떻게 쿼리 성능을 최적화했는지 설명하면 면접관이 당신의 실무
          능력을 더 잘 이해할 수 있습니다. 장단점 및 고려사항 언급: RESTful
          API와 데이터베이스 인덱스 사용의 장단점을 설명하고, 이를 구현할 때
          고려해야 할 사항들을 덧붙이세요.이러한 개선점을 통해 당신의 답변이
          더욱 구체적이고 실무적인 경험을 반영하게 되면, 면접관에게 강한 인상을
          남길 수 있을 것입니다.
        </p>
      </div>
    </div>

    <div
      id="schedule-management"
      class="tab-content"
      v-if="activeSection === 'schedule-management'"
    >
      <div style="display: flex; justify-content: flex-start">
        <div class="box14">
          <FullCalendar
            v-if="activeSection === 'schedule-management'"
            :options="calendarOptions"
            @dateClick="handleDateClick"
          />
        </div>

        <div style="display: flex; flex-direction: column">
          <div class="box13">
            <p class="box-text">D-Day</p>
            <hr class="box-line" />
            <ul class="cal-day">
              <li v-for="(dDay, index) in dDays" :key="index">
                <strong>{{ dDay.title }}</strong
                >: {{ dDay.daysLeft }}일 남음
              </li>
            </ul>
          </div>

          <div class="box13">
            <p class="box-text">일정</p>
            <hr class="box-line" />
            <ul class="cal-day">
              <li v-for="event in eventsList" :key="event.title">
                <strong>{{ event.title }}</strong
                >: {{ event.period }}
              </li>
            </ul>
          </div>
        </div>
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
import { ref, defineComponent, onMounted, watch, computed } from "vue";
import Highcharts from "highcharts";
import Boost from "highcharts/modules/boost";
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import interactionPlugin from "@fullcalendar/interaction";
import axios from "axios";

Boost(Highcharts);

export default defineComponent({
  components: {
    FullCalendar,
  },
  setup() {
    const activeSection = ref("ai-analysis");
    const interviewReport = ref({});

    // Function to fetch interview report
    const fetchInterviewReport = async (id) => {
      try {
        // const response = await axios.get(`/mydream/interview/${id}`);
        const id = 1;
        const response = await axios.get(`${process.env.VUE_APP_BACK_END_URL}/interview?id=${id}`);
        interviewReport.value = response.data;
      } catch (error) {
        console.error("Error fetching interview report:", error);
      }
    };

    // Initialize data on component mount
    onMounted(async () => {
      await fetchInterviewReport(1);

      initHighcharts();
      initCustomPieChart();
      initDualChart();
      initSquareChart();

      const progressBars = document.querySelectorAll(".progress-bar");
      progressBars.forEach((bar) => {
        const value = bar.getAttribute("data-value");
        bar.style.width = value + "%";
      });
    });

    const activateSection = (sectionId, event) => {
      event.preventDefault();
      activeSection.value = sectionId;
    };

    const handleDateClick = (info) => {
      const title = prompt("Enter event title:");
      if (title) {
        calendarOptions.value.events.push({
          title: title,
          date: info.dateStr,
        });
      }
    };

    const calendarOptions = ref({
      plugins: [dayGridPlugin, interactionPlugin],
      initialView: "dayGridMonth",
      events: [
        { title: "삼성전자", start: "2024-07-01", end: "2024-07-05" },
        { title: "SK하이닉스", start: "2024-07-07", end: "2024-07-10" },
        { title: "ICT", date: "2024-07-31" },
      ],
      dateClick: handleDateClick,
    });

    const today = new Date();

    const eventsList = computed(() => {
      return calendarOptions.value.events.map((event) => {
        if (event.start && event.end) {
          return {
            title: event.title,
            period: `${event.start} - ${event.end}`,
          };
        }
        return {
          title: event.title,
          period: event.date,
        };
      });
    });

    const dDays = computed(() => {
      return calendarOptions.value.events
        .filter((event) => event.start || event.date)
        .map((event) => {
          let endDate;
          if (event.date) {
            endDate = new Date(event.date);
          } else if (event.end) {
            endDate = new Date(event.end);
          }
          const daysLeft = Math.ceil((endDate - today) / (1000 * 60 * 60 * 24));
          return { title: event.title, daysLeft };
        })
        .filter((dDay) => dDay.daysLeft >= 0);
    });

    watch(activeSection, (newValue) => {
      if (newValue === "schedule-management") {
        console.log("Schedule management section is now active");
      }
    });

    const initHighcharts = () => {
      Highcharts.chart("chart-1", {
        chart: {
          backgroundColor: null,
        },
        title: { text: "" },
        xAxis: {
          categories: ["Q1", "Q2", "Q3", "Q4", "Q5"],
        },
        yAxis: {
          type: "logarithmic",
          title: { text: "안정도" },
        },
        tooltip: {
          headerFormat: "<b>{series.name}</b><br />",
          pointFormat: "{point.y} million(s)",
        },
        series: [
          {
            name: "음성 분석",
            keys: ["y", "color"],
            data: [
              [16, "#0000ff"],
              [361, "#8d0073"],
              [1018, "#ba0046"],
              [2025, "#d60028"],
              [3192, "#eb0014"],
            ],
            color: {
              linearGradient: {
                x1: 0,
                x2: 0,
                y1: 1,
                y2: 0,
              },
              stops: [
                [0, "#0000ff"],
                [1, "#ff0000"],
              ],
            },
          },
        ],
        credits: { enabled: false },
      });
    };

    const initCustomPieChart = () => {
      Highcharts.chart("chart-2", {
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
              format: "<b>{point.name}</b><br>{point.percentage}%",
              distance: 20,
            },
          },
        },
        series: [
          {
            enableMouseTracking: false,
            animation: { duration: 2000 },
            colorByPoint: true,
            data: [
              { name: "졸림", y: 21.3 },
              { name: "화남", y: 18.7 },
              { name: "기쁨", y: 20.2 },
              { name: "슬픔", y: 14.2 },
              { name: "중립", y: 25.6 },
            ],
          },
        ],
        credits: { enabled: false },
      });
    };

    const initDualChart = () => {
      Highcharts.chart("chart-3", {
        chart: {
          zooming: { type: "xy" },
          backgroundColor: null,
        },
        title: { text: "", align: "center" },
        xAxis: [
          {
            categories: ["Q1", "Q2", "Q3", "Q4", "Q5"],
            crosshair: true,
          },
        ],
        yAxis: [
          {
            labels: {
              format: "{value}°C",
              style: { color: Highcharts.getOptions().colors[1] },
            },
            title: {
              text: "",
              style: { color: Highcharts.getOptions().colors[1] },
            },
          },
          {
            title: {
              text: "",
              style: { color: Highcharts.getOptions().colors[0] },
            },
            labels: {
              format: "{value} mm",
              style: { color: Highcharts.getOptions().colors[0] },
            },
            opposite: true,
          },
        ],
        series: [
          {
            name: "",
            type: "column",
            yAxis: 1,
            data: [45.7, 37.0, 28.9, 17.1, 39.2],
          },
          {
            name: "",
            type: "spline",
            data: [-11.4, -9.5, -14.2, 0.2, 7.0],
          },
        ],
        credits: { enabled: false },
      });
    };

    const initSquareChart = () => {
      Highcharts.chart("chart-5", {
        chart: {
          polar: true,
          backgroundColor: null,
        },
        title: { text: "" },
        pane: { startAngle: 0, endAngle: 360 },
        xAxis: {
          tickInterval: 45,
          min: 0,
          max: 360,
          labels: { format: "{value}°" },
        },
        yAxis: { min: 0 },
        plotOptions: {
          series: {
            pointStart: 0,
            pointInterval: 45,
          },
          column: {
            pointPadding: 0,
            groupPadding: 0,
            pointPlacement: "between",
          },
        },
        series: [
          {
            type: "column",
            name: "Column",
            data: [8, 7, 6, 5, 4, 3, 2, 1],
            pointPlacement: "between",
          },
          {
            type: "line",
            name: "Line",
            data: [1, 2, 3, 4, 5, 6, 7, 8],
          },
          {
            type: "area",
            name: "Area",
            data: [1, 8, 2, 7, 3, 6, 4, 5],
          },
        ],
        credits: { enabled: false },
      });
    };

    return {
      activeSection,
      activateSection,
      calendarOptions,
      handleDateClick,
      eventsList,
      dDays,
      interviewReport,
    };
  },
});
</script>
