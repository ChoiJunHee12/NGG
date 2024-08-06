package kr.ict.mydream.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ict.mydream.dao.ItvDao;
import kr.ict.mydream.vo.ItvDTO;

@Service
public class ItvService {
    @Autowired
    private ItvDao itvDao;

    public List<ItvDTO> itvAttitudeList() {
        return itvDao.itvAttitudeList();
    }

    public List<ItvDTO> itvCareerList() {
        return itvDao.itvCareerList();
    }
}