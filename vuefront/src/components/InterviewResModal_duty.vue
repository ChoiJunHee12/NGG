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
            <p>이름: 홍길동</p>
            <p>날짜: 2024-07-25</p>
          </div>
        </div>

        <div class="resduty-subtitle3 col-1">
          <div class="resduty-subtitlecon3">종합 평가</div>
        </div>

        <div class="resduty-qcon10">
          <div class="reshduty-totalcom">
            <div class="resduty-analyze6-con">○ 시간이 지날수록 자세가 불안정함이 보여 좀 더 집중도를 올려야합니다!</div>
            <div class="resduty-analyze6-con">○ 회원님의 자세 흐트러짐은 평균보다 높아요.</div>
            <div class="resduty-analyze6-con">○ 시간이 지날수록 자세가 불안정함이 보여 좀 더 집중도를 올려야합니다!</div>
            <div class="resduty-analyze6-con">○ 전체적으로 자세를 유지하는 시간이 길지 않으며, 집중도가 떨어짐을 확인할 수 있다.</div>
            <div class="resduty-analyze6-con">○ 너무 같은 단어를 반복하여 말했습니다.</div>
            <div class="resduty-analyze6-con">○ 이력서와 면접 질문과 유사도가 떨어집니다.</div>
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
            전체적으로 자세를 유지하는 시간이 길지 않으며, 집중도가 떨어짐을 확인할 수 있다. dasdsadsa
            ㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㄴㅁㅇㄴㅁㅇㅁㄴㅇㄴㅁㅇㅁㄴㅇㄴㅁㅇㄴㅁㅇㄴㅁㅇㄴㅁㅇㄴㅁㅇㄴㅁㅇㅁㄴㅇㅁ
            ㄴㅇㄴㅁㅇㄹㅇㄴㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㅇㄴㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇd
          </div>
        </div>
        <div class="reshduty-subtitle3 col-1">
          <div class="resh-subtitlecon3">담당 컨설턴트 평가하기</div>
        </div>

        <div class="reshduty-qcon10">
          <div v-if="!isReviewSubmitted" class="reshduty-totalcom">
            <div style="margin:auto; text-align:center;">
              <div style="width:100%; margin:10px;">
                <input type="radio" name="review" value="매우 만족" v-model="review"> &nbsp; 매우 만족 &nbsp;
                <input type="radio" name="review" value="만족" v-model="review"> &nbsp; 만족 &nbsp;
                <input type="radio" name="review" value="보통" v-model="review"> &nbsp; 보통 &nbsp;
                <input type="radio" name="review" value="불만족" v-model="review"> &nbsp; 불만족 &nbsp;
                <input type="radio" name="review" value="매우 불만족" v-model="review"> &nbsp; 매우 불만족
              </div>
              <div style="height:50px; margin:auto;">
                <button class="resh-homebtn col-1" style="height:40px; line-height: 30px; width:110px; margin:10px;" @click="submitReview">제출하기</button>
              </div>
            </div>
          </div>
                    <div v-if="isReviewSubmitted" class="reshduty-totalcom_1">
            평점 : {{ review }}
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
import * as echarts from 'echarts';
export default {
  data() {
    return {
      activePage: 1,
      page: 1,
      Q1: [],
      Question: [
        '자신에 대하여 소개해주세요.',
        '자신의 강점, 단점을 지원 분야와 관련지어 말해주세요.',
        '성공했던 혹은 실패했던 사례는 무엇인가요?',
        '자랑할 만한 성과는 무엇이 있나요?',
        '본인만의 업무상 경쟁력은 무엇인가요?',
        '우리 회사의 (해당 직무)에서 가장 중요하다고 생각하는 업무는 무엇이며, 그 업무를 수행하기 위해 필요한 역량은 무엇이라고 생각하십니까?',
        '(해당 직무)와 관련된 최근 트렌드나 기술 동향에 대해 어떻게 생각하시나요? 이러한 변화가 우리 회사와 해당 직무에 어떤 영향을 미칠 것으로 예상하십니까?'
      ],
      Comment: [
        'Q1번에 대한 답변을 STT로 표현 해주시고 back연결후에 data에 있는 Comment기본값을 지워주시면 됩니다.',
        'Q2번에 대한 답변을 STT로 표현 해주시고 back연결후에 data에 있는 Comment기본값을 지워주시면 됩니다.',
        'Q3번에 대한 답변을 STT로 표현 해주시고 back연결후에 data에 있는 Comment기본값을 지워주시면 됩니다.',
        'Q4번에 대한 답변을 STT로 표현 해주시고 back연결후에 data에 있는 Comment기본값을 지워주시면 됩니다.',
        'Q5번에 대한 답변을 STT로 표현 해주시고 back연결후에 data에 있는 Comment기본값을 지워주시면 됩니다.',
        'Q6번에 대한 답변을 STT로 표현 해주시고 back연결후에 data에 있는 Comment기본값을 지워주시면 됩니다.',
        'Q7번에 대한 답변을 STT로 표현 해주시고 back연결후에 data에 있는 Comment기본값을 지워주시면 됩니다.'
      ],
      feedback: [
        '피드백 존재시 v-if로 보이게 해주시면 됩니다.',
        '현재 Q2. 까지만 보이게 설정했습니다.'
      ],
      i: 0,
      content: 1,
      isReviewSubmitted: false,
      review: ''
    };
  },
  methods: {
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
            name: '목 꺽임',
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
  }
};
</script>

<style scoped>
/* Add your styles here */
</style>
