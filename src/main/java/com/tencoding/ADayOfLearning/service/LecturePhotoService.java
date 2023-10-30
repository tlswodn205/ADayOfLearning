package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.repository.interfaces.LecturePhotoRepository;
import com.tencoding.ADayOfLearning.repository.model.LecturePhoto;

@Service
public class LecturePhotoService {
	
	@Autowired
	LecturePhotoRepository lecturePhotoRepository;
	
	public List<LecturePhoto> getLecturePhotosById(Integer id) {
		return lecturePhotoRepository.findByLectureId(id);
	}
}
