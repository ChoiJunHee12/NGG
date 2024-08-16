package kr.co.ict.yourdream.adminConsultant;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.ict.yourdream.consultantProfile.CnsCareer;
import kr.co.ict.yourdream.consultantProfile.CnsCareerRepository;
import kr.co.ict.yourdream.consultantProfile.CnsCareerVO;
import kr.co.ict.yourdream.consultantProfile.ConsultProfile;
import kr.co.ict.yourdream.consultantProfile.ConsultVO;

@Service
public class AdminConsultService {

    @Autowired
    private AdminConsultRepository adminConsultRepository;

    @Autowired
    private CnsCareerRepository cnsCareerRepository;

    // 모든 컨설턴트 프로필 조회 (읽기 전용 트랜잭션 사용)
    @Transactional(readOnly = true)
    public List<ConsultVO> getAllConsultProfiles() {
        return adminConsultRepository.findAllWithCareers().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    // 새로운 컨설턴트 프로필 생성
    @Transactional
    public ConsultVO createConsultProfile(ConsultVO consultVO) {
        ConsultProfile consultProfile = convertToEntity(consultVO);
        ConsultProfile savedProfile = adminConsultRepository.save(consultProfile);

        // 경력 정보가 있다면 저장
        if (consultVO.getCnscareerVO() != null) {
            for (CnsCareerVO careerVO : consultVO.getCnscareerVO()) {
                CnsCareer career = convertCnsCareerToEntity(careerVO);
                career.setConsultProfile(savedProfile);
                cnsCareerRepository.save(career);
            }
        }

        return convertToVO(savedProfile);
    }

    // 컨설턴트 프로필 삭제
    @Transactional
    public boolean deleteConsultProfile(int cnsno) {
        if (adminConsultRepository.existsById(cnsno)) {
            adminConsultRepository.deleteById(cnsno);
            return true;
        }
        return false;
    }

    // 컨설턴트 프로필 필드 업데이트 (내부 헬퍼 메소드)
    private void updateConsultProfileFields(ConsultProfile profile, ConsultVO vo) {
        BeanUtils.copyProperties(vo, profile, "cnsno", "cnscareerList");
    }

    // 경력 리스트 업데이트 (내부 헬퍼 메소드)
    private void updateCnsCareerList(ConsultProfile profile, List<CnsCareerVO> careerVOList) {
        profile.getCnscareerList().clear();
        if (careerVOList != null) {
            for (CnsCareerVO careerVO : careerVOList) {
                CnsCareer career = convertCnsCareerToEntity(careerVO);
                career.setConsultProfile(profile);
                profile.getCnscareerList().add(career);
            }
        }
    }

    // ConsultProfile 엔티티를 ConsultVO로 변환
    private ConsultVO convertToVO(ConsultProfile profile) {
        ConsultVO vo = new ConsultVO();
        BeanUtils.copyProperties(profile, vo);
        vo.setCnscareerVO(profile.getCnscareerList().stream()
                .map(this::convertCnsCareerToVO)
                .collect(Collectors.toList()));
        return vo;
    }

    // ConsultVO를 ConsultProfile 엔티티로 변환
    private ConsultProfile convertToEntity(ConsultVO vo) {
        ConsultProfile profile = new ConsultProfile();
        BeanUtils.copyProperties(vo, profile);
        return profile;
    }

    // CnsCareer 엔티티를 CnsCareerVO로 변환
    private CnsCareerVO convertCnsCareerToVO(CnsCareer career) {
        CnsCareerVO vo = new CnsCareerVO();
        BeanUtils.copyProperties(career, vo);
        return vo;
    }

    // CnsCareerVO를 CnsCareer 엔티티로 변환
    private CnsCareer convertCnsCareerToEntity(CnsCareerVO vo) {
        CnsCareer career = new CnsCareer();
        BeanUtils.copyProperties(vo, career);
        return career;
    }

    // 컨설턴트 프로필 업데이트
    @Transactional
    public ConsultVO updateConsultProfile(int cnsno, ConsultVO consultVO) {
        return adminConsultRepository.findById(cnsno)
                .map(existingProfile -> {
                    updateConsultProfileFields(existingProfile, consultVO);
                    updateCnsCareerList(existingProfile, consultVO.getCnscareerVO());
                    ConsultProfile updatedProfile = adminConsultRepository.save(existingProfile);
                    return convertToVO(updatedProfile);
                })
                .orElseThrow(() -> new RuntimeException("프로필을 찾을 수 없습니다: " + cnsno));
    }
}