package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.dto.request.LectureRegistarionRequestDto;
import com.tencoding.ADayOfLearning.dto.request.ListSearchRequestDto;
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
//	public List<LectureListItemResponseDto> getMostLecture() {
//		List<LectureListItemResponseDto> lectureList = lectureRepository.findMostLecture();
//		return lectureList;
//	}

	public List<LectureListItemResponseDto> getNewLecture() {
		List<LectureListItemResponseDto> lectureList = lectureRepository.findNewLecture();
		return lectureList;
	}
	// indexPage end

}
