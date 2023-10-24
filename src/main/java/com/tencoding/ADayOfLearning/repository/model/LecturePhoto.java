package com.tencoding.ADayOfLearning.repository.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class LecturePhoto {
	private int lecturePhotoId;
	private int lectureId;
	private boolean isThumbnail;
	private String img;
	private Timestamp createdAt;
}
