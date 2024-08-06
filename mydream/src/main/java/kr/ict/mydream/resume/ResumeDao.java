package kr.ict.mydream.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ict.mydream.vo.ResumeVO;

@Mapper
public interface ResumeDao {
    public List<ResumeVO> resumeList(ResumeVO vo); // 이력서 리스트

    public ResumeVO resumeDetail(int num); // 이력서 상세보기

    public void resumeAdd(ResumeVO vo); // 이력서 추가

    public ResumeVO resumeUpload(ResumeVO vo); // 이력서 업로드

    public ResumeVO resumeImgUp(ResumeVO vo); // 이력서 사진 추가

    public void resumeUpdate(ResumeVO vo); // 이력서 수정

    public void resumeDelete(int num); // 이력서 삭제
}
