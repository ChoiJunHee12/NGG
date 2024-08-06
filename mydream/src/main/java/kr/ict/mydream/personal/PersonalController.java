package kr.ict.mydream.personal;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/personal")
public class PersonalController {

    private final static String filePath = Paths.get("").toAbsolutePath() + "/front/public/img";

    @PostMapping("/makeUpImgSave")
    public void MakeUpImgSave(@RequestParam("imgfile") MultipartFile mf) {
        System.out.println(filePath);
        System.out.println("after_imageSave왔니?");
        if (mf == null || mf.isEmpty()) {
            throw new IllegalArgumentException("파일이 전송되지 않았습니다.");
        }
        String oriFn = mf.getOriginalFilename();
        StringBuffer path = new StringBuffer();
        path.append(filePath).append("/after_image/");
        path.append(oriFn);
        System.out.println("FullPath :" + path);
        File f = new File(path.toString());
        try {
            mf.transferTo(f);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/proImgupdate")
    public void Imgupdate(MemberVO vo) {

    }

    @PostMapping("/seasonUpdate")
    public void SeasonUpdate(MemberVO vo) {

    }
}
