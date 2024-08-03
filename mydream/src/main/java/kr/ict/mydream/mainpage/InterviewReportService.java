package kr.ict.mydream.mainpage;

import org.springframework.stereotype.Service;

@Service
public class InterviewReportService {

    public InterviewReportDTO getInterviewReport(String id) {
        InterviewReportDTO dto = new InterviewReportDTO();

        // 문자열을 기반으로 스위치 문에서 처리 (테스트용)
        switch (id) {
            case "1":
                dto.setMname("박보영");
                dto.setApplpart("IT/SI 면접자");
                dto.setMphoneno("010-1111-2222");
                dto.setMemail("lottone27@naver.com");
                dto.setPrefarea("서울");
                dto.setRating("B");
                dto.setScoretot(51);
                dto.setRank("1");
                dto.setTotalcnt("1");
                dto.setPercentile("상위 100");
                dto.setAnalysisres("보통");
                dto.setStressLevel("스트레스가 높아요");
                dto.setVoiceStability("목소리가 불안정해요");
                dto.setPostureBalance("자세균형이 좋아요");
                dto.setConsultantMsg("일관성이 떨어져요");
                dto.setScoree(80);
                dto.setScorev(35);
                dto.setScorep(70);
                dto.setConsultantScore(45);
                dto.setConsultantName("홍길동");
                break;
            default:
                throw new RuntimeException("No data available for id: " + id);
        }
        return dto;
    }
}
