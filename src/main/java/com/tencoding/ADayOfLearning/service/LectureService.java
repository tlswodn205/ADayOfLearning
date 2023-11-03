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

	/**
	 * 강의 등록
	 * 
	 * @param LectureRegistarionRequestDto
	 * @return 생성된 강의의 id
	 */
	public int insertLecture(LectureRegistarionRequestDto dto, int userId) {

		Lecture lecture = new Lecture().builder().categoryId(dto.getCategoryId()).userId(userId).title(dto.getTitle())
				.content(dto.getContent()).address(dto.getAddress()).addressDetail(dto.getAddressDetail())
				.maximum(dto.getMaximum()).price(dto.getPrice()).phoneNumber(dto.getPhoneNumber()).state(1)
				.latitude(dto.getLatitude()).longitude(dto.getLongitude()).duration(dto.getDuration()).build();

		lectureRepository.insert(lecture);

		return lecture.getLectureId();
	}

}
