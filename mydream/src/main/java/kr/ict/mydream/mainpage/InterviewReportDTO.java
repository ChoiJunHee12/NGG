package kr.ict.mydream.mainpage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class InterviewReportDTO {
    private String mname; // 이름
    private String applpart; // 지원분야
    private String mphoneno; // 전화번호
    private String memail; // 이메일
    private String prefarea; // 선호 지역
    private String rating; // 종합평가 (보통)
    private int scoretot; // 총 점수
    private String rank; // 1등
    private String totalcnt; // 1명
    private String percentile; // 상위 100%
    private String analysisres; // 분석 결과
    private int scoree; // 스트레스 수준
    private int scorev; // 음성 분석
    private int scorep; // 자세 분석
    private int consultantScore; // 컨설턴트 평가점수
    private String consultantName; // 컨설턴트 이름
    private String stressLevel; // 스트레스가 높아요
    private String voiceStability; // 목소리가 불안정해요
    private String postureBalance; // 자세균형이 좋아요
    private String consultantMsg; // 컨설턴트 평가: 일관성이 떨어져요
}
