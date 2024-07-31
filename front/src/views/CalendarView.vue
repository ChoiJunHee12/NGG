<template>
<!-- 설치 필요 !!!
   -->
   <!-- Calendar Start -->     
  <div class="container">
    <div class="calendar-container">
      <div id="calendar-app">
        <h1 style="margin-top: 20px; margin-bottom: 20px">일정관리</h1>
         <div class="calendar-header">
            <button class="prev-month" @click="changeMonth(-1)">◀</button>
            <h5 class="current-month">{{ currentMonth.format("YYYY년 MMMM") }}</h5>
            <button class="next-month" @click="changeMonth(1)">▶</button>
        </div>
        <div class="calendar">
          <div v-for="(weekday, index) in weekdays" :key="weekday" :class="['calendar-day', 'calendar-fixed-day', { 'saturday-day': index === 6 }, {'sunday-day': index === 0 }]">
            {{ weekday }}
          </div>
          <div
            v-for="day in calendar"
            :key="day.dateKey"
            :class="['calendar-day', { 'saturday-day': isSaturday(day.date), 'sunday-day': isSunday(day.date) }]"
            @click="showEventModal(day.date)"
          >
            {{ day.date ? day.date.date() : "" }}
            <ul class="calendar-event-list">
              <li
                class="calendar-event-val"
                v-for="event in day.events"
                :key="event.event"
                :style="{ backgroundColor: event.color }"              >
                {{ event.event }}
              </li>
            </ul>
          </div>
        </div>
      </div>

      <!-- 조회하는 부분 -->    
      <div v-if="isEventModalOpen && selectedDateEvents.length > 0" class="calendar-event-modal" >
        <div class="calendar-modal-top" :style="{ backgroundColor: getModalBackgroundColor() }">
          <h3>{{ selectedDate.format("YYYY-MM-DD") }}</h3>
          <h4>{{ selectedDate.format("dddd") }}</h4>
        </div>
        
        <ul class="calendar-event-list">
          <li v-for="event in selectedDateEvents" :key="event.event">
            <div>
              <label class="form-label">제목:</label>
               <div v-if="!isEditing" class="form-control content-div">
                {{ event.event }}
              </div>
              <input v-if="isEditing" class="form-control" v-model="newEvent" type="text" />
            </div>
            <div>
              <label class="form-label">내용</label>
               <div v-if="!isEditing" class="form-control" style="flex: 1; min-height: 100px; overflow: auto; margin-bottom:50px;">
                {{ event.content }}
              </div>
              <textarea v-if="isEditing" class="form-control" v-model="newContent" rows="5"></textarea>
            </div>
          </li>
        </ul>
       <button v-if="!isEditing" class="calendar-modify-button" @click="editEvent">수정</button>
       <button v-if="isEditing" class="calendar-write-button" @click="saveEditedEvent">저장</button>
       <button class="calendar-modify-button" @click="deleteEvent">삭제</button>
       <button class="calendar-close-button" @click="closeEventModal">닫기</button>
     </div>

      <!-- 입력하는 부분 -->
      <div v-else-if="isEventModalOpen" class="calendar-event-modal">
        <div class="calendar-modal-top" :style="{ backgroundColor: getModalBackgroundColor() }">
          <h3>{{ selectedDate.format("YYYY-MM-DD") }}</h3>
          <h4>{{ selectedDate.format("dddd") }}</h4>
        </div>
        <label class="form-label" for="title">제목:</label>
        <input class="form-control" ref="title" type="text" v-model="newEvent" id="title" />
        <div>
          <label for="calcontent" class="form-label">내용</label>
        </div>
        <div style="margin-bottom:50px;">
          <textarea class="form-control" id="calcontent" rows="5" v-model="newContent"></textarea>
        </div>

        <button class="calendar-write-button" @click="addEvent">등록</button>
        <button class="calendar-close-button" @click="closeEventModal">닫기</button>
      </div>
     </div>
    
  </div>
    <!-- Calendar End -->
</template>

<script>

import moment from 'moment';
import 'moment/locale/ko';

moment.locale('ko'); // moment 로케일을 한국어로 설정

