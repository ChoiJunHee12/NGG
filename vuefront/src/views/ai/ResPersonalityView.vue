<template>
  <div class="res-container">
    <div class="res-btncon row">
      <div @click="pagechage(1)" class="col-1" :class="{ 'res-lbutton': true, 'res-qactive': nownum === 1 }" > Q1 </div>
      <div @click="pagechage(2)" class="col-1" :class="{ 'res-button': true, 'res-qactive': nownum === 2 }" > Q2 </div>
      <div @click="pagechage(3)" class="col-1" :class="{ 'res-button': true, 'res-qactive': nownum === 3 }" > Q3 </div>
      <div @click="pagechage(4)" class="col-1" :class="{ 'res-button': true, 'res-qactive': nownum === 4 }" > Q4 </div>
      <div @click="pagechage(5)" class="col-1" :class="{ 'res-button': true, 'res-qactive': nownum === 5 }" > Q5 </div>
      <div @click="pagechage(6)" class="col-1" :class="{ 'res-rbutton': true, 'res-qactive': nownum === 6 }" > 종합 평가 </div>
    </div>
    <div :style="displayPage(1)" class="res-con row">
      <div class="res-qcon">
        <div class="res-q">
          <div class="res-qnum">Q{{ i + 1 }}.</div>
          <div class="res-question">{{ this.question[i] }}</div>
        </div>
      </div>
      <div class="res-qcon2">
        <video class="res-video" controls :key="i">
          <source :src="video_url[i]" type="video/webm" />
        </video>

        <div class="res-dgraph">
          <div id="graph2"></div>
          <!-- <div class="res-stress">스트레스율 : {{q1detail.emotion.escore}}(%)</div> -->
        </div>
      </div>
      <div class="res-qcon3">
        <div class="res-dcontent row">
          <div class="res-vcon col-1">
            <img
              class="res-voicew"
              v-if="voice[i]"
              :src="voice[i]['vhertzimg']"
            />
          </div>
          <div class="res-voice col-2">
            <div class="res-voice1" v-if="voice[i]">
              음성 안정도 : {{ voice[i]["vscore"] }}
            </div>
            <div class="res-voice1" v-if="voice[i]">
              공백 시간 : {{ voice[i]["vempty"] }}초
            </div>
          </div>
        </div>
      </div>
      <div class="res-qcon4">
        <div class="res-qcontent1">
          <div class="res-subtitle1 row">
            <div class="res-subtitle2 col-1">면접 대답</div>
            <div class="res-qcontent2">{{ this.stt[i] }}</div>
          </div>
        </div>
      </div>
      <div class="res-qcon4" v-if="feedback[i]">
        <div class="res-qcontent1">
          <div class="res-subtitle1 row">
            <div class="res-subtitle2 col-1">피드백</div>
            <div class="res-qcontent2">{{ feedback[i] }}</div>
          </div>
        </div>
      </div>
      <button v-if="page > 1" class="res-nextbtn col-1" @click="Previous">
        이전 문항
      </button>
      <button class="res-nextbtn col-1" @click="next">다음 문항</button>
    </div>

    <!--          종합 평가                       -->
    <div :style="displayPage(6)" class="res-con row">
      <div class="resduty-summary">
        <div class="resduty-total-t row">
          <div class="resduty-total-title col-1">종합 분석</div>
          <div class="resduty-total-subtitle col-1">- 인성 면접</div>
        </div>

        <div class="resduty-summary-right">
          <p>이름: {{this.mname}}</p>
          <p>날짜: {{this.resdate}}</p>
        </div>
      </div>

      <div class="res-subtitle3 col-1">
        <div class="res-subtitlecon3">종합 평가</div>
      </div>
      <hr />
      <div class="res-qcon10">
        <div class="res-totalcom">
          <div class="res-analyze6-con">○ {{ efinalcmt[0] }}</div>
          <div class="res-analyze6-con">○ {{ efinalcmt[1] }}</div>
          <div class="res-analyze6-con">○ {{ pfinalcmt[0] }}</div>
          <div class="res-analyze6-con">○ {{ pfinalcmt[1] }}</div>
          <div class="res-analyze6-con">○ {{ vfinalcmt[0] }}</div>
          <div class="res-analyze6-con">○ {{ vfinalcmt[1] }}</div>
          <div class="res-analyze6-con">○ {{ sttfinalcmt.sttresult1 }}</div>
          <div class="res-analyze6-con">○ {{ sttfinalcmt.sttresult2 }}</div>
        </div>
      </div>
      <div class="res-subtitle3">
        <div class="res-subtitlecon3">표정 분석 / 자세 분석</div>
      </div>
      <hr />
      <div class="res-qcon5">
        <div class="res-face">
          <div id="face" style="width: 400px; height: 400px"></div>
        </div>
        <div class="res-face-com">
          <div id="barchart"></div>
        </div>
      </div>
      <div class="res-subtitle3">
        <div class="res-subtitlecon3">유사도 측정 / 음성 분석</div>
      </div>
      <hr />
      <div class="res-qcon8">
        <div class="res-similarbox">
          <figure class="highcharts-figure">
            <div id="wordcloud1"></div>
          </figure>
        </div>
        <div class="res-similarbox2">
          <figure class="highcharts-figure">
            <div id="voiceg"></div>
          </figure>
        </div>
      </div>
      <div class="res-qcon9">
        <div class="res-similartip">
          <img class="res-tipicon" src="\img\res_tip.png" />
          <div class="res-ti">
            면접에서 사용하신 단어를 시각적으로 표현해 드립니다.

        </div>
      </div>
    </div>

      <router-link to="/Main"
        ><button class="res-homebtn col-1">홈으로 돌아가기</button></router-link
      >
    </div>
  </div>
