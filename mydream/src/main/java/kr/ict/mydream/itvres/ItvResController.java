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

import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("/itv")
public class ItvResController {

    @Autowired
    private ItvService itvService;

    @GetMapping("/attitude")
    public List<Map<String, Object>> getAttitudeResults() {
        return itvService.itvAttitudeList();

    }

    // @GetMapping("/attitude")
    // public List<String> getAttitudeResults() {
    // return Arrays.asList(
    // "<인성면접> 침착맨님 면접결과 24.07.07",
    // "<인성면접> 침착맨님 면접결과 24.07.08",
    // "<인성면접> 침착맨님 면접결과 24.07.09");
    // }

    @GetMapping("/career")
    public List<Map<String, Object>> getCareerResults() {
        return itvService.itvCareerList();
    }

    // @GetMapping("/career")
    // public List<String> getCareerResults() {
    // return Arrays.asList(
    // "<직무면접> 침착맨님 면접결과 24.07.07",
    // "<직무면접> 침착맨님 면접결과 24.07.08",
    // "<직무면접> 침착맨님 면접결과 24.07.09");
    // }

    @GetMapping("/userData")

    public List<ItvDTO> fetchUserData(@RequestParam(value = "intno", required = false) Integer intno) {
        // System.out.println("intno:" + intno);
        return itvService.detail(intno);
    }

    // @GetMapping("/fetchData")
    // public ResponseEntity<Map<String, String>> questionData(int intno) {
    // // 데이터 생성 및 반환 로직
    // Map<String, String> data = new HashMap<>();
    // data.put(itvService.questionData(intno));
    // data.put("question", Arrays.asList("질문1", "질문2", "질문3", "질문4", "질문5",
    // "질문6", "질문7"));
    // data.put("answer", Arrays.asList("답변1", "답변2", "답변3", "답변4", "답변5", "답변6",
    // "답변7"));
    // data.put("aifeedbk", Arrays.asList("피드백1", "피드백2", "피드백3", "피드백4",
    // "피드백5"));
    // System.out.println(ResponseEntity.ok(data));

    // return ResponseEntity.ok(data);
    // }

    @GetMapping("/fetchData")
    public List<Map<String, String>> questionData(int intno) {
        // itvService에서 데이터를 가져옴
        List<Map<String, String>> data = itvService.questionData(intno);

        // if (data == null || data.isEmpty()) {
        // return ResponseEntity.notFound().build();
        // }
        System.out.println("data:" + data);
        return data;
    }

    @GetMapping("/fetchCTFeedback")
    public ResponseEntity<List<String>> fetchCTFeedback() {
        List<String> ctFeedbackList = Arrays.asList(
                "전체적으로 자세를 유지하는 시간이 길지 않으며, 집중도가 떨어짐을 확인할 수 있다. 아무튼 컨설턴트가 블라블라해줄것이다.");

        return ResponseEntity.ok(ctFeedbackList);
    }

    // @GetMapping("/faceData")
    // public ResponseEntity<Map<String, Object>> getFaceData() {
    // Map<String, Object> response = new HashMap<>();
    // int[] values = { 60, 73, 85, 40, 65 }; // 실제 데이터 값
    // response.put("values", values);
    // response.put("chartname", "감정 점수");
    // // System.out.println(ResponseEntity.ok(response));
    // return ResponseEntity.ok(response);
    // }

    @GetMapping("/faceData")
    public ResponseEntity<Map<String, Object>> getFaceData(@RequestParam(value = "intno") Integer intno) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Integer> values = itvService.faceData(intno);
            if (values == null || values.isEmpty()) {
                return ResponseEntity.notFound().build(); // 데이터가 없는 경우 404 Not Found 반환
            }
            response.put("values", values);
            response.put("chartname", "감정 점수");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // 로그를 남기고, 클라이언트에 오류 메시지 전송
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "서버 오류"));
        }
    }

    @GetMapping("/barData")
    public ResponseEntity<Map<String, Object>> getBarData(@RequestParam(value = "intno") Integer intno) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Integer> values = itvService.faceData(intno);
            if (values == null || values.isEmpty()) {
                return ResponseEntity.notFound().build(); // 데이터가 없는 경우 404 Not Found 반환
            }
            response.put("values", values);
            response.put("chartname", "머리흔들림");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // 로그를 남기고, 클라이언트에 오류 메시지 전송
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
        // System.out.println(ResponseEntity.ok(response));
        return ResponseEntity.ok(response);
    }

    // @GetMapping("/faceData")
    // public ResponseEntity<Map<String, Object>> getFaceData(@RequestParam(value =
    // "intno") Integer intno) {
    // Map<String, Object> response = new HashMap<>();
    // try {
    // List<Integer> values = itvService.faceData(intno);
    // if (values == null || values.isEmpty()) {
    // return ResponseEntity.notFound().build(); // 데이터가 없는 경우 404 Not Found 반환
    // }
    // response.put("values", values);
    // response.put("chartname", "감정 점수");
    // return ResponseEntity.ok(response);
    // } catch (Exception e) {
    // // 로그를 남기고, 클라이언트에 오류 메시지 전송
    // e.printStackTrace();
    // return
    // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error",
    // "서버 오류"));
    // }
    // }

    @GetMapping("/wordCloud")
    public ResponseEntity<?> wordCloud(@RequestParam(value = "intno") Integer intno) {
        List<String> answerList = itvService.wordCloud(intno);
        if (answerList == null || answerList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        // List<String>을 하나의 문자열로 변환
        String combinedAnswers = String.join(" ", answerList);
        // System.out.println("답변: " + combinedAnswers);
        return ResponseEntity.ok(combinedAnswers);

    }
}