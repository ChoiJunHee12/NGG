package kr.ict.mydream.vo;

import java.util.Date;
import org.apache.ibatis.type.Alias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("scedulevo")
public class SceduleVO {
    private Long schno; // 일정번호(PK)
    private Long memno; // 회원번호(FK)
    private Date startdt; // 일정시작일시
    private Date enddt; // 일정종료일시
    private String title; // 제목
    private String content; // 내용
    private Date credt; // 최초등록일자
    private Date upddt; // 최종수정일자
}