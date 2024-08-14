<template>
  <div class="modal-overlay" @click="$emit('close', false)">
    <div class="reshduty-container" @click.stop>
      <!-- 종합 평가 -->
      <div class="reshduty-con row scrollable-div">
        <div class="reshduty-summary">
          <div class="reshduty-total-t row">
            <div class="reshduty-total-title col-1">종합 분석</div>
            <div class="reshduty-total-subtitle col-1">- 직무 면접</div>
          </div>

          <div class="reshduty-summary-right">
            <p>이름: {{ userName }}</p>
            <p>날짜: {{ formatDate(credt) }}</p>
          </div>
        </div>

        <div class="resduty-subtitle3 col-1">
          <div class="resduty-subtitlecon3">종합 평가</div>
        </div>

        <div class="resduty-qcon10">
          <div class="reshduty-totalcom">
            <div class="resduty-analyze6-con">
              <li>{{ efeed1 }}</li>
              <li>{{ efeed2 }}</li>
              <li>{{ pfeed1 }}</li>
              <li>{{ pfeed2 }}</li>
              <li>{{ vfeed1 }}</li>
              <li>{{ vfeed2 }}</li>
              <li>{{ sttfeed1 }}</li>
              <li>{{ sttfeed2 }}</li>
              <li>{{ cnsfeedbk }}</li>
            </div>
          </div>
        </div>

        <div class="resduty-subtitle3">
          <div class="resduty-subtitlecon3">표정 분석 / 자세 분석</div>
        </div>

        <div class="resduty-qcon5">
          <div class="resduty-face">
            <div id="face" style="width: 400px; height: 400px"></div>
          </div>
          <div class="resduty-face-com">
            <div id="barchart"></div>
          </div>
        </div>

        <div class="resduty-subtitle3">
          <div class="resduty-subtitlecon3">유사도 측정 / 음성 분석</div>
        </div>

        <div class="resduty-qcon8">
          <div class="resduty-similarbox">
            <figure class="highcharts-figure">
              <div id="wordcloud1"></div>
            </figure>
          </div>
          <div class="resduty-similarbox2">
            <figure class="highcharts-figure">
              <div id="voiceg"></div>
            </figure>
          </div>
        </div>
        <div class="resduty-qcon9">
          <div class="resduty-similartip">
            <img class="resduty-tipicon" src="\img\res_tip.png" />
            <div class="resduty-ti">
              회원님이 작성한 이력서의 기반으로 키워드를 추출하여 면접 내용과
              비교하여 유사도를 ...
            </div>
          </div>
        </div>

        <div class="reshduty-btncon row">
          <div
            @click="pagechage(1)"
            class="col-1"
            :class="{
              'reshduty-lbutton': true,
              'reshduty-active': activePage === 1,
            }"
          >
            Q1
          </div>
          <div
            @click="pagechage(2)"
            class="col-1"
            :class="{
              'reshduty-button': true,
              'reshduty-active': activePage === 2,
            }"
          >
            Q2
          </div>
          <div
            @click="pagechage(3)"
            class="col-1"
            :class="{
              'reshduty-button': true,
              'reshduty-active': activePage === 3,
            }"
          >
            Q3
          </div>
          <div
            @click="pagechage(4)"
            class="col-1"
            :class="{
              'reshduty-button': true,
              'reshduty-active': activePage === 4,
            }"
          >
            Q4
          </div>
          <div
            @click="pagechage(5)"
            class="col-1"
            :class="{
              'reshduty-button': true,
              'reshduty-active': activePage === 5,
            }"
          >
            Q5
          </div>
          <div
            @click="pagechage(6)"
            class="col-1"
            :class="{
              'reshduty-button': true,
              'reshduty-active': activePage === 6,
            }"
          >
            Q6
          </div>
          <div
            @click="pagechage(7)"
            class="col-1"
            :class="{
              'reshduty-button': true,
              'reshduty-active': activePage === 7,
            }"
          >
            Q7
          </div>
        </div>

        <!--면접질문-->
        <div class="row scrollable-div">
          <div class="reshduty-qcon">
            <div class="reshduty-q">
              <div class="reshduty-qnum">Q{{ i + 1 }}.</div>
              <div class="reshduty-question">{{ question }}</div>
            </div>
          </div>

          <div class="reshduty-qcontent1">
            <div class="reshduty-subtitle1 row">
              <div class="reshduty-subtitle2 col-1">면접 대답</div>
              <div class="reshduty-qcontent2">{{ answer }}</div>
            </div>
          </div>

          <div class="reshduty-qcon4" v-if="feedback[i]">
            <div class="reshduty-qcontent1">
              <div class="reshduty-subtitle1 row">
                <div class="reshduty-subtitle2 col-1">AI피드백</div>
                <div class="reshduty-qcontent2">{{ feedback }}</div>
              </div>
            </div>
          </div>
          <div
            class="reshduty-qcontent1"
            v-if="CTFeedback[i - 5] && (i === 5 || i === 6)"
          >
            <div class="reshduty-subtitle1 row">
              <div class="reshduty-subtitle2 col-1">컨설턴트 피드백</div>
              <div class="reshduty-qcontent2">{{ CTFeedback[i - 5] }}</div>
            </div>
          </div>
          <!-- <button v-if="page > 1" class="reshduty-nextbtn col-1" @click="Previous">이전 문항</button>
          <button class="reshduty-nextbtn col-1" @click="next">다음 문항</button> -->
        </div>

        <!-- <div class="reshduty-subtitle3 col-1">
          <div class="resh-subtitlecon3">컨설턴트 총평</div>
        </div>

        <div class="reshduty-qcon10">
          <div class="reshduty-totalcom">
            <div>
              ○ {{ cnsfeedbk }}
            </div>
          </div>
        </div> -->
        <div class="reshdudy-btnarea">
          <button
            v-if="page > 1"
            class="reshduty-nextbtn col-1"
            @click="Previous"
          >
            이전 문항
          </button>
          <button v-if="page < 7" class="reshduty-nextbtn col-1" @click="next">
            다음 문항
          </button>
          <button class="reshduty-homebtn col-1" @click="$emit('close', false)">
            닫기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import * as echarts from "echarts";
