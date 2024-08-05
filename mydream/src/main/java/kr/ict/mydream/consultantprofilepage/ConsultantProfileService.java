package kr.ict.mydream.consultantprofilepage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ConsultantProfileService {

    private ConsultantProfileDTO profile = ConsultantProfileDTO.builder()
            .id("1")
            .name("김지원")
            .gender("여")
            .title("경영/컨설팅 컨설턴트")
            .phone("010-1234-5678")
            .email("jiwon.kim@consultancy.com")
            .birthYear("1992.07.07")
            .area("서울")
            .expertise("경영/컨설팅")
            .experience("5-10년")
            .clients("100회 이상")
            .introduce("글로벌 시장에서의 성공을 위한 맞춤형 전략을 제공합니다. 언어와 문화의 장벽을 넘어 여러분의 비즈니스가 세계로 나아갈 수 있도록 돕겠습니다.")
            .build();

    private List<ConsultantEducationDTO> educationList = new ArrayList<>();
    private List<ConsultantCareerDTO> careerList = new ArrayList<>();

    public ConsultantProfileService() {
        educationList.add(new ConsultantEducationDTO("1", "석사", "국제경영학", "University of Canberra", "2018"));
        educationList.add(new ConsultantEducationDTO("1", "학사", "영어영문학", "서울대학교", "2015"));

        careerList.add(new ConsultantCareerDTO("1", "ABC 컨설팅", "선임 컨설턴트", "2018-2023", "글로벌 기업 대상 비즈니스 전략 수립"));
        careerList.add(new ConsultantCareerDTO("1", "XYZ 교육", "영어 강사", "2015-2018", "기업 임직원 대상 비즈니스 영어 교육"));
    }

    public ConsultantProfileDTO getProfile() {
        return profile;
    }

    public ConsultantProfileDTO updateProfile(ConsultantProfileDTO updatedProfile) {
        // 실제로는 ID를 검사하고 유효성 검사를 추가(나중에)
        profile = updatedProfile;
        return profile;
    }

    public List<ConsultantEducationDTO> getEducation(String id) {
        // 프로필 ID를 기준으로 교육 정보를 필터링
        return educationList.stream()
                .filter(edu -> edu.getId().equals(id))
                .collect(Collectors.toList());
    }

    public List<ConsultantCareerDTO> getCareer(String id) {
        // 프로필 ID를 기준으로 경력 정보를 필터링
        return careerList.stream()
                .filter(career -> career.getId().equals(id))
                .collect(Collectors.toList());
    }
}