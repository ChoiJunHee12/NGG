<template>
<div class="modal-overlay" @click="$emit('close',false)">
    <div class="resh-container" @click.stop>

    
    <div :style="displayPage(1)" class="resh-con row scrollable-div">
          <!--          종합 평가                       -->  
    <div class=" row scrollable-div displayPage6">
        
        <div class="reshduty-summary">
            <div class="reshduty-total-t row">
                <div class="reshduty-total-title col-1">종합 분석</div>
            <div class="reshduty-total-subtitle col-1">- 인성 면접</div>
          </div>

          <div class="reshduty-summary-right">
            <p>이름: {{mname}}</p>
            <p>날짜: {{resDate}}</p>
        </div>
            
        </div>


        <div class="res-subtitle3 col-1"><div class="res-subtitlecon3">종합 평가</div></div>
        <hr>
        <div class="resduty-qcon10">
          <div class="reshduty-totalcom">
            <div v-for="(feedback, index) in AIFeedback" :key="index" class="resduty-analyze6-con">
              ○ {{ feedback }}
            </div>
          </div>
        </div>


        </div>
        <div class="res-subtitle3"><div class="res-subtitlecon3">표정 분석 / 자세 분석</div></div>
        <hr>
        <div class="res-qcon5">
            <div class="res-face"> 
                <div id="face" style="width: 400px;height:400px;"></div>

            </div>
            <div class="res-face-com">
                <div id="barchart"></div>
        </div>
        </div>
        <div class="res-subtitle3"><div class="res-subtitlecon3">유사도 측정  /  음성 분석</div></div>
        <hr>
        <div class="res-qcon8">
            
            
            <div class="res-similarbox">
                <figure class="highcharts-figure">
                <div id="wordcloud1">

                </div>
            </figure>
            </div>
            <div class="res-similarbox2">
                <figure class="highcharts-figure">
                <div id="voiceg">
                </div>
            </figure>
            </div>
        </div>
        <div class="res-qcon9">
            <div class="res-similartip">
                <img class="res-tipicon" src="\img\res_tip.png">
                <div class="res-ti">회원님이 작성한 이력서의 기반으로 키워드를 추출하여 면접 내용과 비교하여 유사도를 ...</div>
            </div>
        </div>

    <div class="resh-btncon row">
        <div @click="pagechage(1)" class="col-1" :class="{'resh-lbutton':true, 'personalactive': activePage === 1}">Q1</div>
        <div @click="pagechage(2)" class="col-1" :class="{'resh-button':true, 'personalactive': activePage === 2}">Q2</div>
        <div @click="pagechage(3)" class="col-1" :class="{'resh-button':true, 'personalactive': activePage === 3}">Q3</div>
        <div @click="pagechage(4)" class="col-1" :class="{'resh-button':true, 'personalactive': activePage === 4}">Q4</div>
        <div @click="pagechage(5)" class="col-1" :class="{'resh-button':true, 'personalactive': activePage === 5}">Q5</div>
        <!-- <div @click="pagechage(6)" class="col-1" :class="{'resh-rbutton':true, 'personalactive': activePage === 6}">종합 평가</div> -->
    </div>

    <!--면접질문-->
    <div class="displayPage-p">
        <div class="resh-qcon">
            <div class="res-subtitle3 col-1"><div class="res-subtitlecon3">

                Q{{ i+1 }}. {{ Question[i] }}</div>
        </div>
    </div>
        <div class="resh-qcon4">
            <div class="resh-qcontent1">
                <div class="resh-subtitle1 row">
                    <div class="resh-subtitle2 col-1">면접 대답</div>
                    <div class="resh-qcontent2">{{this.Comment[i]}}</div>
                </div>
            </div>
        </div>
        <div class="resh-qcon4" v-if="feedback[i]">
            <div class="resh-qcontent1">
                <div class="resh-subtitle1 row">
                    <div class="resh-subtitle2 col-1">피드백</div>
                    <div class="resh-qcontent2">{{feedback[i]}}</div>
                </div>
            </div>
        </div>
        </div>
    </div>
        <button v-if="page>1" class="resh-nextbtn col-1" @click="Previous">이전 문항</button>
        <button v-if='page<5' class="resh-nextbtn col-1" @click="next">다음 문항</button>
        <button class="resh-homebtn col-1" @click="$emit('close',false)">닫기</button>
    </div>




    </div>

