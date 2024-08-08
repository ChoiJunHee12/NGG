package kr.ict.mydream.mainpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kr.ict.mydream.vo.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/mainpage")
public class MainPageController {

    @Autowired
    private MainPageService mainPageService;

    // 회원 정보 조회
    @GetMapping("/memberDetail")
    public ResponseEntity<MemberVO> getMemberDetail(@RequestParam("memno") int memno) {
        MemberVO member = mainPageService.getMemberDetail(memno);
        return ResponseEntity.ok(member);
    }

    // 스트레스율 조회
    @GetMapping("/stressRate")
    public ResponseEntity<Float> getStressRate(@RequestParam("intno") int intno) {
        float stressRate = mainPageService.calculateStressRate(intno);
        return ResponseEntity.ok(stressRate);
    }

    // 음성 점수 조회
    @GetMapping("/voiceRate")
    public ResponseEntity<Float> getVoiceRate(@RequestParam("intno") int intno) {
        float voiceRate = mainPageService.calculateVoiceRate(intno);
        return ResponseEntity.ok(voiceRate);
    }

    // 자세 불량 점수 조회
    @GetMapping("/postureBadCountRate")
    public ResponseEntity<Float> getPostureBadCountRate(@RequestParam("intno") int intno) {
        float postureBadCountRate = mainPageService.calculatePostureBadCountRate(intno);
        return ResponseEntity.ok(postureBadCountRate);
    }

    // 컨설턴트 평가 점수 조회
    @GetMapping("/consultantScore")
    public ResponseEntity<IntResVO> getConsultantScore(@RequestParam("intno") int intno) {
        IntResVO consultantScore = mainPageService.getConsultantScore(intno);
        return consultantScore != null ? ResponseEntity.ok(consultantScore) : ResponseEntity.notFound().build();
    }

    // 컨설턴트 정보 조회
    @GetMapping("/consultantDetail")
    public ResponseEntity<ConsultVO> getConsultantDetail(@RequestParam("cnsno") int cnsno) {
        ConsultVO consultant = mainPageService.getConsultantDetail(cnsno);
        return consultant != null ? ResponseEntity.ok(consultant) : ResponseEntity.notFound().build();
    }

    // 회원-컨설턴트 매핑 정보 조회
    @GetMapping("/memberConsultantMapping")
    public ResponseEntity<MemberConsultVO> getMemberConsultantMapping(@RequestParam("memno") int memno) {
        MemberConsultVO mapping = mainPageService.getMemberConsultantMapping(memno);
        return mapping != null ? ResponseEntity.ok(mapping) : ResponseEntity.notFound().build();
    }

    // 회원 일정 조회
    @GetMapping("/memberSchedules")
    public ResponseEntity<List<SceduleVO>> getSchedulesByMembers(@RequestParam("memno") int memno) {
        List<SceduleVO> schedules = mainPageService.getSchedulesByMember(memno);
        return !schedules.isEmpty() ? ResponseEntity.ok(schedules) : ResponseEntity.notFound().build();
    }

    // 컨설턴트 직무 질문&답변 STT
    @GetMapping("/consultantQuestions")
    public List<IntDetailVO> getConsultantQuestions(
            @RequestParam("intno") int intno,
            @RequestParam(value = "qnos", required = false) List<Integer> qnos) {
        if (qnos == null || qnos.isEmpty()) {
            qnos = Arrays.asList(6, 7); // 기본값 설정
        }
        return mainPageService.getConsultantQuestions(intno, qnos);
    }

    // 컨설턴트 직무 답변 피드백
    @GetMapping("/consultantFeedback")
    public List<ConsultevalVO> getConsultantFeedback(
            @RequestParam("memno") int memno,
            @RequestParam("cnsno") int cnsno,
            @RequestParam("intno") int intno,
            @RequestParam(value = "qnos", required = false) List<Integer> qnos) {
        if (qnos == null || qnos.isEmpty()) {
            qnos = Arrays.asList(6, 7); // 기본값 설정
        }
        return mainPageService.getConsultantFeedback(memno, cnsno, intno, qnos);
    }

    // 컨설턴트 피드백(총평)
    @GetMapping("/consultantTotalFeedback")
    public String getConsultantTotalFeedback(@RequestParam("memno") int memno, @RequestParam("intno") int intno) {
        return mainPageService.getConsultantTotalFeedback(memno, intno);
    }

}