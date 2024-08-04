package kr.ict.mydream.consultantprofilepage;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsultantProfileDTO {
    private Long id;
    private String name;
    private String gender;
    private String title;
    private String phone;
    private String email;
    private String birthYear;
    private String area;
    private String expertise;
    private String experience;
    private String clients;
    private String introduce;
}
