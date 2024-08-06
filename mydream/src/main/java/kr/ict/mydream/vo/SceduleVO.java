package kr.ict.mydream.vo;

import java.util.Date;
import org.apache.ibatis.type.Alias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("scedulevo")
public class SceduleVO {
    private Long schno;
    private Long memno;
    private Date startdt;
    private Date enddt;
    private String title;
    private String content;
    private Date credt;
    private Date upddt;
}