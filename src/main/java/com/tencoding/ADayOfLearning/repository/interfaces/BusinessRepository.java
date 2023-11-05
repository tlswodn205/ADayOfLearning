package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tencoding.ADayOfLearning.dto.response.AdminBusinessResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminMainRequestBusinessResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminRequestBusinessResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessUserDetailResponseDto;
import com.tencoding.ADayOfLearning.dto.response.PagingResponseDto;
import com.tencoding.ADayOfLearning.repository.model.Business;

@Mapper
public interface BusinessRepository {
	public int insert(Business business);
	public int updateByBusinessId(Business business);
	public int deleteByBusinessId(int businessId);
	public Business findByBusinessId(int businessId);
	public List<Business> findByAll();
	public Business findByUserId(int userId);
	public int updateByUserId(Business business);
	public List<AdminMainRequestBusinessResponseDto> findRequestBusiness();
	public PagingResponseDto findRequestBusinessPaging(@Param("type") String type, @Param("keyword") String keyword, @Param("page") Integer page);
	public List<AdminRequestBusinessResponseDto> findRequestBusinessList(@Param("type") String type, @Param("keyword") String keyword, @Param("startNum") int startNum);
	public BusinessUserDetailResponseDto findRequestBusinessByBusinessId(Integer businessId);
	public PagingResponseDto findBusinessPaging(@Param("type") String type, @Param("keyword") String keyword, @Param("page") Integer page);
	public List<AdminBusinessResponseDto> findBusinessList(@Param("type") String type, @Param("keyword") String keyword, @Param("startNum") int startNum);
	public BusinessUserDetailResponseDto findBusinessByBusinessId(Integer businessId);
}