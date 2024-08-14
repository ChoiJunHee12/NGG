package kr.co.ict.yourdream.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CnsCareerVO {
    private int seqno;
    private int cnsno;
    private String term;
    private String content;
    private String detail;
    private String careerdiv;
    private Date credt;
    private Date upddt;
}
