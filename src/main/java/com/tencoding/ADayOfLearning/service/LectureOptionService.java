package com.tencoding.ADayOfLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.dto.request.LectureRegistarionRequestDto;
import com.tencoding.ADayOfLearning.repository.interfaces.LectureOptionRepository;
import com.tencoding.ADayOfLearning.repository.model.LectureOption;

@Service
public class LectureOptionService {

	@Autowired
	LectureOptionRepository lectureOptionRepository;

	public void insertLectureOption(LectureRegistarionRequestDto dto, int registeredLectureId) {
		insertOptionIfTrue(dto.getParkingSpaceAvailable(), 1, registeredLectureId);
		insertOptionIfTrue(dto.getRecordingProvided(), 2, registeredLectureId);
		insertOptionIfTrue(dto.getMaterialsProvided(), 3, registeredLectureId);
		insertOptionIfTrue(dto.getKidsPlayAreaAvailable(), 4, registeredLectureId);
		insertOptionIfTrue(dto.getWomenOnly(), 5, registeredLectureId);
		insertOptionIfTrue(dto.getMenOnly(), 6, registeredLectureId);
		insertOptionIfTrue(dto.getNoKidsZone(), 7, registeredLectureId);
	}

	public void insertOptionIfTrue(Boolean condition, int optionId, int lectureId) {
		Boolean conditionIsNotNull = condition != null ? condition : false;
		if (conditionIsNotNull) {
			LectureOption option = LectureOption.builder().optionId(optionId).lectureId(lectureId).build();

			lectureOptionRepository.insert(option);
		}
	}
}
