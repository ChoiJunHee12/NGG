package kr.ict.mydream.mypage;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
    private int memid;
    private String mname;
    private String mbirth;
    private String mphoneno;
    private String memail;
    private String applpart;
    private String prefarea;
    private String entymd;
    private String quitdate;
    private String status;
    private String season;
    private String mimgn;
    private int locid;
    private Date credt;
    private Date upddt;


    //없는것
    private String mnick;
    private String mgender;
    private String mpwd; 
}
