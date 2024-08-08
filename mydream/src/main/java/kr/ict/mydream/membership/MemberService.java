package kr.ict.mydream.membership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ict.mydream.vo.MemberVO;


@Service
public class MemberService {

    @Autowired
    private MemberDAO memberDAO;

    public void tbmemberadd(MemberVO vo){ //회원가입
        memberDAO.memberadd(vo);
    }
    
}
