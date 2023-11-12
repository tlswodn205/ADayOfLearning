package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tencoding.ADayOfLearning.dto.request.ListSearchRequestDto;
import com.tencoding.ADayOfLearning.dto.request.MapBoundsRequestDto;
import com.tencoding.ADayOfLearning.dto.response.AdminLectureListResponseDto;
import com.tencoding.ADayOfLearning.dto.response.LastLectureResponseDto;
import com.tencoding.ADayOfLearning.dto.response.LectureListItemResponseDto;
import com.tencoding.ADayOfLearning.dto.response.PagingResponseDto;
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

	public List<LectureListItemResponseDto> findNewLecture();

	public List<LectureListItemResponseDto> findByMapBounds(MapBoundsRequestDto mapBoundsRequestDto);

	public LastLectureResponseDto findLastLecture();

	public int findAllCount(ListSearchRequestDto listSearchRequestDto);

	public List<AdminLectureListResponseDto> findLectureList(@Param("type") String type, @Param("keyword") String keyword, @Param("startNum") int startNum);

	public PagingResponseDto findLecturePaging(@Param("type") String type, @Param("keyword") String keyword, @Param("page") int page);
}