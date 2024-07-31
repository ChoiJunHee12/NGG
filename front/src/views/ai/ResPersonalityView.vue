<template>
    <div class="res-container">

    <div class="res-btncon row">
        <div @click="pagechage(1)" class=" col-1" :class="{'res-lbutton':true,'res-qactive':nownum===1}">Q1</div>
        <div @click="pagechage(2)" class=" col-1" :class="{'res-button':true,'res-qactive':nownum===2}">Q2</div>
        <div @click="pagechage(3)" class=" col-1" :class="{'res-button':true,'res-qactive':nownum===3}">Q3</div>
        <div @click="pagechage(4)" class=" col-1" :class="{'res-button':true,'res-qactive':nownum===4}">Q4</div>
        <div @click="pagechage(5)" class=" col-1" :class="{'res-button':true,'res-qactive':nownum===5}">Q5</div>
        <div @click="pagechage(6)" class=" col-1" :class="{'res-rbutton':true,'res-qactive':nownum===6}">종합 평가</div>
    </div>
    <div :style="displayPage(1)" class="res-con row">
        <div class="res-qcon">
        <div class="res-q">
            <div class="res-qnum">Q{{ i+1 }}.</div>
            <div class="res-question">{{ Question[i] }}</div>
        </div>
    </div>
        <div class="res-qcon2">
            <div class="res-vdiv">
            <img class="res-video" src="\img\res_demovideo.png">
            </div>
            
            <div class="res-dgraph">
                <div id="graph2"></div>
                <div class="res-stress">스트레스 : 80(%)</div>
            </div>
   
        </div>
        <div class="res-qcon3">
            
            <div class="res-dcontent row">
                <div class="res-vcon col-1">
                <img class="res-voicew" src="\img\res_demovoice.png">
                </div>
                <div class="res-voice col-2">
                    <div class="res-voice1">
                    음성 안정도 : 90점
                </div>
                <div class="res-voice1">
                    공백 시간 : 12초
                </div>
                </div>
            </div>
        </div>
        <div class="res-qcon4">
            <div class="res-qcontent1">
                <div class="res-subtitle1 row">
                    <div class="res-subtitle2 col-1">면접 대답</div>
                    <div class="res-qcontent2">{{this.Comment[i]}}</div>
                </div>
            </div>
        </div>
        <div class="res-qcon4" v-if="feedback[i]">
            <div class="res-qcontent1">
                <div class="res-subtitle1 row">
                    <div class="res-subtitle2 col-1">피드백</div>
                    <div class="res-qcontent2">{{feedback[i]}}</div>
                </div>
            </div>
        </div>
        <button v-if="page>1" class="res-nextbtn col-1" @click="Previous">이전 문항</button>
        <button class="res-nextbtn col-1" @click="next">다음 문항</button>
    </div>



  <!--          종합 평가                       -->  
    <div :style="displayPage(6)" class="res-con row">
        <div class="resduty-summary">
            <div class="resduty-total-t row"><div class="resduty-total-title col-1">종합 분석</div>
            <div class="resduty-total-subtitle col-1">- 인성 면접</div>
        </div>
            
            <div class="resduty-summary-right">
        <p>이름: 홍길동</p>
        <p>날짜: 2024-07-25</p>
      </div>
        </div>


        <div class="res-subtitle3 col-1"><div class="res-subtitlecon3">종합 평가</div></div>
        <hr>
        <div class="res-qcon10">
            <div class="res-totalcom">
                <div class="res-analyze6-con">○ 시간이 지날수록 자세가 불안정함이 보여 좀 더 집중도를 올려야합니다!</div>
                <div class="res-analyze6-con">○ 회원님의 자세 흐트러짐은 평균보다 높아요.</div>
                <div class="res-analyze6-con">○ 시간이 지날수록 자세가 불안정함이 보여 좀 더 집중도를 올려야합니다!</div>
                <div class="res-analyze6-con">○ 전체적으로 자세를 유지하는 시간이 길지 않으며, 집중도가 떨어짐을 확인할 수 있다.</div>
                <div class="res-analyze6-con">○ 너무 같은 단어를 반복하여 말했습니다.</div>
                <div class="res-analyze6-con">○ 이력서와 면접 질문과 유사도가 떨어집니다.</div>
                
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


     



        <router-link to="/Main"><button class="res-homebtn col-1" >홈으로 돌아가기</button></router-link>
    </div>
    </div>
    
