package kr.co.ict.yourdream.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDetailVO {
    private int chtno;
    private int seqno;
    private String chatdiv;
    private String content;
    private Date chatdt;
}
