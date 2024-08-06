package kr.ict.mydream.membership;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailCertificationDAO {
    int countByEmail(String email);
}
