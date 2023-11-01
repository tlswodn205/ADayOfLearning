package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tencoding.ADayOfLearning.repository.model.LecturePhoto;

@Mapper
public interface LecturePhotoRepository {
	public int insert(LecturePhoto lecturePhoto);

	public int updateByLecturePhotoId(LecturePhoto lecturePhoto);

	public int deleteByLecturePhotoId(int lecturePhotoId);

	public LecturePhoto findByLecturePhotoId(int lecturePhotoId);

	public List<LecturePhoto> findByLectureId(int id);

	public List<LecturePhoto> findByAll();
}