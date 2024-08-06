package kr.ict.mydream.calendar;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalendarVO {
    private Long schno;
    private Long memno;
    private Date startdt;
    private Date enddt;
    private String title;
    private String content;
    private Date credt;
    private Date upddt;
}