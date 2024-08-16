package kr.co.ict.yourdream.consultantProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/consultProfiles")
public class ConsultProfileController {

    @Autowired
    private ConsultProfileService consultProfileService;

    // 특정 컨설턴트 프로필 조회 (기본 정보만)
    @GetMapping("/{cnsno}/basic")
    public ResponseEntity<ConsultVO> getBasicConsultProfileById(@PathVariable("cnsno") int cnsno) {
        try {
            ConsultVO profileVO = consultProfileService.getBasicConsultProfileById(cnsno);
            return ResponseEntity.ok(profileVO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 특정 컨설턴트 프로필 조회 (전체 정보)
    @GetMapping("/{cnsno}")
    public ResponseEntity<ConsultVO> getConsultProfileById(@PathVariable("cnsno") int cnsno) {
        try {
            ConsultVO profileVO = consultProfileService.getConsultProfileById(cnsno);
            return ResponseEntity.ok(profileVO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // // 새 컨설턴트 프로필 생성
    // @PostMapping
    // public ResponseEntity<ConsultVO> createConsultProfile(@RequestBody ConsultVO consultVO) {
    //     ConsultVO createdProfile = consultProfileService.createConsultProfile(consultVO);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
    // }

    // 컨설턴트 프로필 정보 업데이트
    @PostMapping("/{cnsno}")
    public ResponseEntity<ConsultVO> updateConsultProfile(@PathVariable("cnsno") int cnsno,
            @RequestBody ConsultVO consultVO) {
        try {
            ConsultVO updatedProfileVO = consultProfileService.updateConsultProfile(cnsno, consultVO);
            return ResponseEntity.ok(updatedProfileVO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // // 컨설턴트 프로필 삭제
    // @DeleteMapping("/{cnsno}")
    // public ResponseEntity<Void> deleteConsultProfile(@PathVariable("cnsno") int cnsno) {
    //     boolean deleted = consultProfileService.deleteConsultProfile(cnsno);
    //     return deleted ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    // }

    // 프로필 이미지 업로드
    @PostMapping("/uploadProfileImage")
    public ResponseEntity<Map<String, String>> uploadProfileImage(@RequestParam("imgfile") MultipartFile mf) {
        try {
            String filename = consultProfileService.uploadProfileImage(mf);
            Map<String, String> response = new HashMap<>();
            response.put("filename", filename);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException | IOException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    // 프로필 이미지 업데이트
    @PostMapping("/{cnsno}/updateProfileImage")
    public ResponseEntity<Map<String, String>> updateProfileImage(@PathVariable("cnsno") int cnsno,
            @RequestBody Map<String, String> request) {
        String imgname = request.get("imgname"); // JSON 본문에서 imgname 추출

        if (imgname == null) {
            // imgname 파라미터가 없으면 400 Bad Request 응답
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Missing 'imgname' parameter");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        try {
            consultProfileService.updateProfileImage(cnsno, imgname);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Profile image updated successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}