package kr.co.ict.yourdream.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EducationVO {
    private int seqno;
    private int rsmno;
    private String entymd;
    private String gradeymd;
    private String schoolname;
    private String major;
    private String gradeuateyn;
    private String gradescore;
    private Date credt;
    private Date upddt;
}
