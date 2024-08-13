package kr.co.ict.yourdream.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelfIntroVO {
    private int seqno;
    private int rsmno;
    private String title;
    private String content;
    private Date credt;
    private Date upddt;
}
