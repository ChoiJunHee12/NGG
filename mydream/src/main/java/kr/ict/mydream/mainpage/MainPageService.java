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

        // 회원-컨설턴트 매핑 및 컨설턴트 정보 조회
        public MemberConsultVO getMemberConsultantDetail(int memno) {
                MemberConsultVO mapping = mainPageDao.getMemberConsultantDetail(memno);
                return mapping; // 매핑된 컨설턴트 정보가 포함된 객체 반환
        }

        // 스트레스율 계산
        public int calculateStressRate(int intno, int memno) {
                Map<String, Object> params = new HashMap<>();
                params.put("intno", intno);
                params.put("memno", memno);

                List<IntDetailVO> latestDetails = mainPageDao.getLatestEmotionDetails(params);
                if (latestDetails.isEmpty()) {
                        return 0; // 데이터가 없는 경우 0점 반환
                }

                float totalGood = 0, totalSoso = 0, totalBad = 0, totalCount = 0;

                for (IntDetailVO detail : latestDetails) {
                        totalGood += detail.getEcntgood();
                        totalSoso += detail.getEcntsoso();
                        totalBad += detail.getEcntbad();
                        totalCount += detail.getEcntgood() + detail.getEcntsoso() + detail.getEcntbad();
                }

                float badRatio = totalBad / totalCount;
                float stressRate = badRatio * 100;
                return Math.round(Math.max(stressRate, 0));
        }

        // 음성 점수 계산
        public int calculateVoiceRate(int intno, int memno) {
                Map<String, Object> params = new HashMap<>();
                params.put("intno", intno);
                params.put("memno", memno);

                List<IntDetailVO> latestDetails = mainPageDao.getLatestVoiceDetails(params);
                float totalScore = 0;
                int questionCount = latestDetails.size();

                if (questionCount == 0) {
                        return 0;
                }

                for (IntDetailVO detail : latestDetails) {
                        float voiceScore = detail.getVhertz() + detail.getVamplit() - (detail.getVempty() * 2.0f);
                        totalScore += Math.max(voiceScore, 0);
                }

                return Math.round(Math.max((totalScore / questionCount) / 100 * 100, 0));
        }

        // 자세 불량 점수 계산
        public int calculatePostureBadCountRate(int intno, int memno) {
                Map<String, Object> params = new HashMap<>();
                params.put("intno", intno);
                params.put("memno", memno);

                List<IntDetailVO> latestDetails = mainPageDao.getLatestPostureDetails(params);
                if (latestDetails.isEmpty()) {
                        return 0;
                }

                float totalBadCount = 0;
                for (IntDetailVO detail : latestDetails) {
                        totalBadCount += detail.getPbadcnt();
                }

                float averageBadCount = totalBadCount / latestDetails.size();
                return Math.round(Math.max(((100 - averageBadCount) / 100) * 100, 0));
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
        public List<ConsultEvalVO> getConsultantFeedback(int memno, int cnsno, int intno, List<Integer> qnos) {
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