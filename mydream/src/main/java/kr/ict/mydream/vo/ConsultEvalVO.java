package kr.ict.mydream.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("consultevalvo")
public class ConsultEvalVO {

    private int intno;
    private int qno;
    private int memno;
    private int cnsno;
    private String qcnsfeedbk;
    private float qcnsscore;
    private Date credt;
    private Date upddt;
}