</template>
<script>
import axios from 'axios';
import * as echarts from 'echarts';
export default {
  data() {
    return {
      mname: "",
      resDate:"",
      activePage: 1,
      page: 1,
      Question: [],
      Comment: [],
      feedback: [],
      i: 0,
      content: 1,
      isReviewSubmitted: false,
      AIFeedback: [],
      CTFeedback: [],
    };
  },
  methods: {
    fetchUserData(){
      axios.get(`${process.env.VUE_APP_BACK_END_URL}/itv/userData`)
    .then(response => {
      const { mname, resDate } = response.data;
      this.mname = response.data.mname[0];
      this.resDate = response.data.resDate[0];
      console.log(response.data)
    })
    },
    fetchQuestionData() {
  axios.get(`${process.env.VUE_APP_BACK_END_URL}/itv/fetchData`)
    .then(response => {
      const { question, comment, feedback } = response.data;
      this.Question = question || []; // Default to empty array if no data
      this.Comment = comment || [];
      this.feedback = feedback || [];
      console.log(response.data)
    })
    .catch(error => {
      console.error('서버 오류:', error);
    });
},
fetchAIFeedback() {
    axios.get(`${process.env.VUE_APP_BACK_END_URL}/itv/fetchAIFeedback`)
      .then(response => {
        // 서버에서 받은 데이터로 AIFeedback 변수 업데이트
        this.AIFeedback = response.data;
      })
      .catch(error => {
        console.error('서버 오류:', error);
      });
  },
  fetchCTFeedback(){
    axios.get(`${process.env.VUE_APP_BACK_END_URL}/itv/fetchCTFeedback`)
      .then(response => {
        this.CTFeedback = response.data;
      })
      .catch(error => {
        console.error('서버 오류:', error);
      });
  },
    scrollToTop() {
      const container = this.$el.querySelector('.reshduty-con.scrollable-div');
      container.scrollTo({
        top: 0,
        behavior: 'smooth'
      });
    },
    next() {
      this.pagechage(this.page + 1);
      this.activePage = this.page;
      this.scrollToTop();
    },
    Previous() {
      this.pagechage(this.page - 1);
      this.activePage = this.page;
      this.scrollToTop();
    },
    pagechage(num) {
      console.log(num);
      this.page = num;
      this.activePage = num;
      this.i = num - 1;
      if (num === 8) {
        this.content = 8;
      } else {
        this.content = 1;
      }
    },
    displayPage(pageNum) {
      return this.content === pageNum ? { display: 'block' } : { display: 'none' };
    },
    submitReview() {
      this.isReviewSubmitted = true;
    },
    face() {
  const chartContainer = document.getElementById('face');
  chartContainer.style.width = '300px';
  chartContainer.style.height = '400px';

  var myChart = echarts.init(chartContainer);

  axios.get(`${process.env.VUE_APP_BACK_END_URL}/itv/faceData`)
    .then(function(response){
      console.log(response.data); // 응답 데이터 확인

      let faceData = response.data; // response.data가 faceData임을 가정
      let option = {
        title: {
          text: faceData.chartname // 감정 점수
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          left: 'left',
          data: ['점']
        },
        radar: [
          {
            indicator: [
              { text: 'Q1', max: 100 },
              { text: 'Q2', max: 100 },
              { text: 'Q3', max: 100 },
              { text: 'Q4', max: 100 },
              { text: 'Q5', max: 100 }
            ],
            center: ['50%', '50%'],
            radius: 80
          }
        ],
        series: [
          {
            type: 'radar',
            tooltip: {
              trigger: 'item'
            },
            areaStyle: {},
            data: [
              {
                value: [faceData.values[0], faceData.values[1],faceData.values[2],faceData.values[3],faceData.values[4],],// [60, 73, 85, 40, 65]
                name: faceData.chartname // 감정 점수
              }
            ]
          }
        ]
      };
      myChart.setOption(option);
    })
    .catch(function(error) {
      console.error('서버 오류:', error);
    });
},

    barchart() {
      axios.get(`${process.env.VUE_APP_BACK_END_URL}/itv/barData`)
    .then(function(response){
      Highcharts.chart('barchart', {
        chart: {
          type: 'column',
          height: '380px'
        },
        title: {
          text: '자세 흐트러짐',
          align: 'left'
        },
        subtitle: {
          text: '',
          align: 'left'
        },
        xAxis: {
          categories: ['Q1', 'Q2', 'Q3', 'Q4', 'Q5'],
          crosshair: true,
          accessibility: {
            description: 'Countries'
          }
        },
        yAxis: {
          min: 0,
          title: {
            text: 'Count'
          }
        },
        tooltip: {
          valueSuffix: '(회)'
        },
        plotOptions: {
          column: {
            pointPadding: 0.2,
            borderWidth: 0
          }
        },
        series: [
          {
            name: response.data.chartname,
            data: response.data.values,

          }
        ]
      })
    })
    },
    voiceg() {
      axios.get(`${process.env.VUE_APP_BACK_END_URL}/itv/voicegData`)
    .then(function(response){
      Highcharts.chart('voiceg', {
        chart: {
          height: 380,
          width: 470
        },
        title: {
          text: '음성 떨림',
          align: 'left'
        },
        subtitle: {
          text: '',
          align: 'left'
        },
        yAxis: {
          title: {
            text: response.data.chartname
          }
        },
        xAxis: {
          title: {
            text: '(초)'
          },
          accessibility: {
            rangeDescription: '(초)'
          }
        },
        legend: {
          layout: 'vertical',
          align: 'right',
          verticalAlign: 'middle'
        },
        plotOptions: {
          series: {
            label: {
              connectorAllowed: false
            },
            pointStart: 0,
            pointInterval: 10
          }
        },
        series: [
          {
            name: 'Q1',
            data: response.data.q1
          },
          {
            name: 'Q2',
            data: response.data.q2
          },
          {
            name: 'Q3',
            data: response.data.q3
          },
          {
            name: 'Q4',
            data: response.data.q4
          },
          {
            name: 'Q5',
            data: response.data.q5
          }
        ],
        responsive: {
          rules: [
            {
              condition: {
                maxWidth: 500
              },
              chartOptions: {
                legend: {
                  layout: 'horizontal',
                  align: 'center',
                  verticalAlign: 'bottom'
                }
              }
            }
          ]
        }
      });
    })
    },
    wordcloud() {
      axios.get(`${process.env.VUE_APP_BACK_END_URL}/itv/wordCloud`)
    .then(function(response){
      const text =response.data,
        lines = text.replace(/[():'?0-9]+/g, '').split(/[,\. ]+/g),
        data = lines.reduce((arr, word) => {
          let obj = Highcharts.find(arr, obj => obj.name === word);
          if (obj) {
            obj.weight += 1;
          } else {
            obj = {
              name: word,
              weight: 1
            };
            arr.push(obj);
          }
          return arr;
        }, []);

      Highcharts.chart('wordcloud1', {
        chart: {
          height: 380,
          width: 470
        },
        accessibility: {
          screenReaderSection: {
            beforeChartFormat:
              '<h5>{chartTitle}</h5><div>{chartSubtitle}</div><div>{chartLongdesc}</div><div>{viewTableButton}</div>'
          }
        },
        series: [
          {
            type: 'wordcloud',
            data,
            name: 'Occurrences'
          }
        ],
        title: {
          text: '면접답변 빈도 check',
          align: 'left'
        },
        subtitle: {
          text: '',
          align: 'left'
        },
        tooltip: {
          headerFormat: '<span style="font-size: 16px"><b>{point.key}</b></span><br>'
        }
      });
    })
    }
  },
  mounted() {
    this.wordcloud();
    this.barchart();
    this.face();
    this.voiceg();
    this.fetchQuestionData();
    this.fetchAIFeedback();
    this.fetchCTFeedback();
    this.fetchUserData();
  }
};
</script>