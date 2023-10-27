package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.dto.request.ListSearchRequestDto;
import com.tencoding.ADayOfLearning.dto.response.LectureListItemResponseDto;
import com.tencoding.ADayOfLearning.repository.interfaces.LectureRepository;

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
}
