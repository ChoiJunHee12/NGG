package kr.co.ict.yourdream.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CareerVO {
    private int rsmno;
    private int seqno;
    private String entymd;
    private String quitymd;
    private String compname;
    private String jobclass;
    private String maintask;
    private String jobposition;
    private Date credt;
    private Date upddt;
}
