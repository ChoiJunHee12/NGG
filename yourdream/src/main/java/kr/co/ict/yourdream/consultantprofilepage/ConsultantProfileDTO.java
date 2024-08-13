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
// 컨설턴트 정보를 위한 DTO (테이블명 TBCONSULT)
public class ConsultantProfileDTO {
    private int cnsno; // 컨설턴트번호(PK)
    private String name; // 성명
    private String gendercd; // 성별 (M:남성, F:여성)
    private String categcd; // 전문분야
    private String email; // 이메일
    private String phonenum; // 휴대폰번호
    private String birthymd; // 생년월일
    private String introduce; // 자기소개
    private String quitymd; // 퇴사일자
    private String imgname; // 사진이름
    private String cnscareer; // 컨설팅경력
    private String cnsproject; // 프로젝트 수행
    private Date credt; // 최초등록일자
    private Date upddt; // 최종수정일자
    private int memno; // 회원번호 (컨설턴트가 관리하는 회원의 번호)
}