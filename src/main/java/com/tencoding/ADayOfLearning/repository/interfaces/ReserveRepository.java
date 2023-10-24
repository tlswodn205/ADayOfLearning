package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tencoding.ADayOfLearning.repository.model.Reserve;


@Mapper
public interface ReserveRepository {
	public int insert(Reserve reserve);
	public int updateByReserveId(Reserve reserve);
	public int deleteByReserveId(int reserveId);
	public Reserve findByReserveId(int reserveId);
	public List<Reserve> findByAll();
}