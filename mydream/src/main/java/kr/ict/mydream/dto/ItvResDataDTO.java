package kr.ict.mydream.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItvResDataDTO {

    public ItvResDataDTO() {
    }

    public ItvResDataDTO(String question, String answer, String aifeedbk) {
        this.question = question;
        this.answer = answer;
        this.aifeedbk = aifeedbk;
    }

    private String question;
    private String answer;
    private String aifeedbk;
}
