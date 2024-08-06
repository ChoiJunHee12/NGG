package kr.ict.mydream.resume;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ict.mydream.vo.ResumeVO;

@Service
public class ResumeService {
    @Autowired
    private ResumeDao resumeDao;

    public List<ResumeVO> cvList(ResumeVO vo) {
        return resumeDao.resumeList(vo);
    }

    public ResumeVO cvDetail(int num) {
        return resumeDao.resumeDetail(num);
    }

    public void cvAdd(ResumeVO vo) {
        resumeDao.resumeAdd(vo);
    }

    public ResumeVO cvUpload(ResumeVO vo) {
        return resumeDao.resumeUpload(vo);
    }

    public ResumeVO cvImgUp(ResumeVO vo) {
        return resumeDao.resumeImgUp(vo);
    }

    public void cvUpdate(ResumeVO vo) {
        resumeDao.resumeUpdate(vo);
    }

    public void cvDelete(int num) {
        resumeDao.resumeDelete(num);
    }
}
