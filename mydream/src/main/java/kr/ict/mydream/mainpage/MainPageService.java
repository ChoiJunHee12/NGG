package kr.ict.mydream.mainpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ict.mydream.vo.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MainPageService {

        @Autowired
        private MainPageDao mainPageDao;

        // 회원 정보 조회
        public MemberVO getMemberDetail(int memno) {
                return mainPageDao.getMemberDetail(memno);
        }

        // 컨설턴트 정보 조회
        public ConsultVO getConsultantDetail(int cnsno) {
                return mainPageDao.getConsultantDetail(cnsno);
        }

        // 회원-컨설턴트 매핑 정보 조회
        public MemberConsultVO getMemberConsultantMapping(int memno) {
                return mainPageDao.getMemberConsultantMapping(memno);
        }

        // 스트레스율 계산
        public int calculateStressRate(int intno) {
                List<IntDetailVO> latestDetails = mainPageDao.getLatestEmotionDetails(intno);
                if (latestDetails.isEmpty()) {
                        return 0; // 데이터가 없는 경우 0점 반환
                }

                // 변수 초기화
                float totalGood = 0;
                float totalSoso = 0;
                float totalBad = 0;
                float totalCount = 0;

                // 감정 데이터 집계
                for (IntDetailVO detail : latestDetails) {
                        totalGood += detail.getEcntgood();
                        totalSoso += detail.getEcntsoso();
                        totalBad += detail.getEcntbad();
                        totalCount += detail.getEcntgood() + detail.getEcntsoso() + detail.getEcntbad();
                }

                // 감정 비율 계산
                float goodRatio = totalGood / totalCount;
                float sosoRatio = totalSoso / totalCount;
                float badRatio = totalBad / totalCount;

                // 스트레스율 계산 (부정 감정의 비율을 기반으로 계산)
                float stressRate = badRatio * 100; // 부정 감정의 비율을 100으로 곱하여 스트레스율로 변환

                // 스트레스율을 정수로 변환
                return Math.round(Math.max(stressRate, 0)); // 스트레스율이 음수로 나올 수 없으므로 0 이상으로 조정
        }

        // 음성 점수 계산
        public int calculateVoiceRate(int intno) {
                List<IntDetailVO> latestDetails = mainPageDao.getLatestVoiceDetails(intno);
                float totalScore = 0;
                int questionCount = latestDetails.size();

                if (questionCount == 0) {
                        return 0; // 데이터가 없는 경우 0점 반환
                }

                for (IntDetailVO detail : latestDetails) {
                        // vhertz와 vamplit은 그대로 사용하고, vempty는 점수에 부정적인 영향을 미치도록 가중치 설정
                        float vhertz = detail.getVhertz();
                        float vamplit = detail.getVamplit();
                        float vempty = detail.getVempty();

                        // 음성 공백 시간(vempty)이 길수록 점수에 더 큰 부정적 영향을 주도록 가중치 적용
                        float weightForVempty = 2.0f; // 가중치 값, 필요에 따라 조절 가능
                        float voiceScore = vhertz + vamplit - (vempty * weightForVempty);
                        voiceScore = Math.max(voiceScore, 0); // 음성 점수는 0보다 작을 수 없도록 보정

                        totalScore += voiceScore;
                }

                float averageVoiceScore = totalScore / questionCount;
                float maxPossibleScore = 100;
                float calculatedScore = (averageVoiceScore / maxPossibleScore) * 100;

                // 점수를 정수로 변환
                return Math.round(Math.max(calculatedScore, 0));
        }

        // 자세 불량 점수 계산
        public int calculatePostureBadCountRate(int intno) {
                List<IntDetailVO> latestDetails = mainPageDao.getLatestPostureDetails(intno);
                if (latestDetails.isEmpty()) {
                        return 0; // 데이터가 없는 경우 0점 반환
                }

                float totalBadCount = 0;
                for (IntDetailVO detail : latestDetails) {
                        totalBadCount += detail.getPbadcnt();
                }

                float averageBadCount = totalBadCount / latestDetails.size();
                float maxPossibleBadCount = 100; // 예를 들어 최대 불량 횟수가 100으로 가정
                float badCountRate = ((maxPossibleBadCount - averageBadCount) / maxPossibleBadCount) * 100;

                // 점수를 정수로 변환
                return Math.round(Math.max(badCountRate, 0)); // 점수가 음수로 나올 수 없도록 0 이상으로 조정
        }

        // 컨설턴트 평가 점수 조회
        public IntResVO getConsultantScore(int intno) {
                return mainPageDao.getLatestConsultantScore(intno);
        }

        // 회원 일정 조회
        public List<SceduleVO> getSchedulesByMember(int memno) {
                return mainPageDao.getSchedulesByMember(memno);
        }

        // 컨설턴트 직무 질문& 답변 STT
        public List<IntDetailVO> getConsultantQuestions(int intno, List<Integer> qnos) {
                Map<String, Object> params = new HashMap<>();
                params.put("intno", intno);
                params.put("qnos", qnos);
                return mainPageDao.getConsultantQuestions(params);
        }

        // 컨설턴트 직무 질문&답변 피드백
        public List<ConsultevalVO> getConsultantFeedback(int memno, int cnsno, int intno, List<Integer> qnos) {
                Map<String, Object> params = new HashMap<>();
                params.put("memno", memno);
                params.put("cnsno", cnsno);
                params.put("intno", intno);
                params.put("qnos", qnos);
                return mainPageDao.getConsultantFeedback(params);
        }

        // 컨설턴트 피드백(총평)
        public String getConsultantTotalFeedback(int memno, int intno) {
                Map<String, Object> params = new HashMap<>();
                params.put("memno", memno);
                params.put("intno", intno);

                // IntResVO 객체를 반환받아 cnsfeedbk 필드 값을 추출
                IntResVO result = mainPageDao.getConsultantTotalFeedback(params);
                return result != null ? result.getCnsfeedbk() : null; // null 체크
        }

}