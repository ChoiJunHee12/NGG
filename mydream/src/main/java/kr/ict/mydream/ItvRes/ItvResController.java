package kr.ict.mydream.ItvRes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("/itv")
public class ItvResController {
    // public static void main(String[] args) {
    // System.out.println("컨트롤러");
    // }

    @GetMapping("/attitude")
    public List<String> getAttitudeResults() {

        return Arrays.asList(
                "<인성면접> 침착맨님 면접결과 24.07.07",
                "<인성면접> 침착맨님 면접결과 24.07.08",
                "<인성면접> 침착맨님 면접결과 24.07.09",
                "<인성면접> 침착맨님 면접결과 24.07.10",
                "<인성면접> 침착맨님 면접결과 24.07.11");
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
        System.out.println(ResponseEntity.ok(data));

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
        System.out.println(ResponseEntity.ok(data));

        return ResponseEntity.ok(data);
    }

}