package kr.ict.mydream.mypage;

import org.apache.ibatis.annotations.Mapper;

import kr.ict.mydream.vo.MemberVO;

@Mapper
public interface MyPageDAO {

    public MemberVO mypage(int memno);
    public void updateProfile(MemberVO member);
    public void updateImage(MemberVO member);
}
