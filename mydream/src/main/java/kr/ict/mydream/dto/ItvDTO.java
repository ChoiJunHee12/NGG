package kr.ict.mydream.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("itvdto")
public class ItvDTO {

    public ItvDTO() {
    }

    public ItvDTO(String mname, Date credt, String cnsfeedbk, String aifeedbk, String question, int intno) {
        this.mname = mname;
        this.credt = credt;
        this.cnsfeedbk = cnsfeedbk;
        this.aifeedbk = aifeedbk;
        this.question = question;
        this.intno = intno;
        // this.pbadcnt = pbadcnt;
    }

    private String mname;
    private Date credt;
    private String cnsfeedbk;
    private String aifeedbk;
    private String question;
    private int intno;
    // private int pbadcnt;

}
