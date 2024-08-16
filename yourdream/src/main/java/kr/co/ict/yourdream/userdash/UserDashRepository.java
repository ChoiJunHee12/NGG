package kr.co.ict.yourdream.userdash;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UserDashRepository extends JpaRepository<MemberDTO,Integer>{
    
    @Query("SELECT COUNT(m.memno) FROM MemberDTO m")
    long countTotalMembers();

    @Query(value = "SELECT COUNT(*) FROM tblogin WHERE TRUNC(logindt) = TRUNC(:today)", nativeQuery = true)
    long countDailyVisitors(@Param("today") Date today);

    @Query("SELECT COUNT(i.intno) FROM IntResDTO i")
    long countTotalIntnos();

    @Query("SELECT COUNT(mc) FROM MemberConsultDTO mc")
    long countTotal();

    @Query("SELECT new kr.co.ict.yourdream.userdash.DailyMemberCount(m.credt, COUNT(m)) " +
           "FROM MemberDTO m " +
           "WHERE m.credt BETWEEN :startDate AND :endDate " +
           "GROUP BY m.credt " +
           "ORDER BY m.credt")
    List<DailyMemberCount> findDailyCounts(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


    @Query("SELECT m.categcd as categCd, COUNT(m) as count " +
           "FROM MemberDTO m " +
           "GROUP BY m.categcd")
    List<Map<String, Object>> findMemberCountByCategcd();
} 