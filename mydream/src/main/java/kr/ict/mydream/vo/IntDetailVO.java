package kr.ict.mydream.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Alias("intdetailvo")
public class IntDetailVO {
    private int intno; // 면접번호(FK)
    private int qno; // 질문번호(PK)
    private int aiqno; // AI질문번호
    private int cnsqno; // 컨설턴트질문번호
    private String question; // 질문제목
    private String answer; // 질문답변 STT
    private float ecntgood; // 감정긍정count
    private float ecntsoso; // 감정중립count
    private float ecntbad; // 감정부정count
    private float pbadcnt; // 자세불량count
    private float vhertz; // 음성주파수
    private float vamplit; // 음성진폭
    private float vempty; // 음성공백시간
    private String aifeedbk; // AI피드백디테일
    private Date credt; // 최초등록일자
    private Date upddt; // 최종수정일자
    private float escore; // 감정평균점수 (이걸 기준으로 상중하)
    private float pscore; // 자세평균점수 (이걸 기준으로 상중하)
    private float vscore; // 음성평균점수 (이걸 기준으로 상중하)
}
