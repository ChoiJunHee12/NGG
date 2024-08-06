package kr.ict.mydream.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("selfintrovo")
public class SelfIntro {
    private int seqno;
    private int rsmno;
    private String title;
    private String content;
    private Date credt;
    private Date upddt;
}
