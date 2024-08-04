package kr.ict.mydream.consultantprofilepage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConsultantEducationDTO {
    private Long id;
    private Long profileId;
    private String degree;
    private String field;
    private String school;
    private String year;
}
