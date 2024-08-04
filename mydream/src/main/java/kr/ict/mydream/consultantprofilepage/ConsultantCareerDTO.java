package kr.ict.mydream.consultantprofilepage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConsultantCareerDTO {
    private Long id;
    private Long profileId;
    private String company;
    private String position;
    private String period;
    private String description;
}
