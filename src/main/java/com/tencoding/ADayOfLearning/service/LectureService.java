package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.dto.request.ListSearchRequestDto;
import com.tencoding.ADayOfLearning.dto.request.MapBoundsRequestDto;
import com.tencoding.ADayOfLearning.dto.response.LastLectureResponseDto;
import com.tencoding.ADayOfLearning.dto.response.LectureListItemResponseDto;
import com.tencoding.ADayOfLearning.repository.interfaces.LectureRepository;
import com.tencoding.ADayOfLearning.repository.model.Lecture;

@Service
public class LectureService {

	@Autowired
	LectureRepository lectureRepository;

	public List<LectureListItemResponseDto> getLectureList() {
		return lectureRepository.findByStateAll();
	}

	public List<LectureListItemResponseDto> getLectureListBySearch(ListSearchRequestDto dto) {
		return lectureRepository.findBySearch(dto);
	}

	public Lecture getLectureById(Integer id) {
		return lectureRepository.findByLectureId(id);
	}

	// indexPage start
	public List<LectureListItemResponseDto> getNewLecture() {
		List<LectureListItemResponseDto> lectureList = lectureRepository.findNewLecture();
		return lectureList;
	}
	// indexPage end

	public List<LectureListItemResponseDto> getLectureListByMap(MapBoundsRequestDto mapBoundsRequestDto) {
		List<LectureListItemResponseDto> lectureList = lectureRepository.findByMapBounds(mapBoundsRequestDto);
		return lectureList;
	}

	public LastLectureResponseDto getLastLecture() {
		return lectureRepository.findLastLecture();
	}

	public int getCount(ListSearchRequestDto listSearchRequestDto) {
		
		return lectureRepository.findAllCount(listSearchRequestDto);
	}

}
