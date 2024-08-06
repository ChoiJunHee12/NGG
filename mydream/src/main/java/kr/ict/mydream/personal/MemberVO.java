package kr.ict.mydream.personal;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
    private int memno;
    private String name;
    private String gendercd;
    private String nickname;
    private String categcd;
    private String email;
    private String password;
    private String phonenum;
    private String birthymd;
    private int loccd;
    private String seasoncd;
    private String entymd;
    private String quitymd;
    private String imgname;
    private Date credt;
    private Date upddt;

}
