package kr.ict.mydream.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Alias("memberconsultvo")
public class MemberConsultVO {

    private int cnsno;
    private int memno;
    private String useyn;
    private Date credt;
    private Date upddt;
}
