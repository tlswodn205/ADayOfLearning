package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tencoding.ADayOfLearning.repository.model.LectureOption;

@Mapper
public interface LectureOptionRepository {
	public int insert(LectureOption lectureOption);
	public int updateByLectureOptionId(LectureOption lectureOption);
	public int deleteByLectureOptionId(int lectureOptionId);
	public LectureOption findByLectureOptionId(int lectureOptionId);
	public List<LectureOption> findByAll();
}