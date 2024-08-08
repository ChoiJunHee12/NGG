package kr.ict.mydream.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ict.mydream.vo.MemberVO;

@Service
public class MyPageService {

    @Autowired
    private MyPageDAO myPageDAO;

    public MemberVO mypage(int memno){
        return myPageDAO.mypage(memno);
    }

    public void updateProfile(int memno, MemberVO member) {
        myPageDAO.updateProfile(member);
    }

    public void updateImage(MemberVO member) {
        myPageDAO.updateImage(member);
    }
}
