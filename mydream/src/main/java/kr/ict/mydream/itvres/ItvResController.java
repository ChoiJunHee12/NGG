package kr.ict.mydream.itvres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import kr.ict.mydream.vo.ItvDTO;

import java.util.List;
import java.util.Map;

import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("/itv")
public class ItvResController {

    // @Autowired
    // private ItvService itvService;

    // @GetMapping("/attitude")
    // public List<ItvDTO> getAttitudeResults() {
    // return itvService.itvAttitudeList();

    // }

    @GetMapping("/attitude")
    public List<String> getAttitudeResults() {
        return Arrays.asList(
                "<인성면접> 침착맨님 면접결과 24.07.07",
                "<인성면접> 침착맨님 면접결과 24.07.08",
                "<인성면접> 침착맨님 면접결과 24.07.09");
    }

    @GetMapping("/career")
    public List<String> getCareerResults() {
        return Arrays.asList(
                "<직무면접> 침착맨님 면접결과 24.07.07",
                "<직무면접> 침착맨님 면접결과 24.07.08",
                "<직무면접> 침착맨님 면접결과 24.07.09");
    }

    @GetMapping("/fetchData")
    public ResponseEntity<Map<String, Object>> fetchData() {
        // 데이터 생성 및 반환 로직
        Map<String, Object> data = new HashMap<>();
        data.put("question", Arrays.asList("질문1", "질문2", "질문3", "질문4", "질문5", "질문6", "질문7"));
        data.put("comment", Arrays.asList("답변1", "답변2", "답변3", "답변4", "답변5", "답변6", "답변7"));
        data.put("feedback", Arrays.asList("피드백1", "피드백2", "피드백3", "피드백4", "피드백5"));
        // System.out.println(ResponseEntity.ok(data));

        return ResponseEntity.ok(data);
    }

    @GetMapping("/fetchAIFeedback")
    public ResponseEntity<List<String>> fetchAIFeedback() {
        List<String> aiFeedbackList = Arrays.asList(
                "시간이 지날수록 자세가 불안정함이 보여 좀 더 집중도를 올려야합니다.",
                "회원님의 자세 흐트러짐은 평균보다 높아요.",
                "전체적으로 자세를 유지하는 시간이 길지 않으며, 집중도가 떨어짐을 확인할 수 있다.",
                "이력서와 면접 질문과 유사도가 떨어집니다.",
                "너무 같은 단어를 반복하여 말했습니다.",
                "내가 추가한 데이터가 맞나요?");

        return ResponseEntity.ok(aiFeedbackList);
    }

    @GetMapping("/fetchCTFeedback")
    public ResponseEntity<List<String>> fetchCTFeedback() {
        List<String> ctFeedbackList = Arrays.asList(
                "전체적으로 자세를 유지하는 시간이 길지 않으며, 집중도가 떨어짐을 확인할 수 있다. 아무튼 컨설턴트가 블라블라해줄것이다.");

        return ResponseEntity.ok(ctFeedbackList);
    }

    @GetMapping("/userData")

    public ResponseEntity<Map<String, Object>> fetchUserData() {
        Map<String, Object> data = new HashMap<>();
        data.put("mname", Arrays.asList("야옹이"));
        data.put("resDate", Arrays.asList("2024.08.01"));
        // System.out.println(ResponseEntity.ok(data));

        return ResponseEntity.ok(data);
    }

    @GetMapping("/faceData")
    public ResponseEntity<Map<String, Object>> getFaceData() {
        Map<String, Object> response = new HashMap<>();
        int[] values = { 60, 73, 85, 40, 65 }; // 실제 데이터 값
        response.put("values", values);
        response.put("chartname", "감정 점수");
        // System.out.println(ResponseEntity.ok(response));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/barData")
    public ResponseEntity<Map<String, Object>> getBarData() {
        Map<String, Object> response = new HashMap<>();
        int[] values = { 60, 73, 85, 40, 65 }; // 실제 데이터 값
        response.put("values", values);
        response.put("chartname", "머리흔들림");
        System.out.println(ResponseEntity.ok(response));
        return ResponseEntity.ok(response);
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
        response.put("chartname", "머리흔들림");
        System.out.println(ResponseEntity.ok(response));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/wordCloud")
    public String getWordCloud() {
        String wordCloud = "Chapter 1. Down the Rabbit-Hole Alice was beginning to get very tired of sitting by her sister on the bank, and of having nothing to do: once or twice she had peeped into the book her sister was reading, but it had no pictures or conversations in it, 'and what is the use of a book,' thought Alice 'without pictures or conversation?' So she was considering in her own mind (as well as she could, for the hot day made her feel very sleepy and stupid), whether the pleasure of making a daisy-chain would be worth the trouble of getting up and picking the daisies, when suddenly a White Rabbit with pink eyes ran close by her. ";
        System.out.println(wordCloud);
        return wordCloud;
    }

}