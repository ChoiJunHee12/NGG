package kr.ict.mydream.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("aiquestionvo")
public class AIQuestionVO {

    private int cnsqno;
    private int cnsno;
    private String question;
    private String keyword1;
    private String keyword2;
    private String keyword3;
}
