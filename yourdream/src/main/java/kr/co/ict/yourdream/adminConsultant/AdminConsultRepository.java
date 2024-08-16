package kr.co.ict.yourdream.adminConsultant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import kr.co.ict.yourdream.consultantProfile.ConsultProfile;
import org.springframework.data.jpa.repository.Query;

public interface AdminConsultRepository extends JpaRepository<ConsultProfile, Integer> {

    // 모든 컨설턴트 프로필을 경력 정보와 함께 조회
    @Query("SELECT DISTINCT cp FROM ConsultProfile cp LEFT JOIN FETCH cp.cnscareerList")
    List<ConsultProfile> findAllWithCareers();
}
