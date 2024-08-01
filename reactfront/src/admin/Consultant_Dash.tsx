import React, { useEffect, useState} from "react";
import "./Consultant.css";
import ReactEcharts from "echarts-for-react";
import Highcharts from 'highcharts/highmaps';
import HighchartsReact from 'highcharts-react-official';
import axios from 'axios';




const Consultant= () => {
  /* 분야 별 점수 여긴 데이터를 뭐 넣을지 못 정했습니다.*/
  const g3 = {
    tooltip: {
      trigger: 'item'
    },
    legend: {
      top: '5%',
      left: 'center'
    },
    series: [
      {
        name: '점수 분포도',
        type: 'pie',
        radius: ['40%', '70%'],
        center: ['50%', '70%'],
        startAngle: 180,
        endAngle: 360,
        data: [
          { value: 600, name: '0 ~ 1점' },
          { value: 230, name: '1 ~ 2점' },
          { value: 780, name: '3 ~ 4점' },
          { value: 1500, name: '4 ~ 5점' }
        ]
      }
    ]
  };
  /* 월 평균 그래프 */
  const monavg={
    title: {
      text: 'Line Chart'
    },
    tooltip: {
      trigger: 'axis'
    },
    legend: {
      data: ['IT', '경영/컨설팅', '인사/총무', '재무/회계', '외국어','평균']
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    toolbox: {
      feature: {
        saveAsImage: {}
      }
    },
    xAxis: {
      type: 'category',
      boundaryGap: false,
      data: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        name: 'IT',
        type: 'line',
        data: [3.7, 3.9, 4.2, 4.1, 4.0, 4.2, 3.8]
      },
      {
        name: '경영/컨설팅',
        type: 'line',
        data: [3.5, 3.7, 4.3, 4.6, 4.7, 4.4, 3.5]
      },
      {
        name: '인사/총무',
        type: 'line',
        data: [3.9, 4.2, 4.9,2.9 , 3.4, 4.8,3.9]
      },
      {
        name: '재무/회계',
        type: 'line',
        data: [2.9 , 3.4, 4.8,3.9,3.9, 4.2,4.1]
      },
      {
        name: '외국어',
        type: 'line',
        data: [3.9, 4.2, 4.1,2.9 , 3.4, 4.8,3.9]
      },
      {
        name: '평균',
        type: 'line',
        data: [3.9,3.7,3.6,3.9,3.7,3.6,3.8]
      }
    ]
  };
  
  const option1 = {
    xAxis: {
      type: "category",
      boundaryGap: false,
      data: ["20일", "21일", "22일", "23일", "24일", "25일", "26일"],
    },
    yAxis: {
      type: "value",
    },
    series: [
      {
        data: [320, 632, 301, 1334, 890, 1330, 1320],
        type: "line",
        areaStyle: {},
      },
    ],
  };
  const chartpie = {
    series: [
      {
        name: "Access From",
        type: "pie",
        radius: ["40%", "70%"],
        avoidLabelOverlap: false,
        padAngle: 5,
        itemStyle: {
          borderRadius: 10,
        },
        label: {
          show: true,
          position: "inside",
          fontWeight: "bold",
          fontSize: 16,
          rich: {
            alignCenter: {
              align: "center",
            },
          },
        },
        emphasis: {
          label: {
            show: true,
            fontSize: 20,
            fontWeight: "bold",
          },
        },
        labelLine: {
          show: false,
        },
        data: [
          { value: 1048, name: "IT" },
          { value: 735, name: "사무직" },
          { value: 580, name: "제조업" },
          { value: 484, name: "의료" },
          { value: 300, name: "회계" },
        ],
      },
    ],
  };
  const chartbar = {
    tooltip: {
      trigger: "axis",
      axisPointer: {
        type: "cross",
        crossStyle: {
          color: "#999",
        },
      },
    },

    xAxis: [
      {
        type: "category",
        data: ["IT", "사무직", "제조업", "의료", "회계"],
        axisPointer: {
          type: "shadow",
        },
      },
    ],
    yAxis: [
      {
        type: "value",

        min: 0,
        max: 100,
        interval: 25,
        axisLabel: {
          formatter: "{value} 점",
        },
      },
      {
        type: "value",
        min: 0,
        max: 100,
        interval: 25,
        axisLabel: {
          formatter: "{value} 점",
        },
      },
    ],
    series: [
      {
        name: "인성면접",
        type: "bar",
        tooltip: {
          valueFormatter: function (value: number) {
            return (value as number) + " 점";
          },
        },
        data: [
          52.0, 64.9, 67.0, 53.2, 65.6, 46.7, 96.6, 62.2, 32.6, 40.0, 96.4,
          63.3,
        ],
      },
      {
        name: "직무면접",
        type: "bar",
        tooltip: {
          valueFormatter: function (value: number) {
            return (value as number) + " 점";
          },
        },
        data: [
          72.6, 65.9, 59.0, 96.4, 48.7, 30.7, 75.6, 82.2, 98.7, 38.8, 76.0,
          42.3,
        ],
      },
      {
        name: "Temperature",
        type: "line",
        yAxisIndex: 1,
        tooltip: {
          valueFormatter: function (value: number) {
            return (value as number) + "점";
          },
        },
        data: [
          2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2,
        ],
      },
    ],
  };
  let base = +new Date(1988, 9, 3);
  let oneDay = 24 * 3600 * 1000;
  let data = [[base, Math.random() * 300]];
  for (let i = 1; i < 20000; i++) {
    let now = new Date((base += oneDay));
    data.push([+now, Math.round((Math.random() - 0.5) * 20 + data[i - 1][1])]);
  }
  const chartzi = {
    xAxis: {
      type: "time",
      boundaryGap: true,
    },
    yAxis: {
      type: "value",
      boundaryGap: [0, "100%"],
    },
    dataZoom: [
      {
        type: "inside",
        start: 0,
        end: 20,
      },
      {
        start: 0,
        end: 20,
      },
    ],
    series: [
      {
        name: "Fake Data",
        type: "line",
        smooth: true,
        symbol: "none",
        areaStyle: {},
        data: data,
      },
    ],
  };
  const twobar = {
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'cross',
        crossStyle: {
          color: '#999'
        }
      }
    },
    toolbox: {
      feature: {
        dataView: { show: true, readOnly: false },
        magicType: { show: true, type:  'bar' },
        restore: { show: true },
        saveAsImage: { show: true }
      }
    },
    legend: {
      data: [ '컨설팅 완료','컨설팅 신청']
    },
    xAxis: [
      {
        type: 'category',
        data: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
        axisPointer: {
          type: 'shadow'
        }
      }
    ],
    yAxis: [
      {
        type: 'value',
        name: '', //y축 설명
        min: 0,
        max: 250,
        interval: 50,
        axisLabel: {
          formatter: '{value} 회'
        }
      }
    ],
    series: [
      {
        name: '컨설팅 완료',
        type: 'bar',
        tooltip: 
          ''
        ,
        data: [
          2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3
        ]
      },
      {
        name: '컨설팅 신청',
        type: 'bar',
        tooltip: '',
        data: [
          2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3
        ]
      }
    ]
  };
  return (
    <div className="consultant-container">
      <div className="consultant-header">
          <p className="consultant-text">&nbsp;Consultant DashBoard</p>
      </div>
      <br></br>
      <div className="consultant-dash">
        <div className="consultant-info1">
          <div>
            <h3 className="consultant-info-text">컨설턴트 수</h3>
            <div>
              <p className="consultant-info-textinfo">36명</p>
            </div>
          </div>
          <div className="consultant-info2">
            <img src="/img/admin_img/people.svg" className="consultant-svg"></img>
          </div>
        </div>
        <div className="consultant-info1">
          <div>
            <h3 className="consultant-info-text">컨설턴트 평균 회원수</h3>
            <div>
              <p className="consultant-info-textinfo">4.6명</p>
            </div>
          </div>
          <div className="consultant-info2">
            <img src="/img/admin_img/person.svg" className="consultant-svg"></img>
          </div>
        </div>
        <div className="consultant-info1">
          <div>
            <h3 className="consultant-info-text">컨설턴트 평균 평점</h3>
            <div>
              <p className="consultant-info-textinfo">3.2점</p>
            </div>
          </div>
          <div className="consultant-info2">
            <img src="/img/admin_img/dollar.svg" className="consultant-svg"></img>
          </div>
        </div>
        <div className="consultant-info1">
          <div>
            <h3 className="consultant-info-text">주간 컨설팅 횟수</h3>
            <div>
              <p className="consultant-info-textinfo">1,329번</p>
            </div>
          </div>
          <div className="consultant-info2">
            <img src="/img/admin_img/dollars.svg" className="consultant-svg"></img>
          </div>
        </div>
      </div>

      <div className="consultant-chart">
        <div className="consultant-chart-col">
          <div className="consultant-chart-row" style={{ width: "800px"}}>
            <div className="consultant-chart-name">완료 횟수 </div>
            <ReactEcharts
              option={twobar}
              style={{ height: "400px", width: "100%" }}
            />
            {/* <ReactEcharts
              option={option1}
              style={{ height: "400px", width: "100%" }}
            /> */}
          </div>
          <div className="consultant-chart-row" style={{ width: "480px" }}>
            <div className="consultant-chart-name">컨설턴트 분포도</div>
            <ReactEcharts
              option={chartpie}
              style={{ height: "400px", width: "100%" }}
            />
          </div>
        </div>
        <div className="consultant-chart-col">
          <div className="consultant-chart-row" style={{ width: "480px" }}>
            <div className="consultant-chart-name">점수 분포도</div>
            {/* <ReactEcharts
              option={chartbar}
              style={{ height: "400px", width: "100%" }}
            /> */}
            <ReactEcharts
              option={g3}
              style={{ height: "400px", width: "100%" }}
            />
          </div>
          <div className="consultant-chart-row" style={{ width: "800px" }}>
            <div className="consultant-chart-name">컨설팅 월평균점수</div>
            <ReactEcharts
              option={monavg}
              style={{ height: "400px", width: "100%" }}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Consultant;
