package kr.ict.mydream.membership;

import org.apache.ibatis.annotations.Mapper;

import kr.ict.mydream.vo.MemberVO;





@Mapper
public interface MemberDAO {

    public void memberadd(MemberVO vo); //회원가입
    
}
