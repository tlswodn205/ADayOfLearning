package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tencoding.ADayOfLearning.repository.model.LectureSession;


@Mapper
public interface LectureSessionRepository {
	public int insert(LectureSession lectureSession);
	public int updateByLectureSessionId(LectureSession lectureSession);
	public int deleteByLectureSessionId(int lectureSessionId);
	public LectureSession findByLectureSessionId(int lectureSessionId);
	public List<LectureSession> findByAll();
}