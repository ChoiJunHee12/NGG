package kr.ict.mydream.CV;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cv")
public class CVController {

    @GetMapping("/cvDetail")
    public ResponseEntity<?> cvDetail(@RequestParam("num") int num) {
        System.out.println(num + "번 이력서 상세");
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("name", "침착맨");
        responseBody.put("birth", "2000-01-01");
        responseBody.put("address", "서울특별시 강남구");
        responseBody.put("tel", "02-111-1111");
        responseBody.put("htel", "010-1111-1111");
        return ResponseEntity.ok().body(responseBody);
    }

    @GetMapping("/cvAdd")
    public ResponseEntity<?> cvAdd() {
        System.out.println("이력서 추가");
        return ResponseEntity.ok().body(1);
    }

    @GetMapping("/cvDelete")
    public void cvDelete(@RequestParam("num") int num) {
        System.out.println(num + "번 이력서 삭제");
    }

    @PostMapping("/cvUpdate")
    public ResponseEntity<?> cvUpdate(@RequestParam("num") int num, @RequestBody Map<String, String> resp) {
        System.out.println("받은 데이터 : " + resp);
        System.out.println(num + "번 이력서 수정");
        return ResponseEntity.ok().body(2);
    }
}
