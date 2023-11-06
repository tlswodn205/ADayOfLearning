package com.tencoding.ADayOfLearning.dto.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class LectureSessionResponseDto {
	private int lectureSessionId;
	private int lectureId;
	private Timestamp sessionDate;
	private Timestamp createdAt;
	private int students;
}
