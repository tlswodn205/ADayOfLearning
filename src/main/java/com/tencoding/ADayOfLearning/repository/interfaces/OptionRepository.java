package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tencoding.ADayOfLearning.repository.model.Option;


@Mapper
public interface OptionRepository {
	public int insert(Option option);
	public int updateByOptionId(Option option);
	public int deleteByOptionId(int optionId);
	public Option findByOptionId(int optionId);
	public List<Option> findByAll();
}