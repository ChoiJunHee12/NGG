package kr.ict.mydream.mypage;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mypage")
public class MypageController {

    private MemberVO member;

    public MypageController(){
        member = new MemberVO();
        member.setMemid(3);
        member.setMname("진");
        member.setMbirth("1990-01-01");
        member.setMphoneno("010-1234-5678");
        member.setMemail("jin@naver.com");
        member.setApplpart("Software Development");
        member.setPrefarea("서울");
        member.setEntymd("2020-05-11");
        member.setQuitdate("2023-01-01");
        member.setStatus("Active");
        member.setSeason("Summer");
        member.setMimgn("profile_image.png");
        member.setLocid(100);
        member.setCredt(new Date());
        member.setUpddt(new Date());

        //없는것
        member.setMnick("지존홍길동");
        member.setMgender("남자");
        member.setMpwd("12345678");
    }
    
    //http://172.30.1.64:80/mydream/mypage/profile?mnum=3 성공
    @GetMapping("/profile")
    public MemberVO myprofile(@RequestParam("mnum") int num){
        if(num == 3){
            return member;
        }
        return null;
    }

    @PutMapping("/profile")
    public MemberVO updateProfile(@RequestParam("mnum") int num, @RequestBody MemberVO updatedMember) {
        if (num == 3) {
            // 기존 정보 업데이트
            member.setMphoneno(updatedMember.getMphoneno());
            member.setApplpart(updatedMember.getApplpart());
            member.setPrefarea(updatedMember.getPrefarea());
            member.setMnick(updatedMember.getMnick());
            member.setMpwd(updatedMember.getMpwd());
            member.setUpddt(new Date()); // 업데이트 날짜 변경
            return member;
        }
        return null;
    }

    @GetMapping("/eduprofile")
    public EducationVO getMethodName(@RequestParam("mnum") int num) {
        if(num == 3){
            EducationVO edu = new EducationVO();
                edu.setUniversity("서울대학교");
                edu.setUmajor("컴퓨터공학과");
                edu.setUgraduate("2015-01-01");
                edu.setMaster("고려대학교 대학원");
                edu.setMmajor("딥러닝");
                edu.setMgraduate("2020-01-01");

            return edu;
        }
        return null;
    }
    




}
