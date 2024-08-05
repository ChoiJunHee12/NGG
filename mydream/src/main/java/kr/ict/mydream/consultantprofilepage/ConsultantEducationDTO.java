package kr.ict.mydream.consultantprofilepage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConsultantEducationDTO {
    private String id;
    private String degree;
    private String field;
    private String school;
    private String year;
}