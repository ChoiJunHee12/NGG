package kr.ict.mydream.mainpage;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class InterviewReportDTO {
    private String mname;
    private String applpart;
    private String mphoneno;
    private String memail;
    private String prefarea;
    private String rating;
    private int scoretot;
    private int rank;
    private int totalcnt;
    private int percentile;
    private AnalysisResult analysisres;
    private int scoree;
    private int scorev;
    private int scorep;
    private int consultantScore;
    private String consultantName;
    private String stressLevel;
    private String voiceStability;
    private String postureBalance;
    private String consultantMsg;

    // 컨설팅
    private String jobquestion1;
    private String jobquestion2;
    private String jobanswer1;
    private String jobanswer2;
    private String consultantfeedback1;
    private String consultantfeedback2;
    private String consultanttotalfeedback;

    // 일정관리
    private List<ScheduleEvent> events;
    private List<DDay> dDays;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScheduleEvent {
        private String title;
        private String start;
        private String end;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DDay {
        private String title;
        private int daysLeft;
    }

    public enum AnalysisResult {
        GOOD, AVERAGE, POOR
    }

    // 하이차트
    private List<PieChartData> pieChartData; // 감정분석결과
    private List<LogChartData> logChartData; // 음성분석결과
    private List<DetailedAnalysisData> detailedAnalysisData; // 자세 분석 결과
    private List<ConsistencyAnalysisData> consistencyAnalysisData; // 일관성 분석 결과

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PieChartData {
        private String emotion; // 감정이름
        private double emotionrate; // 감정 비율
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LogChartData {
        private String question; // 질문
        private int voiceStabilityscore; // 안정도 점수
        private String color; // 색상
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailedAnalysisData {
        private String question; // 질문
        private int postureBalancescore; // 자세 총 점수?
        private int postureBalanceavgscore; // 선형 그래프에 사용할 추가 데이터 (예: 평균점수?)
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ConsistencyAnalysisData {
        private String date; // 날짜
        private int feedbackscore; // 피드백 점수
    }

}