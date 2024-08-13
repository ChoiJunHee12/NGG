package kr.co.ict.yourdream.vo;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class IntResVO {
    private int intno; // 면접번호(PK)
    private int memno; // 회원번호(FK)
    private String inttypecd; // 직무 or 인성면접
    private String aifeedbk; // AI 피드백
    private String cnsfeedbk; // 컨설턴트 피드백(총평)
    private float cnsscore; // 컨설턴트 평가점수
    private Date itenddt; // 면접종료시간
    private float reviewscore; // 컨설턴트 평점주기
    private Date credt; // 최초등록일자
    private Date upddt; // 최종수정일자
}
