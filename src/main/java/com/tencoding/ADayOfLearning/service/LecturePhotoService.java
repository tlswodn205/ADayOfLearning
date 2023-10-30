package com.tencoding.ADayOfLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.repository.interfaces.LecturePhotoRepository;

@Service
public class LecturePhotoService {
	
	@Autowired
	LecturePhotoRepository lecturePhotoRepository;
}
