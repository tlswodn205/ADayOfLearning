package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.repository.interfaces.LectureOptionRepository;
import com.tencoding.ADayOfLearning.repository.model.LectureOption;

@Service
public class LectureOptionService {

	@Autowired
	LectureOptionRepository lectureOptionRepository;
	
	public List<LectureOption> getLectureOptionByLectureId(int lectureId) {
		List<LectureOption> list = lectureOptionRepository.findByLectureId(lectureId);
		
		return list;
	}

}
