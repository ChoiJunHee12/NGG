package kr.ict.mydream.mainpage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class InterviewReportService {

        public InterviewReportDTO getInterviewReport(String id) {
                InterviewReportDTO dto = new InterviewReportDTO();

                switch (id) {
                        case "1":
                                // 기본 정보 설정
                                dto.setMname("박보영");
                                dto.setApplpart("IT/SI 면접자");
                                dto.setMphoneno("010-1111-3333");
                                dto.setMemail("lottone27@naver.com");
                                dto.setPrefarea("서울");
                                dto.setRating("B");
                                dto.setScoretot(51);
                                dto.setRank(1);
                                dto.setTotalcnt(1);
                                dto.setPercentile(100);
                                dto.setAnalysisres(InterviewReportDTO.AnalysisResult.AVERAGE);
                                dto.setStressLevel("스트레스가 높아요");
                                dto.setVoiceStability("목소리가 불안정해요");
                                dto.setPostureBalance("자세균형이 좋아요");
                                dto.setConsultantMsg("일관성이 떨어져요");
                                dto.setScoree(80);
                                dto.setScorev(35);
                                dto.setScorep(70);
                                dto.setConsultantScore(45);
                                dto.setConsultantName("홍길동");
                                dto.setJobquestion1("RESTful API의 원칙과 이를 구현할 때 고려해야 할 사항은 무엇인가요?");
                                dto.setJobquestion2("데이터베이스 인덱스의 종류와 이를 사용하는 이유는 무엇인가요?");
                                dto.setJobanswer1(
                                                "RESTful API는 클라이언트-서버 구조, 무상태성, 캐시 가능성, 계층 구조, 인터페이스 일관성, 그리고 코드 온디맨드라는 6가지 원칙에 기반합니다. 이를 구현할 때는 URL 구조의 일관성, HTTP 메서드의 올바른 사용(GET, POST, PUT, DELETE 등), 그리고 적절한 응답 코드(200, 404, 500 등)를 고려해야 합니다.");
                                dto.setJobanswer2(
                                                "데이터베이스 인덱스는 검색 속도를 높이기 위해 사용됩니다.\r\n인덱스의 종류에는 B-Tree 인덱스, 해시 인덱스, 비트맵 인덱스,\r\n그리고 전체 텍스트 인덱스 등이 있습니다. B-Tree 인덱스는 범위\r\n검색에 유리하고, 해시 인덱스는 정확한 값 검색에 적합합니다.");
                                dto.setConsultantfeedback1(
                                                "이 대답은 RESTful API의 주요 원칙을 잘 설명하고 있습니다. 그러나 더 구체적으로 설명할 수 있는 기회를 놓친 것 같습니다. 예를 들어, 클라이언트-서버 구조의 장점(서버와 클라이언트의 독립적 발전 가능성)이나 무상태성의 이점(서버 확장성 향상)을 추가적으로 언급하면 좋습니다. 또한, 실제 프로젝트에서 이러한 원칙을 어떻게 적용했는지 구체적인 예시를 제공하면 더 설득력 있는 답변이 될 수 있습니다.");
                                dto.setConsultantfeedback2(
                                                "이 대답은 인덱스의 기본 개념과 주요 유형을 잘 설명하고 있습니다.\r\n그러나 인덱스 사용의 장단점, 예를 들어 인덱스가 검색 성능을 높이는\r\n동시에 쓰기 성능을 저하시킬 수 있다는 점도 언급하면 좋습니다.\r\n또한, 실제 경험에서 인덱스를 사용하여 성능을 최적화한 사례를 들어\r\n설명하면 면접관에게 더 깊은 인상을 줄 수 있습니다.");
                                dto.setConsultanttotalfeedback(
                                                "당신의 답변은 기본적인 기술 개념을 잘 설명하고 있으며, RESTful API와\r\n데이터베이스 인덱스에 대한 주요 원칙과 종류를 명확하게 이해하고 있는\r\n것으로 보입니다. 이는 기술적 기초가 탄탄하다는 것을 보여주는 긍정적인\r\n신호입니다. 하지만, 이론적인 설명에 그치지 않고, 더 구체적인 예시나\r\n실제 경험을 통해 답변을 풍부하게 만들면 더 좋을 것입니다. 조언:\r\n구체적인 예시 추가: RESTful API와 데이터베이스 인덱스를 실제\r\n프로젝트에서 어떻게 적용했는지 구체적인 사례를 제공해 보세요. 예를\r\n들어, 특정 프로젝트에서 API 설계를 어떻게 했는지, 그리고 인덱스를\r\n사용하여 어떻게 쿼리 성능을 최적화했는지 설명하면 면접관이 당신의 실무\r\n능력을 더 잘 이해할 수 있습니다. 장단점 및 고려사항 언급: RESTful\r\nAPI와 데이터베이스 인덱스 사용의 장단점을 설명하고, 이를 구현할 때\r\n고려해야 할 사항들을 덧붙이세요. 이러한 개선점을 통해 당신의 답변이\r\n더욱 구체적이고 실무적인 경험을 반영하게 되면, 면접관에게 강한 인상을\r\n남길 수 있을 것입니다.");

                                // 일정 이벤트 설정
                                List<InterviewReportDTO.ScheduleEvent> events = new ArrayList<>();
                                events.add(new InterviewReportDTO.ScheduleEvent("삼성전자", "2024-08-09", "2024-08-10"));
                                events.add(new InterviewReportDTO.ScheduleEvent("SK하이닉스", "2024-08-07", "2024-08-10"));
                                events.add(new InterviewReportDTO.ScheduleEvent("ICT", "2024-08-15", "2024-08-31"));
                                dto.setEvents(events);

                                // D-Day 설정
                                List<InterviewReportDTO.DDay> dDays = new ArrayList<>();
                                LocalDate today = LocalDate.now();
                                // System.out.println("Today's date: " + today); // 현재 날짜 출력 확인

                                for (InterviewReportDTO.ScheduleEvent event : events) {
                                        System.out.println("Processing event: " + event); // 이벤트 정보 출력
                                        InterviewReportDTO.DDay dDay = calculateDDay(event.getTitle(), event.getStart(),
                                                        today);
                                        if (dDay != null) {
                                                dDays.add(dDay);
                                        } else {
                                                System.out.println("No D-Day for event: " + event.getTitle()); // D-Day
                                                                                                               // 없음
                                        }
                                }
                                dto.setDDays(dDays);

                                // 하이차트 데이터 설정
                                dto.setPieChartData(getPieChartData()); // 감정분석결과
                                dto.setLogChartData(getLogChartData()); // 음성분석결과
                                dto.setDetailedAnalysisData(getDetailedAnalysisData()); // 자세 분석 결과
                                dto.setConsistencyAnalysisData(getConsistencyAnalysisData()); // 일관성 분석 결과

                                break;
                        default:
                                // 빈 데이터 객체를 반환하여 클라이언트에서 문제를 감지
                                return new InterviewReportDTO();
                }
                return dto;
        }

        public InterviewReportDTO.DDay calculateDDay(String title, String dateStr, LocalDate today) {
                try {
                        LocalDate eventDate = LocalDate.parse(dateStr);
                        // System.out.println("eventDate:" + eventDate); // 이벤트 날짜 출력 확인
                        long daysLeft = ChronoUnit.DAYS.between(today, eventDate);

                        if (daysLeft >= 0) {
                                System.out.println("D-Day for event: " + title + " is " + daysLeft + " days away"); // D-Day
                                                                                                                    // 출력
                                return new InterviewReportDTO.DDay(title, (int) daysLeft);
                        } else {
                                System.out.println("Event already passed: " + title); // 이벤트가 지나간 경우
                                return null;
                        }
                } catch (DateTimeParseException e) {
                        System.err.println("D-Day 계산 중 오류 발생: " + e.getMessage()); // 날짜 파싱 오류
                        return null;
                }

        }

        public List<InterviewReportDTO.PieChartData> getPieChartData() {
                List<InterviewReportDTO.PieChartData> pieChartData = new ArrayList<>();
                pieChartData.add(new InterviewReportDTO.PieChartData("졸림", 21.3));
                pieChartData.add(new InterviewReportDTO.PieChartData("화남", 18.7));
                pieChartData.add(new InterviewReportDTO.PieChartData("기쁨", 20.2));
                pieChartData.add(new InterviewReportDTO.PieChartData("슬픔", 14.2));
                pieChartData.add(new InterviewReportDTO.PieChartData("중립", 25.6));
                return pieChartData;
        }

        public List<InterviewReportDTO.LogChartData> getLogChartData() {
                List<InterviewReportDTO.LogChartData> logChartData = new ArrayList<>();
                logChartData.add(new InterviewReportDTO.LogChartData("Q1", 20, "#0000ff"));
                logChartData.add(new InterviewReportDTO.LogChartData("Q2", 30, "#8d0073"));
                logChartData.add(new InterviewReportDTO.LogChartData("Q3", 10, "#ba0046"));
                logChartData.add(new InterviewReportDTO.LogChartData("Q4", 10, "#d60028"));
                logChartData.add(new InterviewReportDTO.LogChartData("Q5", 30, "#eb0014"));
                return logChartData;
        }

        public List<InterviewReportDTO.DetailedAnalysisData> getDetailedAnalysisData() {
                List<InterviewReportDTO.DetailedAnalysisData> detailedAnalysisData = new ArrayList<>();
                detailedAnalysisData.add(new InterviewReportDTO.DetailedAnalysisData("Q1", 75, 80));
                detailedAnalysisData.add(new InterviewReportDTO.DetailedAnalysisData("Q2", 85, 85));
                detailedAnalysisData.add(new InterviewReportDTO.DetailedAnalysisData("Q3", 70, 75));
                detailedAnalysisData.add(new InterviewReportDTO.DetailedAnalysisData("Q4", 60, 65));
                detailedAnalysisData.add(new InterviewReportDTO.DetailedAnalysisData("Q5", 100, 55));
                return detailedAnalysisData;
        }

        public List<InterviewReportDTO.ConsistencyAnalysisData> getConsistencyAnalysisData() {
                List<InterviewReportDTO.ConsistencyAnalysisData> consistencyAnalysisData = new ArrayList<>();
                consistencyAnalysisData.add(new InterviewReportDTO.ConsistencyAnalysisData("2024-08-01", 85));
                consistencyAnalysisData.add(new InterviewReportDTO.ConsistencyAnalysisData("2024-08-02", 90));
                consistencyAnalysisData.add(new InterviewReportDTO.ConsistencyAnalysisData("2024-08-03", 80));
                consistencyAnalysisData.add(new InterviewReportDTO.ConsistencyAnalysisData("2024-08-04", 70));
                consistencyAnalysisData.add(new InterviewReportDTO.ConsistencyAnalysisData("2024-08-05", 75));
                return consistencyAnalysisData;
        }

}