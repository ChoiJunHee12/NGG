package kr.co.ict.yourdream.consultantProfile;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.jpa.QueryHints;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConsultProfileService {

    @Autowired
    private ConsultProfileRepository consultProfileRepository;

    @Autowired
    private CnsCareerRepository cnsCareerRepository;

    @Autowired
    private EntityManager entityManager;

    // 파일 업로드 경로 설정
    private final static String filePath = Paths.get("").toAbsolutePath().toString().replace("yourdream", "")
            + "/reactfront/public/img";

    // 특정 ID로 컨설턴트 프로필을 기본 정보만 조회하는 메서드
    @Transactional(readOnly = true)
    public ConsultVO getBasicConsultProfileById(int cnsno) {
        return consultProfileRepository.findBasicProfileById(cnsno)
                .map(this::convertToVO)
                .orElseThrow(() -> new RuntimeException("프로필을 찾을 수 없습니다: " + cnsno));
    }

    // 특정 ID로 컨설턴트 프로필을 조회하는 메서드 (모든 경력 정보 포함)
    @Transactional(readOnly = true)
    public ConsultVO getConsultProfileById(int cnsno) {
        TypedQuery<ConsultProfile> query = entityManager.createQuery(
                "SELECT cp FROM ConsultProfile cp LEFT JOIN FETCH cp.cnscareerList WHERE cp.cnsno = :cnsno",
                ConsultProfile.class);
        query.setParameter("cnsno", cnsno);
        query.setHint(QueryHints.HINT_READONLY, true);
        ConsultProfile profile = query.getSingleResult();
        return convertToVO(profile);
    }

    // 새로운 컨설턴트 프로필을 생성하는 메서드
    @Transactional
    public ConsultVO createConsultProfile(ConsultVO consultVO) {
        ConsultProfile consultProfile = convertToEntity(consultVO);
        ConsultProfile savedProfile = consultProfileRepository.save(consultProfile);

        if (consultVO.getCnscareerVO() != null) {
            for (CnsCareerVO careerVO : consultVO.getCnscareerVO()) {
                CnsCareer career = convertCnsCareerToEntity(careerVO);
                career.setConsultProfile(savedProfile);
                cnsCareerRepository.save(career);
            }
        }

        return convertToVO(savedProfile);
    }

    // 기존 컨설턴트 프로필을 업데이트하는 메서드
    @Transactional
    public ConsultVO updateConsultProfile(int cnsno, ConsultVO consultVO) {
        return consultProfileRepository.findById(cnsno)
                .map(existingProfile -> {
                    updateConsultProfileFields(existingProfile, consultVO);
                    updateCnsCareerList(existingProfile, consultVO.getCnscareerVO());
                    ConsultProfile updatedProfile = consultProfileRepository.save(existingProfile);
                    return convertToVO(updatedProfile);
                })
                .orElseThrow(() -> new RuntimeException("프로필을 찾을 수 없습니다: " + cnsno));
    }

    // 컨설턴트 프로필을 삭제하는 메서드
    @Transactional
    public boolean deleteConsultProfile(int cnsno) {
        if (consultProfileRepository.existsById(cnsno)) {
            consultProfileRepository.deleteById(cnsno);
            return true;
        }
        return false;
    }

    // 프로필 이미지 파일을 업로드하는 메서드
    @Transactional
    public String uploadProfileImage(MultipartFile mf) throws IOException {
        if (mf == null || mf.isEmpty()) {
            throw new IllegalArgumentException("파일이 전송되지 않았습니다.");
        }

        // 파일 이름을 UUID로 생성하여 저장
        String filename = UUID.randomUUID().toString().substring(0, 8) + "_" + mf.getOriginalFilename();
        File f = new File(filePath + "/upimg/" + filename);
        mf.transferTo(f);

        return filename;
    }

    // 프로필 이미지를 업데이트하는 메서드
    @Transactional
    public void updateProfileImage(int cnsno, String imgname) {
        consultProfileRepository.updateProfileImage(cnsno, imgname);
        System.out.println("프로필 이미지 업데이트 성공. cnsno: " + cnsno + ", imgname: " + imgname);
    }

    // 컨설턴트 프로필의 필드를 업데이트하는 내부 헬퍼 메서드
    private void updateConsultProfileFields(ConsultProfile profile, ConsultVO vo) {
        BeanUtils.copyProperties(vo, profile, "cnsno", "cnscareerList");
    }

    // 컨설턴트 프로필의 경력 리스트를 업데이트하는 내부 헬퍼 메서드
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

    // ConsultProfile 엔티티를 ConsultVO로 변환하는 내부 헬퍼 메서드
    private ConsultVO convertToVO(ConsultProfile profile) {
        ConsultVO vo = new ConsultVO();
        BeanUtils.copyProperties(profile, vo);
        vo.setCnscareerVO(profile.getCnscareerList().stream()
                .map(this::convertCnsCareerToVO)
                .collect(Collectors.toList()));
        return vo;
    }

    // ConsultVO를 ConsultProfile 엔티티로 변환하는 내부 헬퍼 메서드
    private ConsultProfile convertToEntity(ConsultVO vo) {
        ConsultProfile profile = new ConsultProfile();
        BeanUtils.copyProperties(vo, profile);
        return profile;
    }

    // CnsCareer 엔티티를 CnsCareerVO로 변환하는 내부 헬퍼 메서드
    private CnsCareerVO convertCnsCareerToVO(CnsCareer career) {
        CnsCareerVO vo = new CnsCareerVO();
        BeanUtils.copyProperties(career, vo);
        return vo;
    }

    // CnsCareerVO를 CnsCareer 엔티티로 변환하는 내부 헬퍼 메서드
    private CnsCareer convertCnsCareerToEntity(CnsCareerVO vo) {
        CnsCareer career = new CnsCareer();
        BeanUtils.copyProperties(vo, career);
        return career;
    }
}