export default {
  data() {
    return {
      currentMonth: moment(), // 현재 표시되는 월
      calendar: [], // 월별 달력 데이터 저장
      newEvent: "", // 추가되는 이벤트 저장      
      selectedDate: null, // 선택한 날짜
      isEventModalOpen: false, // 이벤트 모달창 오픈 여부
      events: [], // 모든 이벤트 목록 저장
      weekdays: moment.weekdaysShort(), // 요일 이름 배열
      newContent: "", // 내용 추가 
      isEditing: false, // 수정 모드 여부
      editingEvent: null, // 수정 중인 이벤트
    };
  },
  methods: {
    generateCalendar() {
      // 현재 월의 각 날짜 배열
      const startOfMonth = this.currentMonth.clone().startOf("month");
      const endOfMonth = this.currentMonth.clone().endOf("month");
      const firstDayOfWeek = startOfMonth.day();
      const daysInMonth = endOfMonth.date();

      let calendar = [];

      for (let i = 1; i <= daysInMonth; i++) {
        const currentDate = moment([
          this.currentMonth.year(),
          this.currentMonth.month(),
          i,
        ]);
        calendar.push({
          date: currentDate,
          dateKey: currentDate.format("YYYY-MM-DD"),
          events: this.getEventsForDate(currentDate),
        });
      }

      for (let i = 0; i < firstDayOfWeek; i++) {
        calendar.unshift({ date: null, dateKey: `empty-${i}`, events: [] });
      }

      this.calendar = calendar;
    },

    changeMonth(step) {
      // 월 변경
      if (!this.isEventModalOpen) {
        this.currentMonth = this.currentMonth.clone().add(step, "month");
        this.generateCalendar();
      }
    },

    showEventModal(date) {
      // 클릭된 날짜가 null인 경우 처리
      if (!date || !date.date) {
        return;
      }
      // 선택한 날짜의 이벤트 모달 오픈
      this.selectedDate = date;
      this.isEventModalOpen = true;
      this.isEditing = false;
      this.editingEvent = null;

      // 레이아웃 변경
      const calendarContainer = document.querySelector('.calendar-container');
      if (calendarContainer) {
        calendarContainer.classList.add('expanded');
      }

      this.$nextTick(() => {
        if (this.selectedDateEvents.length === 0) {
          this.$refs.title.focus();
        }
      });
    },

    closeEventModal() {
      // 이벤트 모달 닫기
      this.isEventModalOpen = false;
      this.selectedDate = null;
      this.newEvent = "";
      this.newContent = "";

       // 레이아웃 복구
      const calendarContainer = document.querySelector('.calendar-container');
      if (calendarContainer) {
        calendarContainer.classList.remove('expanded');
      }
    },


     addEvent() {
      // 해당 날짜, 목록에 이벤트 추가
      const eventColors = {
        event1: "#FED2B5",
        event2: "#ADDCC8",
        event3: "#ADCDFF",
      };
      if (this.selectedDate) {
        const selectedDay = this.calendar.find(
          (day) => day.date && day.date.isSame(this.selectedDate, "day")
        );

        if (selectedDay) {
          const eventId = `event${selectedDay.events.length + 1}`;
          const eventColor = eventColors[eventId] || "#CCCCCC";
          selectedDay.events.push({ event: this.newEvent, content: this.newContent, color: eventColor });

          this.events.push({
            date: this.selectedDate,
            event: this.newEvent,  
            content: this.newContent,
            color: eventColor,
          });
        }
      }
      this.newEvent = "";
      this.newContent = "";
      this.closeEventModal();
    },

    editEvent() {
      // 선택한 이벤트 수정 모드로 전환
      if (this.selectedDateEvents.length > 0) {
        this.isEditing = true;
        const event = this.selectedDateEvents[0];
        this.editingEvent = event;
        this.newEvent = event.event;
        this.newContent = event.content;
         
        this.$nextTick(() => {
          if (this.selectedDateEvents.length === 0) {
            this.$refs.title.focus();
          }        
        });
      }
    },

    saveEditedEvent() {
      // 수정된 이벤트 저장
      if (this.editingEvent) {
        this.editingEvent.event = this.newEvent;
        this.editingEvent.content = this.newContent;
        this.generateCalendar(); // Update calendar
        this.closeEventModal();
      }
    },

    deleteEvent() {
      // 선택한 이벤트 삭제
      const index = this.events.findIndex(
        (event) => event.date.isSame(this.selectedDate, "day")
      );
      if (index > -1) {
        this.events.splice(index, 1);
        this.generateCalendar();
        this.closeEventModal();
      }
    },

    getEventsForDate(date) {
      // 이벤트 목록 반환
      return this.events
        .filter((event) => event.date.isSame(date, "day"));
    },

     isWeekend(date) {
      if (!date) return false;
      const day = date.day();
      return day === 0 || day === 6; // 0: Sunday, 6: Saturday
    },

    isSaturday(date) {
      if (!date) return false;
      return date.day() === 6; // 6: Saturday
    },

    isSunday(date) {
      if (!date) return false;
      return date.day() === 0; // 0: Sunday
    },
    getModalBackgroundColor() {
      if (!this.selectedDate) return '#ffffff'; // 기본 배경색
      const day = this.selectedDate.day();
      if (day === 6) return '#1659de'; // 토요일
      if (day === 0) return 'red'; // 일요일
      return '#326653'; // 평일
    },
  },

  computed: {
    selectedDateEvents() {
      // 현재 선택된 날짜의 이벤트 목록 반환
      return this.getEventsForDate(this.selectedDate);
    },
  },

  mounted() {
  this.generateCalendar();

  this.$nextTick(() => {
    // activity Chart
    var activityctx = document.getElementById("activityChart")?.getContext("2d");
    if (activityctx) {
      var activityChartData = {
        labels: [
          "Jan",
          "Feb",
          "Mar",
          "Apr",
          "May",
          "Jun",
          "Jul",
          "Aug",
          "Sep",
          "Oct",
          "Nov",
          "Dec",
        ],
        datasets: [
          {
            label: "Activity Data",
            borderColor: "rgba(255, 105, 180, 1)",
            borderWidth: 2,
            data: [65, 59, 80, 81, 56, 55, 40, 30, 20, 50, 70, 60],
          },
        ],
      };

      new Chart(activityctx, {
        type: "line",
        data: activityChartData,
        options: {
          scales: {
            y: {
              beginAtZero: true,
            },
          },
        },
      });
    }   
  });
}
}
</script>

