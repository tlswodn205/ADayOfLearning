package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
}