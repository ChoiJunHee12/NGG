package kr.ict.mydream.mainpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ict.mydream.vo.*;
import java.util.Date;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                return mapping;
        }

        // 스트레스율 계산
        public int calculateStressRate(int intno, int memno) {
                Map<String, Object> params = new HashMap<>();
                params.put("intno", intno);
                params.put("memno", memno);

                List<IntDetailVO> latestDetails = mainPageDao.getLatestEmotionDetails(params);
                if (latestDetails.isEmpty()) {
                        return 0;
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
                        return 100; // 데이터가 없으면 최고 점수 반환
                }

                float totalBadCount = 0;
                for (IntDetailVO detail : latestDetails) {
                        totalBadCount += detail.getPbadcnt();
                }

                float avgPbadcnt = totalBadCount / latestDetails.size();
                float postureScore = 100 - avgPbadcnt;
                return Math.round(Math.max(postureScore, 0)); // 음수가 나오지 않도록 보정
        }

        // 컨설턴트 평가 점수 조회
        public IntResVO getConsultantScore(int intno) {
                return mainPageDao.getLatestConsultantScore(intno);
        }

        // 회원 일정 조회
        public List<ScheduleVO> getSchedulesByMember(int memno) {
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

                IntResVO result = mainPageDao.getConsultantTotalFeedback(params);
                return result != null ? result.getCnsfeedbk() : null;
        }

        // 최근 5개의 인성면접 데이터
        public Map<String, Object> calculateRecentInterviewScores(int memno) {
                List<IntDetailVO> interviewDetails = mainPageDao.getInterviewDetails(memno);

                List<Integer> stressRates = new ArrayList<>();
                List<Integer> postureScores = new ArrayList<>();
                List<Integer> voiceScores = new ArrayList<>();
                List<String> dates = new ArrayList<>();
                Map<String, List<Integer>> questionVoiceScores = new HashMap<>();
                Map<String, List<Map<String, Object>>> questionEmotionScores = new HashMap<>();

                Map<Integer, List<IntDetailVO>> groupedByIntno = interviewDetails.stream()
                                .collect(Collectors.groupingBy(IntDetailVO::getIntno));

                for (Map.Entry<Integer, List<IntDetailVO>> entry : groupedByIntno.entrySet()) {
                        List<IntDetailVO> interviewGroup = entry.getValue();

                        float totalEcntgood = 0, totalEcntsoso = 0, totalEcntbad = 0;
                        float totalPbadcnt = 0, totalVhertz = 0, totalVamplit = 0, totalVempty = 0;
                        int count = interviewGroup.size();
                        Date credt = null;

                        Map<Integer, Float> questionVoiceScoreMap = new HashMap<>();
                        Map<Integer, Map<String, Object>> questionEmotionScoreMap = new HashMap<>();

                        for (IntDetailVO detail : interviewGroup) {
                                totalEcntgood += detail.getEcntgood();
                                totalEcntsoso += detail.getEcntsoso();
                                totalEcntbad += detail.getEcntbad();
                                totalPbadcnt += detail.getPbadcnt();
                                totalVhertz += detail.getVhertz();
                                totalVamplit += detail.getVamplit();
                                totalVempty += detail.getVempty();
                                if (credt == null || detail.getCredt().after(credt)) {
                                        credt = detail.getCredt();
                                }

                                // 질문당 음성 점수 계산
                                float questionVoiceScore = calculateQuestionVoiceScore(detail);
                                questionVoiceScoreMap.put(detail.getQno(), questionVoiceScore);

                                // 질문당 감정 점수 및 카운트 계산
                                Map<String, Object> emotionData = calculateQuestionEmotionScore(detail);
                                questionEmotionScoreMap.put(detail.getQno(), emotionData);
                        }

                        // 스트레스율 계산
                        float totalEmotions = totalEcntgood + totalEcntsoso + totalEcntbad;
                        int stressRate = Math.round((totalEcntbad / totalEmotions) * 100);
                        stressRates.add(stressRate);

                        // 자세 점수 계산
                        int postureScore = Math.round(Math.max(100 - (totalPbadcnt / count), 0));
                        postureScores.add(postureScore);

                        // 전체 음성 점수 계산
                        float rawVoiceScore = ((totalVhertz + totalVamplit) / count) - ((totalVempty / count) * 2.0f);
                        int voiceScore = Math.round(Math.max(rawVoiceScore, 0) / 100 * 100);
                        voiceScores.add(voiceScore);

                        // 날짜 추가
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        dates.add(sdf.format(credt));

                        // 질문당 음성 점수 추가
                        for (Map.Entry<Integer, Float> questionScore : questionVoiceScoreMap.entrySet()) {
                                String key = "Q" + questionScore.getKey();
                                questionVoiceScores.computeIfAbsent(key, k -> new ArrayList<>())
                                                .add(Math.round(questionScore.getValue()));
                        }

                        // 질문당 감정 점수 및 카운트 추가
                        for (Map.Entry<Integer, Map<String, Object>> questionEmotion : questionEmotionScoreMap
                                        .entrySet()) {
                                String key = "Q" + questionEmotion.getKey();
                                questionEmotionScores.computeIfAbsent(key, k -> new ArrayList<>())
                                                .add(questionEmotion.getValue());
                        }
                }

                Map<String, Object> result = new HashMap<>();
                result.put("stressRates", stressRates);
                result.put("postureScores", postureScores);
                result.put("voiceScores", voiceScores);
                result.put("dates", dates);
                result.put("questionVoiceScores", questionVoiceScores);
                result.put("questionEmotionScores", questionEmotionScores);

                return result;
        }

        private float calculateQuestionVoiceScore(IntDetailVO detail) {
                float voiceScore = detail.getVhertz() + detail.getVamplit() - (detail.getVempty() * 2.0f);
                return Math.max(voiceScore, 0) / 100 * 100; // 0-100 스케일로 변환
        }

        private Map<String, Object> calculateQuestionEmotionScore(IntDetailVO detail) {
                float totalEmotions = detail.getEcntgood() + detail.getEcntsoso() + detail.getEcntbad();
                float emotionScore = (detail.getEcntgood() / totalEmotions) * 100; // 긍정 감정을 기준으로 점수 계산

                Map<String, Object> emotionData = new HashMap<>();
                emotionData.put("score", Math.round(emotionScore));
                emotionData.put("good", Math.round(detail.getEcntgood()));
                emotionData.put("soso", Math.round(detail.getEcntsoso()));
                emotionData.put("bad", Math.round(detail.getEcntbad()));

                return emotionData;
        }

        // 회원의 최근 인터뷰 정보를 가져옴
        public Map<String, Integer> getLatestInterviewInfo(int memno) {
                Map<String, Object> result = mainPageDao.getLatestInterviewInfo(memno);
                if (result == null || result.isEmpty()) {
                        return null;
                }

                Map<String, Integer> convertedResult = new HashMap<>();
                for (Map.Entry<String, Object> entry : result.entrySet()) {
                        if (entry.getValue() != null) {
                                if (entry.getValue() instanceof Integer) {
                                        convertedResult.put(entry.getKey(), (Integer) entry.getValue());
                                } else if (entry.getValue() instanceof BigDecimal) {
                                        convertedResult.put(entry.getKey(), ((BigDecimal) entry.getValue()).intValue());
                                } else {
                                        // 예외 처리 또는 로깅
                                        System.out.println("Unexpected type for key " + entry.getKey() + ": "
                                                        + entry.getValue().getClass());
                                }
                        }
                }
                return convertedResult;
        }
}