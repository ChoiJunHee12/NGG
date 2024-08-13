package kr.co.ict.yourdream.consultantprofilepage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ConsultantProfileService {

    private ConsultantProfileDTO profile = ConsultantProfileDTO.builder()
            .cnsno(1001)
            .name("김지원")
            .gendercd("F")
            .categcd("1")
            .email("consultant@example.com")
            .phonenum("010-9876-5432")
            .birthymd("19850515")
            .introduce("Experienced consultant with expertise in AI and Machine Learning.")
            .quitymd(null)
            .imgname("ct1.png")
            .cnscareer("5년")
            .cnsproject("100회 이상")
            .credt("2024-08-07")
            .upddt("2024-08-07")

            .build();

    private List<ConsultantProfileCareerDTO> careerList = new ArrayList<>();

    public ConsultantProfileDTO getProfile() {
        return profile;
    }

    public ConsultantProfileDTO updateProfile(ConsultantProfileDTO updatedProfile) {
        // 실제로는 ID를 검사하고 유효성 검사를 추가(나중에)
        profile = updatedProfile;
        return profile;
    }

    public List<ConsultantProfileCareerDTO> getCareer(int cnsno) {
        // 프로필 ID를 기준으로 경력 정보를 필터링
        return careerList.stream()
                .filter(career -> career.getCnsno().equals(cnsno))
                .collect(Collectors.toList());
    }
}