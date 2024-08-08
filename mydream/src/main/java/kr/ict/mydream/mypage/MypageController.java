package kr.ict.mydream.mypage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import kr.ict.mydream.vo.MemberVO;

@RestController
@RequestMapping("/mypage")
public class MyPageController {

    @Autowired
    private MyPageService myPageService;

    private final static String filePath = Paths.get(System.getProperty("user.dir"))
            .resolve("vuefront/public/img/upimg").toString();

    public MyPageController() {
        System.out.println("imagePath=>" + filePath);

        Path directoryPath = Paths.get(filePath);

        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
                System.out.println("디렉토리 생성: " + directoryPath);
            } catch (Exception e) {
                System.err.println("디렉토리 생성 실패: " + e.getMessage());
            }
        }
    }

    // http://192.168.0.54/mydream/mypage/profile?memno=51 성공
    @GetMapping("/profile")
    public MemberVO myprofile(@RequestParam("memno") int memno) {
        return myPageService.mypage(memno);
    }

    @PutMapping("/profile")
    public void updateProfile(@RequestParam("memno") int memno, @RequestBody MemberVO vo) {
        myPageService.updateProfile(memno, vo);
    }

    @PostMapping("/profileImage")
    public ResponseEntity<?> updateImage(@RequestParam("file") MultipartFile mf, @RequestParam("memno") int memno) {
        if (mf == null || mf.isEmpty()) {
            return ResponseEntity.badRequest().body("파일이 전송되지 않았습니다.");
        }

        String oriFn = mf.getOriginalFilename();
        Path destinationPath = Paths.get(filePath).resolve(oriFn).normalize(); // 경로를 안전하게 조작
        System.out.println("FullPath :" + destinationPath);

        try {
            // 파일 복사 및 덮어쓰기
            Files.copy(mf.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

            MemberVO vo = new MemberVO();
            vo.setMemno(memno);
            vo.setImgname(oriFn);
            myPageService.updateImage(vo);

            return ResponseEntity.ok().body(oriFn); // 파일 이름 반환
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("이미지 업로드 중 오류가 발생했습니다.");
        }
    }

}
