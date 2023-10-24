package com.tencoding.ADayOfLearning.repository.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class LectureOption {
	private int lectureOptionId;
	private int optionId;
	private int lectureId;
	private Timestamp createdAt;
}