export default {
  props: {
    intno: {
      type: Number,
      required: true,
    },
  },
  created() {
    console.log("Received intno:", this.intno); // 콘솔에 찍기
  },
  data() {
    return {
      // mname: "",
      // resDate:"",
      // activePage: 1,
      // page: 1,
      // Question: [],
      // Comment: [],
      // feedback: [],
      // i: 0,
      // content: 1,
      // isReviewSubmitted: false,
      // AIFeedback: [],
      intno: this.$props.intno,
      activePage: 1,
      page: 1,
      question: "",
      answer: "",
      feedback: "",
      i: 0,
      content: 1,
      aifeedbk: "",
      cnsfeedbk: "",
      CTFeedback: [],
      userName: "",
      credt: "",
      pbadcnt: [],
      qnaData: [],
      qcnsfeedbk: "",
    };
  },
  methods: {
    fetchUserData() {
      axios
        .get(`${process.env.VUE_APP_BACK_END_URL}/itv/userData`, {
          params: { intno: this.intno },
        })
        .then((response) => {
          // const { mname, resDate } = response.data;
          // this.mname = response.data.mname[0];
          // this.resDate = response.data.resDate[0];
          // console.log(response.data)
          this.userName = response.data[0].mname;
          this.credt = response.data[0].credt;
          this.efeed1 = response.data[0].efeed1;
          this.efeed2 = response.data[0].efeed2;
          this.pfeed1 = response.data[0].pfeed1;
          this.pfeed2 = response.data[0].pfeed2;
          this.vfeed1 = response.data[0].vfeed1;
          this.vfeed2 = response.data[0].vfeed2;
          this.sttfeed1 = response.data[0].sttfeed1;
          this.sttfeed2 = response.data[0].sttfeed2;
          this.pbadcnt = response.data.pbadcnt;
          this.cnsfeedbk = response.data[0].cnsfeedbk;
          console.log(this.cnsfeedbk);
        });
    },
    formatDate(datetime) {
      // 'T'를 기준으로 문자열을 나누고 첫 번째 부분을 반환합니다.
      return datetime.split("T")[0];
    },
    fetchQuestionData() {
      //   axios.get(`${process.env.VUE_APP_BACK_END_URL}/itv/fetchData`, { params: { intno: this.intno } })
      // .then(response => {
      //   // const { question, comment, feedback } = response.data;
      //   // this.Question = question || []; // Default to empty array if no data
      //   // this.Comment = comment || [];
      //   // this.feedback = feedback || [];
      //   console.log("여기다+"+response.data[0]);
      //   //this.qnaData = response.data;
      //   this.question = response.data[`${this.i}`].QUESTION;
      //   this.answer = response.data[`${this.i}`].ANSWER;
      //   this.feedback = response.data[`${this.i}`].AIFEEDBK;
      // })
      axios
        .get(`${process.env.VUE_APP_BACK_END_URL}/itv/fetchData`, {
          params: { intno: this.intno },
        })
        .then((response) => {
          // const { question, comment, feedback } = response.data;
          // this.Question = question || []; // Default to empty array if no data
          // this.Comment = comment || [];
          // this.feedback = feedback || [];
          console.log("여기다+" + response.data);
          this.qnaData = response.data;
          this.question = response.data[`${this.i}`].QUESTION;
          this.answer = response.data[`${this.i}`].ANSWER;
          this.feedback = response.data[`${this.i}`].AIFEEDBK;

          console.log(this.qnaData[2]);
          console.log(this.question);
          console.log(this.answer);
        })
        .catch((error) => {
          console.error("서버 오류:", error);
        });
    },
    // fetchAIFeedback() {
    //     axios.get(`${process.env.VUE_APP_BACK_END_URL}/itv/fetchAIFeedback`)
    //       .then(response => {
    //         // 서버에서 받은 데이터로 AIFeedback 변수 업데이트
    //         this.AIFeedback = response.data;
    //       })
    //       .catch(error => {
    //         console.error('서버 오류:', error);
    //       });
    //   },
    fetchCTFeedback() {
      axios
        .get(`${process.env.VUE_APP_BACK_END_URL}/itv/fetchCTFeedback`, {
          params: { intno: this.intno },
        })
        .then((response) => {
          this.CTFeedback = response.data;
        })
        .catch((error) => {
          console.error("서버 오류:", error);
        });
    },
    // scrollToTop() {
    //   const container = this.$el.querySelector('.reshduty-con.scrollable-div');
    //   container.scrollTo({
    //     top: 0,
    //     behavior: 'smooth'
    //   });
    // },
    next() {
      this.pagechage(this.page + 1);
      this.activePage = this.page;
      // this.scrollToTop();
    },
    Previous() {
      this.pagechage(this.page - 1);
      this.activePage = this.page;
      // this.scrollToTop();
    },
    // pagechage(num) {
    //   console.log(num);
    //   this.page = num;
    //   this.activePage = num;
    //   this.i = num - 1;
    //   if (num === 8) {
    //     this.content = 8;
    //   } else {
    //     this.content = 1;
    //   }
    // },
    // pagechage(num) {
    //   console.log(num);

    //   this.page = num;
    //   this.activePage = num;
    //   this.i = num - 1;
    //   this.question = this.qnaData[`${num-1}`].QUESTION;
    //   this.answer = this.qnaData[`${num-1}`].ANSWER;
    //   this.feedback = this.qnaData[`${num-1}`].AIFEEDBK;
    //   this.qcnsfeedbk = this.qnaData[`${num-1}`].QCNSFEEDBK;
    //   // console.log(question)
    //   if (num === 8) {
    //     this.content = 8;
    //   } else {
    //     this.content = 1;
    //   }
    // },
    pagechage(num) {
      console.log(num);

      this.page = num;
      this.activePage = num;
      this.i = num - 1;

      this.question =
        this.qnaData[`${num - 1}`]?.QUESTION || "저장된 값이 없습니다";
      this.answer =
        this.qnaData[`${num - 1}`]?.ANSWER || "저장된 값이 없습니다";
      this.feedback =
        this.qnaData[`${num - 1}`]?.AIFEEDBK || "저장된 값이 없습니다";
      this.qcnsfeedbk =
        this.qnaData[`${num - 1}`]?.QCNSFEEDBK || "저장된 값이 없습니다";

      if (num === 8) {
        this.content = 8;
      } else {
        this.content = 1;
      }
    },
    displayPage(pageNum) {
      return this.content === pageNum
        ? { display: "block" }
        : { display: "none" };
    },
    submitReview() {
      this.isReviewSubmitted = true;
    },
    face() {
      const chartContainer = document.getElementById("face");
      chartContainer.style.width = "300px";
      chartContainer.style.height = "400px";

      var myChart = echarts.init(chartContainer);

      axios
        .get(`${process.env.VUE_APP_BACK_END_URL}/itv/faceData`, {
          params: { intno: this.intno },
        })
        .then(function (response) {
          console.log([response.data]); // 응답 데이터 확인

          let option = {
            title: {
              text: "감정 점수",
            },

            tooltip: {
              trigger: "axis",
            },
            legend: {
              left: "left",
              data: ["점"],
            },
            radar: [
              {
                indicator: [
                  { text: "Q1", max: 100 },
                  { text: "Q2", max: 100 },
                  { text: "Q3", max: 100 },
                  { text: "Q4", max: 100 },
                  { text: "Q5", max: 100 },
                  { text: "Q6", max: 100 },
                  { text: "Q7", max: 100 },
                ],
                center: ["50%", "50%"],
                radius: 80,
              },
            ],
            series: [
              {
                type: "radar",
                tooltip: {
                  trigger: "item",
                },
                areaStyle: {
                  color: "#08AD94", // 영역 색상 변경
                },
                itemStyle: {
                  color: "#0A6065", // 데이터 포인트 색상 변경
                },
                data: [
                  {
                    value: response.data, // [60, 73, 85, 40, 65,75]
                    name: "감정 점수",
                  },
                ],
              },
            ],
          };
          myChart.setOption(option);
        })
        .catch(function (error) {
          console.error("서버 오류:", error);
        });
    },

    barchart() {

      axios
        .get(`${process.env.VUE_APP_BACK_END_URL}/itv/barData`, {
          params: { intno: this.intno },
        })
        .then(function (response) {
          Highcharts.chart("barchart", {
            chart: {
              type: "column",
              height: "380px",
            },
            title: {
              text: "자세 흐트러짐",
              align: "left",
            },
            subtitle: {
              text: "",
              align: "left",
            },
            xAxis: {
              categories: ["Q1", "Q2", "Q3", "Q4", "Q5", "Q6", "Q7"],
              crosshair: true,
              accessibility: {
                description: "Countries",
              },
            },
            yAxis: {
              min: 0,
              title: {
                text: "Count",
              },
            },
            tooltip: {
              valueSuffix: "(회)",
            },
            plotOptions: {
              column: {
                pointPadding: 0.2,
                borderWidth: 0,
              },
            },
            series: [
              {
                name: response.data.chartname,
                data: response.data.values,
                color: "#08AD94",
              },
            ],
          });
        });
    },
    voiceg() {
      axios
        .get(`${process.env.VUE_APP_BACK_END_URL}/itv/voicegData`)
        .then(function (response) {
          Highcharts.chart("voiceg", {
            chart: {
              height: 380,
              width: 470,
            },

            title: {
              text: "음성 떨림",
              align: "left",
            },
            subtitle: {
              text: "",
              align: "left",
            },
            yAxis: {
              title: {
                text: response.data.chartname,
              },
            },
            xAxis: {
              title: {
                text: "(초)",
              },
              accessibility: {
                rangeDescription: "(초)",
              },
            },
            legend: {
              layout: "vertical",
              align: "right",
              verticalAlign: "middle",
            },
            plotOptions: {
              series: {
                label: {
                  connectorAllowed: false,
                },
                pointStart: 0,
                pointInterval: 10,
              },
            },
            series: [
              {
                name: "Q1",
                data: response.data.q1,
              },
              {
                name: "Q2",
                data: response.data.q2,
              },
              {
                name: "Q3",
                data: response.data.q3,
              },
              {
                name: "Q4",
                data: response.data.q4,
              },
              {
                name: "Q5",
                data: response.data.q5,
              },
              {
                name: "Q6",
                data: response.data.q5,
              },
              {
                name: "Q7",
                data: response.data.q5,
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
        });
    },
    wordcloud() {
      axios
        .get(`${process.env.VUE_APP_BACK_END_URL}/itv/wordCloud`, {
          params: { intno: this.intno },
        })
        .then(function (response) {
          const text = response.data,
            // 특수문자와 숫자 제거 후 단어로 분할
            lines = text.replace(/[():'?0-9]+/g, "").split(/[,\. ]+/g),
            // 단어의 빈도를 계산
            data = lines.reduce((arr, word) => {
              let obj = Highcharts.find(arr, (obj) => obj.name === word);
              if (obj) {
                obj.weight += 1;
              } else {
                obj = {
                  name: word,
                  weight: 1,
                };
                arr.push(obj);
              }
              return arr;
            }, []);

          // 빈도에 따라 내림차순으로 정렬 후 상위 100개의 단어만 선택
          const top100 = data.sort((a, b) => b.weight - a.weight).slice(0, 90);

          // Highcharts를 이용해 워드클라우드 생성
          Highcharts.chart("wordcloud1", {
            chart: {
              height: 380,
              width: 470,
            },
            accessibility: {
              screenReaderSection: {
                beforeChartFormat:
                  "<h5>{chartTitle}</h5><div>{chartSubtitle}</div><div>{chartLongdesc}</div><div>{viewTableButton}</div>",
              },
            },
            series: [
              {
                type: "wordcloud",
                data: top100,
                name: "Occurrences",
              },
            ],
            title: {
              text: "Top 100 Wordcloud",
            },
          });
        })
        .catch(function (error) {
          console.error("Error fetching word cloud data:", error);
        });
    },
  },
  mounted() {
    // this.face();
    // this.wordcloud();
    // this.barchart();
    // this.voiceg();
    // this.fetchQuestionData();
    // this.fetchAIFeedback();
    this.fetchCTFeedback();
    // this.fetchUserData();
    this.wordcloud();
    this.barchart();
    this.face();
    this.voiceg();
    this.fetchQuestionData();
    this.fetchUserData();
  },
};
</script>

<style scoped>
/* Add your styles here */
</style>
