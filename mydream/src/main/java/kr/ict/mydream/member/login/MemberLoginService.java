package kr.ict.mydream.member.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.ict.mydream.vo.MemberVO;


@Service
public class MemberLoginService {
    @Autowired
    private MemberLoginDAO memberLoginDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public void registerMember(MemberVO member) {
        memberLoginDAO.registerMember(member);
    }

    public MemberVO findByEmail(String email) {
        return memberLoginDAO.findByEmail(email);
    }

    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    // http://192.168.0.73/mydream/membership/profile?memno=51
    public void updateProfile(int memno, MemberVO member) {
        System.out.println("서비스에서 vo확인"+member.getPassword());
        if (member.getPassword() != null && !member.getPassword().isEmpty()) {
            member.setPassword(passwordEncoder.encode(member.getPassword()));
        }
        System.out.println("인코딩후 확인"+member.getPassword());

        member.setMemno(memno);

        memberLoginDAO.updateProfile(member);
    }






    public MemberVO mypage(int memno){
        return memberLoginDAO.mypage(memno);
    }


    public void updateImage(MemberVO member) {
        memberLoginDAO.updateImage(member);
    }
}
