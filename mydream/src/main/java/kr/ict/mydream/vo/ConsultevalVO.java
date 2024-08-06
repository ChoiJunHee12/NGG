package kr.ict.mydream.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("consultevalvo")
public class ConsultevalVO {

    private int intno;
    private int qno;
    private int memno;
    private int cnsno;
    private String qcnsfpeedbk;
    private float qcnsscore;
    private Date credt;
    private Date upddt;
}
