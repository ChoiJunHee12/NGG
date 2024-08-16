package kr.co.ict.yourdream.userdash;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tbintres") // 데이터베이스 테이블 이름과 맞춰야 합니다.
public class IntResDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_intno")
    @SequenceGenerator(name = "seq_intno", sequenceName = "seq_intno", allocationSize = 1)
    @Column
    private int intno; // 면접번호(PK)

    @Column
    private int memno; // 회원번호(FK)

    @Column
    private String inttypecd; // 직무 or 인성면접

    @Column
    private String cnsfeedbk; // 컨설턴트 피드백(총평)

    @Column
    private float cnsscore; // 컨설턴트 평가점수

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date itenddt; // 면접종료시간

    @Column
    private float reviewscore; // 컨설턴트 평점주기

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date credt; // 최초등록일자

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date upddt; // 최종수정일자
}