</template>
<script>
import axios from "axios";
import * as echarts from "echarts";
export default {
  data() {
    return {
      page: 1,
      q1detail: JSON.parse(localStorage.getItem("q4detail")),
      detail: [],
      question: [],
      i: 0,
      content: 1,
      nownum: 1,
      efinalcmt: [],
      pfinalcmt: [],
      vfinalcmt: [],
      sttfinalcmt: [],
      detailvo: {},
      stt: [],
      emotion: [],
      position: [],
      voice: [],
      video_url: [],
      feedback: [],
      detailvoList: [],      
      mname: "",
      resdate: "",
    };
  },
  methods: {
    next() {
      this.pagechage(this.page + 1);
    },
    Previous() {
      this.pagechage(this.page - 1);
    },
    pagechage(num) {
      this.page = num;
      this.i = num - 1;
      if (num === 6) {
        this.content = 6;
      } else {
        this.content = 1;
      }
      this.nownum = num;
      window.scrollTo({
        top: 0,
        behavior: "smooth", // 부드러운 스크롤을 위해 'smooth' 옵션 사용
      });
      if (num<5){
        this.graph2(num);
      }
      
    },
    displayPage(pageNum) {
      return this.content === pageNum
        ? { display: "block" }
        : { display: "none" };
    },
    graph2(num) {
      const i=num-1;    
      // 임시 데이터
      const data = [];
      const time = new Date().getTime(); // 현재 시간

      // emotion 객체의 키를 가져오고 정렬합니다.
      const emotionKeys = Object.keys(this.emotion[i])
        .filter((key) => key.endsWith("sec"))
        .sort((a, b) => parseInt(a) - parseInt(b));
      const numPoints = emotionKeys.length;

      emotionKeys.forEach((key, index) => {
        const y = this.emotion[i][key];
        data.push({
          x: index * 90000, // x축의 값은 0초부터 시작
          y: y,
        });
      });    
      Highcharts.chart("graph2", {
        chart: {
          type: "spline",
          height: 320, // Height of the chart
          width: 480, // Width of the chart
        },

        title: {
          text: `스트레스율 : ${this.detailvoList[i].escore}%`,
          align: "center",
        },

        subtitle: {
          text: `긍정: ${this.emotion[i].ecntgood}초, 중립: ${this.emotion[i].ecntsoso}초, 부정: ${this.emotion[i].ecntbad}초`,
          align: "center",
        },

        xAxis: {
          type: "datetime",
          title: {
            text: "시간",
          },
          min: 0, // x축의 시작점을 0으로 설정
          labels: {
            formatter: function () {
              const seconds = Math.round(this.value / 90000); // 90,000 milliseconds = 1 minute 30 seconds
              return `${seconds}초`; // Label formatting to show '0sec', '1sec', etc.
            },
          },
        },

        yAxis: [
          {
            title: {
              text: "감정 인식",
            },
            min: -1,
            max: 1,
            tickPositions: [-1, 0, 1],
            labels: {
              formatter: function () {
                return this.value === -1
                  ? "부정"
                  : this.value === 0
                  ? "중립"
                  : this.value === 1
                  ? "긍정"
                  : "";
              },
            },
          },
        ],

        plotOptions: {
          series: {
            animation: {
              duration: 1000,
            },
            marker: {
              enabled: false,
            },
            lineWidth: 3,
          },
        },

        series: [
          {
            name: "감정 라인",
            data: data,
          },
        ],

        responsive: {
          rules: [
            {
              condition: {
                maxWidth: 300,
              },
              chartOptions: {
                yAxis: [
                  {
                    tickAmount: 2,
                    title: {
                      x: 15,
                      reserveSpace: false,
                    },
                  },
                ],
              },
            },
          ],
        },
      });
    },

    voiceg() {
      Highcharts.chart("voiceg", {
        chart: {
          height: 380, // Height of the chart
          width: 470, // Width of the chart
        },
        title: {
          text: "음성 데이터",
          align: "left",
        },

        subtitle: {
          text: "",
          align: "left",
        },

        yAxis: {
          title: {
            text: "",
          },
        },

        xAxis: {
          title: {
            text: "(문제)",
          },
          accessibility: {
            rangeDescription: "(문제)",
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
            pointStart: 1,
            pointInterval: 1,
          },
        },

        series: [
          {
            name: "vhertz",
            data: [
              this.detailvoList[0].vhertz,
              this.detailvoList[1].vhertz,
              this.detailvoList[2].vhertz,
              this.detailvoList[3].vhertz,
              this.detailvoList[4].vhertz,
            ],
          },
          {
            name: "vjitter",
            data: [
              this.detailvoList[0].vjitter,
              this.detailvoList[1].vjitter,
              this.detailvoList[2].vjitter,
              this.detailvoList[3].vjitter,
              this.detailvoList[4].vjitter,
            ],
          },
          {
            name: "vspeed",
            data: [
              this.detailvoList[0].vspeed,
              this.detailvoList[1].vspeed,
              this.detailvoList[2].vspeed,
              this.detailvoList[3].vspeed,
              this.detailvoList[4].vspeed,
            ],
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
    },

    face() {
      const chartContainer = document.getElementById("face");
      chartContainer.style.width = "300px";
      chartContainer.style.height = "380px";

      // ECharts 초기화
      var myChart = echarts.init(chartContainer);
      var option = {
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
            areaStyle: {},
            data: [
              {
                value: [
                  this.detailvoList[0].escore,
                  this.detailvoList[1].escore,
                  this.detailvoList[2].escore,
                  this.detailvoList[3].escore,
                  this.detailvoList[4].escore,
                ],
                name: "감정 점수",
              },
            ],
          },
        ],
      };
      myChart.setOption(option);
    },
    barchart() {
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
          text: "고개, 어깨가 정자세에 어긋날 때 마다 1회 증가합니다",
          align: "left",
        },
        xAxis: {
          categories: ["Q1", "Q2", "Q3", "Q4", "Q5"],
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
            name: "자세 흐트러짐 count",
            data: [
              this.detailvoList[0].pbadcnt,
              this.detailvoList[1].pbadcnt,
              this.detailvoList[2].pbadcnt,
              this.detailvoList[3].pbadcnt,
              this.detailvoList[4].pbadcnt,
            ],
            color: "#A1C5FB",
          },
        ],
      });
    },
    wordcloud() {
      const text =
          this.detailvoList[0].answer + " " +
          this.detailvoList[1].answer + " " +
          this.detailvoList[2].answer + " " +
          this.detailvoList[3].answer + " " +
          this.detailvoList[4].answer,
        lines = text.replace(/[():'?0-9]+/g, "").split(/[,\. ]+/g),
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

      Highcharts.chart("wordcloud1", {
        chart: {
          height: 380,
          width: 470,
        },
        accessibility: {
          screenReaderSection: {
            beforeChartFormat:
              "<h5>{chartTitle}</h5>" +
              "<div>{chartSubtitle}</div>" +
              "<div>{chartLongdesc}</div>" +
              "<div>{viewTableButton}</div>",
          },
        },
        series: [
          {
            type: "wordcloud",
            data,
            name: "Occurrences",
          },
        ],
        title: {
          text: "답변 반복 핵심 키워드",
          align: "left",
        },
        subtitle: {
          text: "",
          align: "left",
        },
        tooltip: {
          headerFormat:
            '<span style="font-size: 16px"><b>{point.key}</b>' + "</span><br>", //커서 내용
        },
      });
    },
    fetchData() {
      const details = ["q1detail", "q2detail", "q3detail", "q4detail", "q5detail"];
      details.forEach((key) => {
        const storedDetail = JSON.parse(localStorage.getItem(key));

        this.stt.push(storedDetail.answer || "");
        this.emotion.push(storedDetail.emotion || { escore: 0 });
        this.position.push(storedDetail.position || { pscore: 0 });
        this.voice.push(storedDetail.voice || { vscore: 0 });
        this.video_url.push(storedDetail.video_url || "");
        this.feedback.push(storedDetail.aifeedback || "");
      });

      // 반복문을 통해 detailvo 객체를 생성하고 detailvoList에 추가
      for (let i = 0; i < 5; i++) {
        this.detailvo = {
          qno: i + 1,
          aiqno: i <= 4 ? JSON.parse(localStorage.getItem("questionlist"))[i].totalqno : null,
          cnsqno: i > 4 ? JSON.parse(localStorage.getItem("questionlist"))[i].totalqno : null,
          question: JSON.parse(localStorage.getItem("questionlist"))[i].totalq,
          answer: this.stt[i],
          ecntgood: this.emotion[i].ecntgood,
          ecntsoso: this.emotion[i].ecntsoso,
          ecntbad: this.emotion[i].ecntbad,
          pbadcnt: this.position[i].pbadcnt,
          vhertz: this.voice[i].vhertz,
          vjitter: this.voice[i].vjitter,
          vspeed: this.voice[i].vspeed,
          aifeedbk: this.feedback[i],
          escore: this.emotion[i].escore,
          pscore: this.position[i].pscore,
          vscore: this.voice[i].vscore,
        };

        this.detailvoList.push(this.detailvo);
        this.question.push(this.detailvo.question);
      }
      console.log("this.detailvoList => ", this.detailvoList);
      console.log("this.question => ", this.question);

      this.efinalcmt = JSON.parse(localStorage.getItem("efinalcmt"));
      this.pfinalcmt = JSON.parse(localStorage.getItem("pfinalcmt"));
      this.vfinalcmt = JSON.parse(localStorage.getItem("vfinalcmt"));
      this.sttfinalcmt = JSON.parse(localStorage.getItem("sttfinalcmt"));
    },
  },
  mounted() {
    window.scrollTo(0, 0);

    this.fetchData();
    this.graph2(1);
    this.wordcloud();
    this.barchart();
    this.$nextTick(() => {
      this.face();
    });
    this.voiceg();
    axios.get(`${process.env.VUE_APP_BACK_END_URL}/interview/getresnametime?memno=${localStorage.getItem("memno")}`)
    .then((res) => {
        console.log(res.data.mname);
        console.log(res.data.resdate);
        this.mname = res.data.mname;
        this.resdate = res.data.resdate;
    })
  },
  beforeUnmount() {
    localStorage.removeItem('q1detail');
    localStorage.removeItem('q2detail');
    localStorage.removeItem('q3detail');
    localStorage.removeItem('q4detail');
    localStorage.removeItem('q5detail');
    localStorage.removeItem('inttypecd');
    localStorage.removeItem('inttypename');
    localStorage.removeItem('questionlist');
    localStorage.removeItem('pfinalcmt');
    localStorage.removeItem('efinalcmt');
    localStorage.removeItem('vfinalcmt');
    localStorage.removeItem('sttfinalcmt');    
  },
};
</script>