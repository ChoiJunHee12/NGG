package kr.ict.mydream.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Alias("membervo")
public class MemberVO {
    private int memno; // 회원번호 (PK)
    private String name; // 회원이름
    private String gendercd; // 회원성별
    private String nickname; // 회원닉네임
    private String categcd; // 회원의 지원분야
    private String email; // 회원 이메일
    private String password; // 회원 비밀번호
    private String phonenum; // 회원 전화번호
    private String birthymd; // 회원 생년월일
    private String loccd; // 회원의 거주지역
    private String seasoncd; // 회원의 퍼스널컬러
    private String entymd; // 회원의 가입일자
    private String quitymd; // 회원의 탈퇴일자
    private String imgname; // 회원의 사진이름
    private Date credt; // 최초등록일자
    private Date upddt; // 최초수정일자
}
