package com.tencoding.ADayOfLearning.repository.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Reserve {
	private int reserveId;
	private int userId;
	private int lectureSessionId;
	private Timestamp createdAt;
}
