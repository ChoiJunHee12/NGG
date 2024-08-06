package kr.ict.mydream.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ict.mydream.vo.ItvDTO;

@Mapper
public interface ItvDao {

    public List<ItvDTO> itvAttitudeList();

    public List<ItvDTO> itvCareerList();

}
