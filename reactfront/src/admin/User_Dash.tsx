import React, { useState, useEffect } from "react";
import "./user.css";
import ReactEcharts from "echarts-for-react";
import Highcharts from "highcharts/highmaps";
import HighchartsReact from "highcharts-react-official";
import axios from "axios";

interface CapitalData {
  name: string;
  lat: number;
  lon: number;
  population: number;
  color: string;
  z?: number; // z 속성 추가
}
const categoryNameMapping: { [key: string]: string } = {
  "1": "IT",
  "2": "교육",
  "3": "마케팅",
  "4": "기획",
  "5": "경영",
};
const User = () => {
  const [totalMembers, setTotalMembers] = useState<number | null>(null); //총회원수
  const [dailyVisitors, setDailyVisitors] = useState<number | null>(null); // 일일방문자수
  const [totalIntnos, setTotalIntnos] = useState<number | null>(null); // 총 면접수
  const [totalMemCon, setTotalMemCon] = useState<number | null>(null); // 총 면접수

  const [chartData, setChartData] = useState({
    xAxisData: [] as string[], // x축 데이터
    seriesData: [] as number[], // y축 데이터
  });

  const [topologyData, setTopologyData] = useState<any>(null);
  const [capitalsData, setCapitalsData] = useState<CapitalData[]>([]);

  // 총 회원수를 가져오는 함수
  const fetchTotalMembers = async () => {
    try {
      const response = await axios.get(
        "http://192.168.0.73:81/yourdream/api/members/count"
      );
      setTotalMembers(response.data);
    } catch (error) {
      console.error("Failed to fetch total members", error);
    }
  };

  // 일일방문자수
  const fetchDailyVisitors = async () => {
    try {
      const today = new Date().toISOString().split("T")[0]; // YYYY-MM-DD 형식으로 오늘 날짜 가져오기
      const response = await axios.get(
        `http://192.168.0.73:81/yourdream/api/members/daily-visit-count?date=${today}`
      );
      setDailyVisitors(response.data);
    } catch (err) {
      console.error("일일방문자수 에러", err);
    }
  };

  //총 인터뷰수
  const fetchTotalIntnos = async () => {
    try {
      const response = await axios.get(
        "http://192.168.0.73:81/yourdream/api/members/int-count"
      );
      setTotalIntnos(response.data);
    } catch (err) {
      console.error("총면접수 에러", err);
    }
  };

  const fetchTotalMemCon = async () => {
    try {
      const response = await axios.get(
        "http://192.168.0.73:81/yourdream/api/members/con-count"
      );
      setTotalMemCon(response.data);
    } catch (err) {
      console.error("총매칭수 에러", err);
    }
  };

  const [categoryChartData, setCategoryChartData] = useState<
    { value: number; name: string }[]
  >([]);

  useEffect(() => {
    fetchTotalMembers();
    fetchDailyVisitors();
    fetchTotalIntnos();
    fetchTotalMemCon();
    const fetchData = async () => {
      try {
        // 한국지도 요청
        const topologyResponse = await axios.get(
          "https://code.highcharts.com/mapdata/countries/kr/kr-all.topo.json"
        );
        setTopologyData(topologyResponse.data);

        // 데이터를 back에서 요청해서 받아서 하시면 됩니다.
        const capitals: CapitalData[] = [
          {
            name: "서울",
            lat: 37.5665,
            lon: 126.978,
            population: 9720846,
            color: "#FF0000",
          },
          {
            name: "부산",
            lat: 35.1796,
            lon: 129.0756,
            population: 3413841,
            color: "#00FF00",
          },
          {
            name: "대구",
            lat: 35.8714,
            lon: 128.6014,
            population: 2466052,
            color: "#0000FF",
          },
          {
            name: "인천",
            lat: 37.7563,
            lon: 128.3052,
            population: 2947217,
            color: "#FFFF00",
          },
          {
            name: "광주",
            lat: 35.4595,
            lon: 126.9526,
            population: 1476974,
            color: "#00FFFF",
          },
          {
            name: "대전",
            lat: 36.4504,
            lon: 127.2845,
            population: 1486849,
            color: "#FF00FF",
          },
          {
            name: "울산",
            lat: 35.5384,
            lon: 129.3114,
            population: 1143694,
            color: "#800080",
          },
          {
            name: "제주",
            lat: 33.4,
            lon: 126.4983,
            population: 675883,
            color: "#008000",
          },
        ];

        // 새로운 API 요청 (일별 회원 수)
        const endDate = new Date();

        // 일주일 전 날짜 구하기
        const startDate = new Date();
        startDate.setDate(endDate.getDate() - 7);
        const formatDate = (date: Date) => {
          const year = date.getFullYear();
          const month = String(date.getMonth() + 1).padStart(2, "0");
          const day = String(date.getDate()).padStart(2, "0");
          return `${year}-${month}-${day}`;
        };
        const formattedStartDate = formatDate(startDate);
        const formattedEndDate = formatDate(endDate);
        const dailyCountsResponse = await axios.get(
          "http://192.168.0.73:81/yourdream/api/members/dailyCounts",
          {
            params: {
              startDate: formattedStartDate,
              endDate: formattedEndDate,
            },
          }
        );
        const data = dailyCountsResponse.data;
        const labels = Object.keys(data);
        const values = Object.values(data);
        setChartData({
          xAxisData: labels.map((date) => `${date.split("월")[1]}`), // x축 데이터
          seriesData: values as number[], // y축 데이터
        });

        // Process data
        capitals.forEach((p) => {
          p.z = p.population;
        });

        setCapitalsData(capitals);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []);
  // 카테고리 데이터 가져오기
  useEffect(() => {
    const fetchCategoryData = async () => {
      try {
        const response = await axios.get(
          "http://192.168.0.73:81/yourdream/api/members/categcdCounts"
        );
        const data = response.data;

        // 데이터를 카테고리 차트 형식에 맞게 변환
        const formattedCategoryData = Object.keys(data).map((key) => ({
          value: data[key],
          name: categoryNameMapping[key] || `Category ${key}`, // 카테고리 이름 설정
        }));

        setCategoryChartData(formattedCategoryData); // 카테고리 차트 데이터 설정
      } catch (error) {
        console.error("Error fetching category data:", error);
      }
    };

    fetchCategoryData();
  }, []);

  const chartOptions = {
    chart: {
      backgroundColor: "rgb(255, 255, 255)", //배경색
    },
    title: {
      text: "", //제목
      style: {
        fontFamily: "Jua",
      },
    },
    series: [
      {
        name: "Basemap",
        mapData: topologyData,
        accessibility: {
          exposeAsGroupOnly: true,
        },
        borderColor: "#606060", // 지도 선색
        nullColor: "rgba(200, 200, 200, 0.1)", // 지도색
        showInLegend: false,
      },
      {
        type: "mapbubble",
        dataLabels: {
          enabled: true,
          format: "{point.capital}",
          style: {
            color: "white", // 텍스트 색상을 기본값으로 설정
            textOutline: "none", // 텍스트 외곽선을 없앰
          },
        },
        accessibility: {
          point: {
            valueDescriptionFormat:
              "하하, " +
              "{point.parentState}. Population {point.population}. " +
              "Latitude {point.lat:.2f}, longitude {point.lon:.2f}.",
          },
        },
        name: "이이잉",
        data: capitalsData,
        maxSize: "12%",
        color: Highcharts.getOptions().colors,
        tooltip: {
          pointFormat:
            "{point.name}<br>회원명 또는 비율을 여기에 써주세요!: {point.population}",
        },
      },
    ],
  };
  /* 하이차트 */

  const option1 = {
    xAxis: {
      type: "category",
      boundaryGap: false,
      data: chartData.xAxisData,
    },
    yAxis: {
      type: "value",
    },
    series: [
      {
        data: chartData.seriesData,
        type: "line",
        areaStyle: {},
      },
    ],
  };
  const chartpie = {
    series: [
      {
        name: "Category Distribution",
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
        data: categoryChartData, // API에서 받아온 카테고리 데이터
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
  return (
    <div className="user-container">
      <div className="user-header">
        <div>
          <p className="user-text">&nbsp;User DashBoard</p>
        </div>
      </div>
      <br></br>
      <div className="user-dash">
        <div className="user-info1">
          <div>
            <h3 className="user-info-text">누적 회원수</h3>
            <div>
              <p className="user-info-textinfo">
                {totalMembers !== null
                  ? `${totalMembers.toLocaleString()}명`
                  : "로딩 중..."}
              </p>
            </div>
          </div>
          <div className="user-info2">
            <img src="/img/admin_img/people.svg" className="user-svg"></img>
          </div>
        </div>
        <div className="user-info1">
          <div>
            <h3 className="user-info-text">일일 방문자</h3>
            <div>
              <p className="user-info-textinfo">
                {dailyVisitors !== null
                  ? `${dailyVisitors.toLocaleString()}명`
                  : "로딩 중..."}
              </p>
            </div>
          </div>
          <div className="user-info2">
            <img src="/img/admin_img/person.svg" className="user-svg"></img>
          </div>
        </div>
        <div className="user-info1">
          <div>
            <h3 className="user-info-text">AI면접 횟수</h3>
            <div>
              <p className="user-info-textinfo">
                {totalIntnos !== null
                  ? `${totalIntnos.toLocaleString()}번`
                  : "로딩 중..."}
              </p>
            </div>
          </div>
          <div className="user-info2">
            <img src="/img/admin_img/dollar.svg" className="user-svg"></img>
          </div>
        </div>
        <div className="user-info1">
          <div>
            <h3 className="user-info-text">컨설팅 신청횟수</h3>
            <div>
              <p className="user-info-textinfo">
                {totalMemCon !== null
                  ? `${totalMemCon.toLocaleString()}번`
                  : "로딩 중..."}
              </p>
            </div>
          </div>
          <div className="user-info2">
            <img src="/img/admin_img/dollars.svg" className="user-svg"></img>
          </div>
        </div>
      </div>

      <div className="user-chart">
        <div className="user-chart-col">
          <div className="user-chart-row" style={{ width: "800px" }}>
            <div className="chart-name">주간 유저 증가량</div>
            <ReactEcharts
              option={option1}
              style={{ height: "400px", width: "100%" }}
            />
          </div>
          <div className="user-chart-row" style={{ width: "480px" }}>
            <div className="chart-name">희망 직군</div>
            <ReactEcharts
              option={chartpie}
              style={{ height: "400px", width: "100%" }}
            />
          </div>
        </div>
        <div className="user-chart-col">
          <div className="user-chart-row" style={{ width: "480px" }}>
            <div className="chart-name">지역별 이용도</div>
            <div>
              {topologyData && (
                <HighchartsReact
                  highcharts={Highcharts}
                  constructorType={"mapChart"}
                  options={chartOptions}
                />
              )}
            </div>
          </div>
          <div className="user-chart-row" style={{ width: "800px" }}>
            <div className="chart-name">누적 컨설팅횟수</div>
            <ReactEcharts
              option={chartzi}
              style={{ height: "400px", width: "100%" }}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default User;
