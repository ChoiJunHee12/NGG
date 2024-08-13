package kr.co.ict.yourdream.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomVO {

    private int chtno;
    private int memno;
    private int cnsno;
    private String chatname;
    private Date credt;
    private Date upddt;

}
