package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.dto.request.ReserveDataRequestDto;
import com.tencoding.ADayOfLearning.dto.response.LectureSessionResponseDto;
import com.tencoding.ADayOfLearning.repository.interfaces.LectureSessionRepository;

@Service
public class LectureSessionService {

	@Autowired
	LectureSessionRepository lectureSessionRepository;

	public List<LectureSessionResponseDto> findByLectureIdAndDate(ReserveDataRequestDto reserveDataRequestDto) {
		return lectureSessionRepository.findByLectureIdAndDate(reserveDataRequestDto);
	}
}
