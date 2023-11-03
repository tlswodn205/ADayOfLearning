package com.tencoding.ADayOfLearning.repository.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LecturePhoto {
	private int lecturePhotoId;
	private int lectureId;
	private boolean isThumbnail;
	private String img;
	private Timestamp createdAt;
}
