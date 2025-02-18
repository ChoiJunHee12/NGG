package kr.ict.mydream.itvres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ict.mydream.dto.ItvDTO;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/itv")
public class ItvResController {

    @Autowired
    private ItvService itvService;

    @GetMapping("/attitude")
    public List<Map<String, Object>> getAttitudeResults(
            @RequestParam(value = "memno", required = false) Integer memno) {
        return itvService.itvAttitudeList(memno);

    }

    @GetMapping("/career")
    public List<Map<String, Object>> getCareerResults(@RequestParam(value = "memno", required = false) Integer memno) {
        return itvService.itvCareerList(memno);
    }

    @GetMapping("/userData")
    public List<ItvDTO> fetchUserData(@RequestParam(value = "intno", required = false) Integer intno) {
        System.out.println(itvService.detail(intno));
        return itvService.detail(intno);
    }

    @GetMapping("/fetchData")
    public List<Map<String, String>> questionData(@RequestParam(value = "intno") Integer intno) {
        List<Map<String, String>> data = itvService.questionData(intno);
        return data;
    }

    @GetMapping("/fetchCTFeedback")
    public List<String> fetchCTFeedback(@RequestParam(value = "intno") Integer intno) {
        List<String> ctFeedbackList = itvService.ctFeedbackList(intno);
        return ctFeedbackList;
    }

    @GetMapping("/faceData")
    public List<Integer> getFaceData(@RequestParam(value = "intno") Integer intno) {
        List<Integer> values = itvService.faceData(intno);
        // System.out.println(values);
        return values;
    }

    @GetMapping("/barData")
    public ResponseEntity<Map<String, Object>> getBarData(@RequestParam(value = "intno") Integer intno) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Integer> values = itvService.getBarData(intno);
            if (values == null || values.isEmpty()) {
                return ResponseEntity.notFound().build(); // 데이터가 없는 경우 404 Not Found 반환
            }
            response.put("values", values);
            response.put("chartname", "머리흔들림");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "서버 오류"));
        }
    }

    @GetMapping("/voicegData")
    public ResponseEntity<Map<String, Object>> getvoicegData() {
        Map<String, Object> response = new HashMap<>();
        int[] q1 = { 0, 0, 1, 2, 2, 2, 2, 3, 3, 3 }; // 실제 데이터 값
        int[] q2 = { 0, 1, 2, 3, 3, 3, 3, 3, 4, 4 };
        int[] q3 = { 0, 0, 1, 1, 1, 1, 1, 2, 2, 2 };
        int[] q4 = { 0, 0, 1, 2, 2, 2, 2, 3, 4, 5 };
        int[] q5 = { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1 };
        response.put("q1", q1);
        response.put("q2", q2);
        response.put("q3", q3);
        response.put("q4", q4);
        response.put("q5", q5);
        response.put("chartname", "음성떨림");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/wordCloud")
    public ResponseEntity<?> wordCloud(@RequestParam(value = "intno") Integer intno) {
        List<String> answerList = itvService.wordCloud(intno);
        if (answerList == null || answerList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        String combinedAnswers = String.join(" ", answerList);
        return ResponseEntity.ok(combinedAnswers);

    }
}