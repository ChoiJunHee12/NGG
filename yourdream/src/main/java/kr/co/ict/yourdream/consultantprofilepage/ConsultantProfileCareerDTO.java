package kr.co.ict.yourdream.consultantprofilepage;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
// 컨설턴트 약력을 위한 DTO (테이블명 TBCNSCAREER)
public class ConsultantProfileCareerDTO {
    private int cnsno; // 컨설턴트 번호(PK1)
    private int seqno; // 컨설턴트 약력 일련번호(PK2)
    private String term; // 약력기간
    private String content; // 약력내용
    private String detail; // 약력디테일
    private String careerdiv; // 학력/경력구분
    private Date credt; // 최초등록일자
    private Date upddt; // 최종수정일자
}