<style>
.calendar-content {
  display: flex;
  flex-direction: row;
  width: 100%;
  max-width: 1280px;
  color: #000000; /* 텍스트 색상 추가 */
}
.calendar-header {
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  height: 5%;
}
.calendar-modal-top {
  border-radius: 5px;
  padding:10px;
  margin-top:130px; 
  margin-bottom:30px;
  color:#ffffff;
  background-color:#1659DE;
} 
.prev-month, .next-month {
  border-radius: 5px;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  background-color:#004aad ;
  color: #ffffff;
}
.prev-month {
  left: 0;
  margin-left:230px;
}
.next-month {
  right: 0;
  margin-right:230px;
}
.current-month {
  font-size: 1.5em;
  text-align: center;
  width: 100%;
}
#calendar-app {
  margin-right: 20px;
  border: 3px solid rgba(0, 0, 0, 0.3);
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  border-radius: 5px;
  overflow: hidden;
  width: 60%;
  text-align: center;
  background-color: #ffffff;
  color: #000000; /* 텍스트 색상 추가 */  
}
.calendar-event-modal {
  border: 3px solid rgba(0, 0, 0, 0.3);
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  border-radius: 5px;
  padding: 20px 20px;  
  width: 35%;
  background-color: #ffffff;
  position: sticky;
  top: 10%;
  color: #000000;   
  display: none; /* 처음엔 숨겨둠 */   
  height: 710px; /* 모달의 높이 설정 */
  transition: height 0.3s ease; /* 부드러운 전환 효과 추가 */
}
.calendar {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  margin: 20px;
  border: 2px solid rgba(23, 2, 82, 0.3);
  border-radius: 5px;
  overflow: hidden;  
  color: #000000; /* 텍스트 색상 추가 */
}
/* 달력의 클릭된 날짜에 대한 스타일 */
.calendar-day.clicked {
  background-color: #f0f0f0; /* 클릭된 날짜의 배경색 */
}
.calendar-day {
  padding: 5px;
  text-align: left;
  cursor: pointer;
  height: 100px;  
  border: 1px solid;
  border-color: #113280;
  overflow: hidden;
  color: #000000; /* 텍스트 색상 추가 */
}
.calendar-day:last-child {
  border-right: 2.5px solid ;
}
.calendar-fixed-day {
  background-color: #edeff1;
  font-weight: bold;
  text-align: center;
  height: 40px;
  color: #000000; /* 텍스트 색상 추가 */
}
.calendar-event-list {  
  margin-top: 20px;
  list-style: none;
  padding: 0;
  color: #000000; /* 텍스트 색상 추가 */  
}
.calendar-event-list li {  
  margin-bottom: 5px;
}
.calendar-event-val {
  overflow: hidden;
}
.calendar-write-button {
  background-color: #004aad;
  padding: 10px 20px;
  cursor: pointer;
  border-radius: 5px;
  width: 80px;
  border: none;
  color: #ffffff;
  font-size: 1.1rem;
  opacity: 1;
  transition: opacity 0.3s ease;
  margin: 5px;
}

.calendar-modify-button {
  background-color: #004aad;
  padding: 10px;
  cursor: pointer;
  border-radius: 5px;
  width: 80px;
  border: none;
  color: #ffffff;
  font-size: 1.1rem;
  opacity: 1;
  transition: opacity 0.3s ease;
  margin: 5px;
}

.calendar-write-button:hover {
  background-color: #005bcc;
  color: #f8f8f8;
  cursor: pointer;
  opacity: 0.5;
}
.calendar-close-button {
  background-color: #cdcdcd;
  padding: 10px 20px;
  cursor: pointer;
  border-radius: 5px;
  width: 80px;
  border: none;
  color: #010202;
  font-size: 1.1rem;
  opacity: 1;
  transition: opacity 0.3s ease;
  margin: 5px;
}
.saturday-day {
  color: blue;
}
.sunday-day {
  color: red;
}
/* 추가된 스타일 */
.calendar-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  transition: all 0.3s ease;
}
.calendar-container.expanded {
  justify-content: flex-start;
}
.calendar-container.expanded .calendar-event-modal {
  display: block; /* 클릭 시 보이게 */
}

@media (max-width: 968px) {
  .container {
    flex-direction: column;
  }
  #calendar-app,
  .calendar-event-modal {  
    width: 100%;
    margin: 0;
  }  
}
</style>