package kr.ict.mydream.member.login;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.ict.mydream.vo.MemberVO;



@Mapper
public interface MemberLoginDAO {
    MemberVO findByEmail(String email);
    int registerMember(MemberVO member);
    void updateProfile(MemberVO member);


    public MemberVO mypage(int memno);    
    public void updateImage(MemberVO member);

    String getPasswordByMemno(int memno);
    int updatePassword(@Param("memno") int memno, @Param("newPassword") String newPassword);
} 
