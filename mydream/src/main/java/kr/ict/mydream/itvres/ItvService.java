package kr.ict.mydream.itvres;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ict.mydream.dto.ItvDTO;

import java.util.Map;

@Service
public class ItvService {
    @Autowired
    private ItvDao itvDao;

    public List<Map<String, Object>> itvAttitudeList() {
        return itvDao.itvAttitudeList();
    }

    public List<Map<String, Object>> itvCareerList() {
        return itvDao.itvCareerList();
    }

    public List<ItvDTO> detail(int intno) {
        System.out.println("intno:" + intno);
        return itvDao.detail(intno);
    }

    public List<Integer> faceData(int intno) {
        return itvDao.faceData(intno);
    }

    public List<Integer> getBarData(int intno) {
        return itvDao.getBarData(intno);
    }

    public List<String> wordCloud(int intno) {
        return itvDao.wordCloud(intno);
    }

    public List<Map<String, String>> questionData(int intno) {
        return itvDao.questionData(intno);
    }
}