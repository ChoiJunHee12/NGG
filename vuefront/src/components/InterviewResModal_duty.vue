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
            <p>이름: {{mname}}</p>
            <p>날짜: {{resDate}}</p>
          </div>
        </div>

        <div class="resduty-subtitle3 col-1">
          <div class="resduty-subtitlecon3">종합 평가</div>
        </div>


        <div class="resduty-qcon10">
          <div class="reshduty-totalcom">
            <div v-for="(feedback, index) in AIFeedback" :key="index" class="resduty-analyze6-con">
              ○ {{ feedback }}
            </div>
          </div>
        </div>

        <div class="resduty-subtitle3">
          <div class="resduty-subtitlecon3">표정 분석 / 자세 분석</div>
        </div>

        <div class="resduty-qcon5">
          <div class="resduty-face">
            <div id="face" style="width: 400px;height:400px;"></div>
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
            <img class="resduty-tipicon" src="\img\res_tip.png">
            <div class="resduty-ti">회원님이 작성한 이력서의 기반으로 키워드를 추출하여 면접 내용과 비교하여 유사도를 ...</div>
          </div>
        </div>

        <div class="reshduty-btncon row">
          <div @click="pagechage(1)" class="col-1" :class="{'reshduty-lbutton':true, 'reshduty-active': activePage === 1}">Q1</div>
          <div @click="pagechage(2)" class="col-1" :class="{'reshduty-button':true, 'reshduty-active': activePage === 2}">Q2</div>
          <div @click="pagechage(3)" class="col-1" :class="{'reshduty-button':true, 'reshduty-active': activePage === 3}">Q3</div>
          <div @click="pagechage(4)" class="col-1" :class="{'reshduty-button':true, 'reshduty-active': activePage === 4}">Q4</div>
          <div @click="pagechage(5)" class="col-1" :class="{'reshduty-button':true, 'reshduty-active': activePage === 5}">Q5</div>
          <div @click="pagechage(6)" class="col-1" :class="{'reshduty-button':true, 'reshduty-active': activePage === 6}">Q6</div>
          <div @click="pagechage(7)" class="col-1" :class="{'reshduty-button':true, 'reshduty-active': activePage === 7}">Q7</div>
        </div>

        <!--면접질문-->
        <div :style="displayPage(1)" class="row scrollable-div">
          <div class="reshduty-qcon">
            <div class="reshduty-q">
              <div class="reshduty-qnum">Q{{ i + 1 }}.</div>
              <div class="reshduty-question">{{ Question[i] }}</div>
            </div>
          </div>

          <div class="reshduty-qcon4">
            <div class="reshduty-qcontent1">
              <div class="reshduty-subtitle1 row">
                <div class="reshduty-subtitle2 col-1">면접 대답</div>
                <div class="reshduty-qcontent2">{{ this.Comment[i] }}</div>
              </div>
            </div>
          </div>
          <div class="reshduty-qcon4" v-if="feedback[i]">
            <div class="reshduty-qcontent1">
              <div class="reshduty-subtitle1 row">
                <div class="reshduty-subtitle2 col-1">피드백</div>
                <div class="reshduty-qcontent2">{{ feedback[i] }}</div>
              </div>
            </div>
          </div>
          <button v-if="page > 1" class="reshduty-nextbtn col-1" @click="Previous">이전 문항</button>
          <button class="reshduty-nextbtn col-1" @click="next">다음 문항</button>
        </div>

        

        <div class="reshduty-subtitle3 col-1">
          <div class="resh-subtitlecon3">컨설턴트 피드백</div>
        </div>

        <div class="reshduty-qcon10">
          <div class="reshduty-totalcom">
            <div v-for="(feedback, index) in CTFeedback" :key="index">
              ○ {{ feedback }}
            </div>
            <!-- 전체적으로 자세를 유지하는 시간이 길지 않으며, 집중도가 떨어짐을 확인할 수 있다. dasdsadsa
            ㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㄴㅁㅇㄴㅁㅇㅁㄴㅇㄴㅁㅇㅁㄴㅇㄴㅁㅇㄴㅁㅇㄴㅁㅇㄴㅁㅇㄴㅁㅇㄴㅁㅇㅁㄴㅇㅁ
            ㄴㅇㄴㅁㅇㄹㅇㄴㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㅇㄴㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇd -->
          </div>
        </div>
        <div class="reshdudy-btnarea">
          <button class="reshduty-homebtn col-1" @click="$emit('close', false)">닫기</button>
        </div>
      </div>
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
      this.mname = mname;
      this.resDate = resDate ;
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
      var option = {
        title: {
          text: '감정 점수'
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
            areaStyle: {
              color: '#08AD94' // 영역 색상 변경
            },
            itemStyle: {
              color: '#0A6065' // 데이터 포인트 색상 변경
            },
            data: [
              {
                value: [60, 73, 85, 40, 65],
                name: '감정 점수'
              }
            ]
          }
        ]
      };
      myChart.setOption(option);
    },
    barchart() {
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
          categories: ['Q1', 'Q2'],
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
            name: '목 꺾임',
            data: [8, 3],
            color: '#08AD94'
          }
        ]
      });
    },
    voiceg() {
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
            text: '누적 목소리 떨림 횟수'
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
            data: [0, 0, 1, 2, 2, 2, 2, 3, 3, 3]
          },
          {
            name: 'Q2',
            data: [0, 1, 2, 3, 3, 3, 3, 3, 4, 4]
          },
          {
            name: 'Q3',
            data: [0, 0, 1, 1, 1, 1, 1, 2, 2, 2]
          },
          {
            name: 'Q4',
            data: [0, 0, 1, 2, 2, 2, 2, 3, 4, 5]
          },
          {
            name: 'Q5',
            data: [0, 0, 0, 0, 0, 0, 0, 1, 1, 1]
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
    },
    wordcloud() {
      const text =
        "Chapter 1. Down the Rabbit-Hole Alice was beginning to get very tired of sitting by her sister on the bank, and of having nothing to do: once or twice she had peeped into the book her sister was reading, but it had no pictures or conversations in it, 'and what is the use of a book,' thought Alice 'without pictures or conversation?' So she was considering in her own mind (as well as she could, for the hot day made her feel very sleepy and stupid), whether the pleasure of making a daisy-chain would be worth the trouble of getting up and picking the daisies, when suddenly a White Rabbit with pink eyes ran close by her.",
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
          text: '이력서 키워드 유사도 카운트',
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

<style scoped>
/* Add your styles here */
</style>
