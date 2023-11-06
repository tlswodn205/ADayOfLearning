package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tencoding.ADayOfLearning.dto.request.ListSearchRequestDto;
import com.tencoding.ADayOfLearning.dto.response.LectureListItemResponseDto;
import com.tencoding.ADayOfLearning.repository.model.Lecture;

@Mapper
public interface LectureRepository {
	public int insert(Lecture lecture);

	public int updateByLectureId(Lecture lecture);

	public int deleteByLectureId(int lectureId);

	public Lecture findByLectureId(int lectureId);

	public List<Lecture> findByAll();

	public List<LectureListItemResponseDto> findByStateAll();

	public List<LectureListItemResponseDto> findBySearch(ListSearchRequestDto dto);

	public List<LectureListItemResponseDto> findMostLecture();

	public List<LectureListItemResponseDto> findNewLecture();
}