</template>
<script>
import * as echarts from 'echarts';
export default {
    data() {
        return {
            page:6,
            Q1 : [],
            Question:['자신에 대하여 소개해주세요.',
                '자신의 강점, 단점을 지원 분야와 관련지어 말해주세요.',
                '성공했던 혹은 실패했던 사례는 무엇인가요?',
                '자랑할 만한 성과는 무엇이 있나요?',
                '본인만의 업무상 경쟁력은 무엇인가요?'
            ],
            Comment:['Q1번에 대한 답변을 STT로 표현 해주시고 back연결후에 data에 있는 Comment기본값을 지워주시면 됩니다.',
            'Q2번에 대한 답변을 STT로 표현 해주시고 back연결후에 data에 있는 Comment기본값을 지워주시면 됩니다.',
            'Q3번에 대한 답변을 STT로 표현 해주시고 back연결후에 data에 있는 Comment기본값을 지워주시면 됩니다.',
            'Q4번에 대한 답변을 STT로 표현 해주시고 back연결후에 data에 있는 Comment기본값을 지워주시면 됩니다.',
            'Q5번에 대한 답변을 STT로 표현 해주시고 back연결후에 data에 있는 Comment기본값을 지워주시면 됩니다.'],
            feedback:['피드백 존재시 v-if로 보이게 해주시면 됩니다.',
                '현재 Q2. 까지만 보이게 설정했습니다.'
            ],
            i:5,
            content:6,
            nownum:6
        }
    },
    methods: {
        next(){
            this.pagechage(this.page+1);
        },
        Previous(){
            this.pagechage(this.page-1);
        },
        pagechage(num){
            console.log(num)
            this.page=num;
            this.i=num-1;
            if(num===6){
                this.content=6
            }else{
                this.content=1
            }
            this.nownum=num;
            window.scrollTo({
                top: 0,
                behavior: 'smooth' // 부드러운 스크롤을 위해 'smooth' 옵션 사용
            });
        },
        displayPage(pageNum) {
            
            return this.content === pageNum ? { display: 'block' } : { display: 'none' };
        },
        graph2(){
            // 임시 데이터
            const data = [];
            const time = (new Date()).getTime();
            let y = 0;

            for (let i = -9; i <= 0; i++) {
                // Change y to -1 occasionally
                if (Math.random() < 0.1) {
                    y = -1;
                } else {
                    y = Math.random() > 0.5 ? 1 : 0;
                }

                data.push({
                    x: time + i * 90000, // 90,000 milliseconds = 1 minute 30 seconds
                    y: y
                });
            }
            Highcharts.chart('graph2', {
            chart: {
                type: 'spline',
                height: 320, // Height of the chart
                width: 480   // Width of the chart
            },

            title: {
                text: "",
                align: 'left'
            },

            subtitle: {
                text: '',
                align: 'left'
            },

            xAxis: {
                type: 'datetime',
                title: {
                    text: '시간'
                }
            },

            yAxis: [{
                title: {
                    text: '감정 인식'
                },
                min: -1,
                max: 1,
                tickPositions: [-1, 0, 1],
                labels: {
                    formatter: function () {
                        return this.value === -1 ? '부정' :
                            this.value === 0 ? '중립' :
                            this.value === 1 ? '긍정' : '';
                    }
                },
            }],

            plotOptions: {
                series: {
                    animation: {
                        duration: 1000
                    },
                    marker: {
                        enabled: false
                    },
                    lineWidth: 3
                }
            },

            series: [{
                name: '감정 라인',
                data: data
            }],
            
            responsive: {
                rules: [{
                    condition: {
                        maxWidth: 300
                    },
                    chartOptions: {
                        yAxis: [{
                            tickAmount: 2,
                            title: {
                                x: 15,
                                reserveSpace: false
                            }
                        }]
                    }
                }]
            }
        });


        },

voiceg(){
    Highcharts.chart('voiceg', {
chart:{
    height: 380, // Height of the chart
    width: 470,   // Width of the chart
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
    title:{
        text:'(초)'
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

series: [{
    name: 'Q1',
    data: [
        0, 0, 1, 2, 2, 2,2,
        3, 3, 3
    ]
},
{
    name: 'Q2',
    data: [
        0, 1, 2, 3, 3, 3,3,
        3, 4, 4
    ]
},
{
    name: 'Q3',
    data: [
        0, 0, 1, 1, 1, 1,1,
        2, 2, 2
    ]
},
{
    name: 'Q4',
    data: [
        0, 0, 1, 2, 2, 2,2,
        3, 4, 5
    ]
},
{
    name: 'Q5',
    data: [
        0, 0, 0, 0, 0, 0,0,
        1, 1, 1
    ]
}
// }, {
//     name: 'Manufacturing',
//     data: [
//         24916, 37941, 29742, 29851, 32490, 30282,
//         38121, 36885, 33726, 34243, 31050, 33099, 33473
//     ]
// }, {
//     name: 'Sales & Distribution',
//     data: [
//         11744, 30000, 16005, 19771, 20185, 24377,
//         32147, 30912, 29243, 29213, 25663, 28978, 30618
//     ]
// }, {
//     name: 'Operations & Maintenance',
//     data: [
//         null, null, null, null, null, null, null,
//         null, 11164, 11218, 10077, 12530, 16585
//     ]
// }, {
//     name: 'Other',
//     data: [
//         21908, 5548, 8105, 11248, 8989, 11816, 18274,
//         17300, 13053, 11906, 10073, 11471, 11648
//     ]
// }
],

responsive: {
    rules: [{
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
    }]
}

});
},


        face(){
            const chartContainer = document.getElementById('face');
    chartContainer.style.width = '300px';
    chartContainer.style.height = '380px';

    // ECharts 초기화
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
    data: [
      '점',

    ]
  },
  radar: [
    {
      indicator: [
        { text: 'Q1', max: 100 },
        { text: 'Q2', max: 100 },
        { text: 'Q3', max: 100 },
        { text: 'Q4', max: 100 },
        { text: 'Q5', max: 100}
      ],
      center: ['50%', '50%'],
      radius: 80
    },


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
          value: [60, 73, 85, 40,65],
          name: '감정 점수'
        }
      ]
    },
    
  ]
};
myChart.setOption(option);
        },
        barchart(){
            Highcharts.chart('barchart', {
    chart: {
        type: 'column',
        height:'380px',
    },
    title: {
        text: '자세 흐트러짐',
        align: 'left'
    },
    subtitle: {
        text:
            '',
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
            name: '목 꺽임',
            data: [8, 3,5,3,4],
            color:'#A1C5FB'
        },
        
        
    ]
});
        },
        wordcloud(){
        const text =
        'Chapter 1. Down the Rabbit-Hole ' +
        'Alice was beginning to get very tired of sitting by her sister on ' +
        'the bank, and of having nothing to do: ' +
        'once or twice she had peeped into the book her sister was reading, ' +
        'but it had no pictures or conversations ' +
        'in it, \'and what is the use of a book,\' thought Alice ' +
        '\'without pictures or conversation?\'' +
        'So she was considering in her own mind (as well as she could, for ' +
        'the hot day made her feel very sleepy ' +
        'and stupid), whether the pleasure of making a daisy-chain would be ' +
        'worth the trouble of getting up and picking ' +
        'the daisies, when suddenly a White Rabbit with pink eyes ran close ' +
        'by her.',
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
            width: 470,   

        },
    accessibility: {
        screenReaderSection: {
            beforeChartFormat: '<h5>{chartTitle}</h5>' +
                '<div>{chartSubtitle}</div>' +
                '<div>{chartLongdesc}</div>' +
                '<div>{viewTableButton}</div>'
        }
    },
    series: [{
        type: 'wordcloud',
        data,
        name: 'Occurrences'
    }],
    title: {
        text: '이력서 키워드 유사도 카운트',
        align: 'left'
    },
    subtitle: {
        text: '',
        align: 'left'
    },
    tooltip: {
        headerFormat: '<span style="font-size: 16px"><b>{point.key}</b>' +
            '</span><br>'//커서 내용
    }
});
    }
    },
    mounted() {
        // this.graph1();
        this.graph2();
        this.wordcloud();
        this.barchart();
        this.face();
        this.voiceg();
    },
}
</script>
<style scoped>

</style>