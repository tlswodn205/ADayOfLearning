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
public class LectureSession {
	private int lectureSessionId;
	private int lectureId;
	private Timestamp sessionDate;
	private Timestamp createdAt;
}
