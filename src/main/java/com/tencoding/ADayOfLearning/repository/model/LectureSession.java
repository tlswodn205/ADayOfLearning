package com.tencoding.ADayOfLearning.repository.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class LectureSession {
	private int lectureSessionId;
	private int lectureId;
	private Timestamp sessionDate;
	private Timestamp createdAt;
}
