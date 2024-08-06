package kr.ict.mydream.vo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("cnscareerVO")
public class CnsCareerVO {
    private int cnsno; // 컨설턴트번호
    private int seqno; // 컨설턴트약력일련번호
    private String term; // 약력기간
    private String content; // 약력내용
    private String detail; // 약력디테일
    private String careerdiv; // 학력/경력구분
    private Date credt; // 최초등록일자
    private Date upddt; // 최종수정일자
}
