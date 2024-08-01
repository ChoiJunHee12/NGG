import React, { useEffect, useState } from 'react';
import Highcharts from 'highcharts';
import * as echarts from 'echarts';
import wordcloud from 'highcharts/modules/wordcloud';
import './feedbackDetail.css'; // 스타일 파일을 별도로 작성하거나 기존 스타일을 변환하세요.

wordcloud(Highcharts);

const FeedbackDetail: React.FC = () => {
  const [page, setPage] = useState<number>(1);
  const [i, setI] = useState<number>(0);
  const [content, setContent] = useState<number>(1);
  const [nownum, setNownum] = useState<number>(1);

  const questions = [
    '자신에 대하여 소개해주세요.',
    '자신의 강점, 단점을 지원 분야와 관련지어 말해주세요.',
    '성공했던 혹은 실패했던 사례는 무엇인가요?',
    '자랑할 만한 성과는 무엇이 있나요?',
    '본인만의 업무상 경쟁력은 무엇인가요?',
    '우리 회사의 (해당 직무)에서 가장 중요하다고 생각하는 업무는 무엇이며, \n 그 업무를 수행하기 위해 필요한 역량은 무엇이라고 생각하십니까?',
    '(해당 직무)와 관련된 최근 트렌드나 기술 동향에 대해 어떻게 생각하시나요? \n 이러한 변화가 우리 회사와 해당 직무에 어떤 영향을 미칠 것으로 예상하십니까?'
  ];

  const comments = [
    'Q1번에 대한 답변을 STT로 표현 해주시고 back연결후에 data에 있는 Comment기본값을 지워주시면 됩니다.',
    'Q2번에 대한 답변을 STT로 표현 해주시고 back연결후에 data에 있는 Comment기본값을 지워주시면 됩니다.',
    'Q3번에 대한 답변을 STT로 표현 해주시고 back연결후에 data에 있는 Comment기본값을 지워주시면 됩니다.',
    'Q4번에 대한 답변을 STT로 표현 해주시고 back연결후에 data에 있는 Comment기본값을 지워주시면 됩니다.',
    'Q5번에 대한 답변을 STT로 표현 해주시고 back연결후에 data에 있는 Comment기본값을 지워주시면 됩니다.'
  ];

  const feedbacks = [
    '피드백 존재시 v-if로 보이게 해주시면 됩니다.',
    '현재 Q2. 까지만 보이게 설정했습니다.'
  ];

  useEffect(() => {
    graph2();
    wordcloud();
    barchart();
    face();
    voiceg();
  }, []);

  const pagechange = (num: number) => {
    setPage(num);
    setI(num - 1);
    setContent(num === 8 ? 8 : 1);
    setNownum(num);
    window.scrollTo({
      top: 0,
      behavior: 'smooth'
    });
  };

  const displayPage = (pageNum: number) => {
    return content === pageNum ? { display: 'block' } : { display: 'none' };
  };

  const graph2 = () => {
    const data = [];
    const time = (new Date()).getTime();
    let y = 0;
  
    for (let i = -9; i <= 0; i++) {
      if (Math.random() < 0.1) {
        y = -1;
      } else {
        y = Math.random() > 0.5 ? 1 : 0;
      }
  
      data.push({
        x: time + i * 90000,
        y: y
      });
    }
  
    Highcharts.chart('graph2', {
      accessibility: {
        enabled: false
      },
      chart: {
        type: 'spline',
        height: 320,
        width: 480
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
          // formatter: function () {
          //   return this.value === -1 ? '부정' :
          //     this.value === 0 ? '중립' :
          //     this.value === 1 ? '긍정' : '';
          // }
        }
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
        type: 'spline',
        name: '감정 라인',
        data: data,
        color: '#08AD94'
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
  };

  const face = () => {
    const chartContainer = document.getElementById('face')!;
    chartContainer.style.width = '300px';
    chartContainer.style.height = '400px';

    const myChart = echarts.init(chartContainer);
    const option = {
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
            color: '#08AD94'
          },
          itemStyle: {
            color: '#0A6065'
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
  };

  const barchart = () => {
    Highcharts.chart('barchart', {
      accessibility: {
        enabled: false
      },
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
          description: 'Categories'
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
      series: [{
        type: 'column',
        name: '목 꺽임',
        data: [8, 3],
        color: '#08AD94'
      }]
    });
  };

  const wordcloud = () => {
    const text = 'Chapter 1. Down the Rabbit-Hole Alice was beginning to get very tired of sitting by her sister on the bank, and of having nothing to do: once or twice she had peeped into the book her sister was reading, but it had no pictures or conversations in it, \'and what is the use of a book,\' thought Alice \'without pictures or conversation?\' So she was considering in her own mind (as well as she could, for the hot day made her feel very sleepy and stupid), whether the pleasure of making a daisy-chain would be worth the trouble of getting up and picking the daisies, when suddenly a White Rabbit with pink eyes ran close by her.';
  
    const lines = text.replace(/[():'?0-9]+/g, '').split(/[,\. ]+/g);
    const data = lines.reduce((arr: { name: string, weight: number }[], word) => {
      let obj = arr.find(obj => obj.name === word);
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
      accessibility: {
        enabled: false
      },
      chart: {
        type: 'wordcloud',
        height: 380,
        width: 470
      },
      title: {
        text: '단어 구름'
      },
      series: [{
        type: 'wordcloud',
        data: data,
        name: 'Occurrences',
        color: '#08AD94'
      }],
    });
  };

  const voiceg = () => {
    const ctx = new (window.AudioContext || (window as any).webkitAudioContext)();
    const analyser = ctx.createAnalyser();
    analyser.fftSize = 128;
    const bufferLength = analyser.frequencyBinCount;
    const dataArray = new Uint8Array(bufferLength);

    const canvas = document.getElementById('voiceg') as HTMLCanvasElement;
    const canvasCtx = canvas.getContext('2d')!;
    canvas.width = 470;
    canvas.height = 380;

    const draw = () => {
      requestAnimationFrame(draw);
      analyser.getByteFrequencyData(dataArray);

      canvasCtx.fillStyle = '#000';
      canvasCtx.fillRect(0, 0, canvas.width, canvas.height);

      const barWidth = (canvas.width / bufferLength) * 2.5;
      let barHeight;
      let x = 0;

      for (let i = 0; i < bufferLength; i++) {
        barHeight = dataArray[i] / 2;
        canvasCtx.fillStyle = '#08AD94';
        canvasCtx.fillRect(x, canvas.height - barHeight / 2, barWidth, barHeight);
        x += barWidth + 1;
      }
    };

    draw();
  };

  return (
    <div className="feedback-detail">
      <div className="feedback-header">
        <h1>Feedback Details</h1>
        <div className="pagination">
          <button onClick={() => pagechange(1)} className={page === 1 ? 'active' : ''}>1</button>
          <button onClick={() => pagechange(2)} className={page === 2 ? 'active' : ''}>2</button>
          <button onClick={() => pagechange(3)} className={page === 3 ? 'active' : ''}>3</button>
          <button onClick={() => pagechange(4)} className={page === 4 ? 'active' : ''}>4</button>
          <button onClick={() => pagechange(5)} className={page === 5 ? 'active' : ''}>5</button>
          <button onClick={() => pagechange(6)} className={page === 6 ? 'active' : ''}>6</button>
          <button onClick={() => pagechange(7)} className={page === 7 ? 'active' : ''}>7</button>
          <button onClick={() => pagechange(8)} className={page === 8 ? 'active' : ''}>8</button>
        </div>
      </div>

      <div className="feedback-content" style={displayPage(1)}>
        <h2>질문</h2>
        <div className="questions">
          {questions.map((question, index) => (
            <div key={index}>{index + 1}. {question}</div>
          ))}
        </div>
      </div>

      <div className="feedback-content" style={displayPage(2)}>
        <h2>STT 피드백</h2>
        <div className="comments">
          {comments.map((comment, index) => (
            <div key={index}>{index + 1}. {comment}</div>
          ))}
        </div>
      </div>

      <div className="feedback-content" style={displayPage(3)}>
        <h2>그래프</h2>
        <div id="graph2" className="chart-container"></div>
      </div>

      <div className="feedback-content" style={displayPage(4)}>
        <h2>단어 구름</h2>
        <div id="wordcloud1" className="chart-container"></div>
      </div>

      <div className="feedback-content" style={displayPage(5)}>
        <h2>레이다 차트</h2>
        <div id="face" className="chart-container"></div>
      </div>

      <div className="feedback-content" style={displayPage(6)}>
        <h2>막대 차트</h2>
        <div id="barchart" className="chart-container"></div>
      </div>

      <div className="feedback-content" style={displayPage(7)}>
        <h2>음성 그래프</h2>
        <canvas id="voiceg" className="chart-container"></canvas>
      </div>

      <div className="feedback-content" style={displayPage(8)}>
        <h2>피드백</h2>
        <div className="feedbacks">
          {feedbacks.map((feedback, index) => (
            <div key={index}>{index + 1}. {feedback}</div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default FeedbackDetail;
