package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tencoding.ADayOfLearning.dto.response.PagingResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ReserveListResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ReserveResponseDto;
import com.tencoding.ADayOfLearning.repository.model.Reserve;


@Mapper
public interface ReserveRepository {
	public int insert(Reserve reserve);
	public int updateByReserveId(Reserve reserve);
	public int deleteByReserveId(int reserveId);
	public Reserve findByReserveId(int reserveId);
	public List<Reserve> findByAll();
	public List<ReserveListResponseDto> findByUserId(@Param("type")String type, @Param("keyword")String keyword, @Param("page")Integer page, @Param("status")String status, @Param("userId")int userId, @Param("startNum")int startNum );
	public ReserveResponseDto findAllByReserveId(int reserveId);
	public PagingResponseDto findPaging(@Param("type")String type, @Param("keyword")String keyword, @Param("page")Integer page, @Param("status")String status, @Param("userId")int userId);
}