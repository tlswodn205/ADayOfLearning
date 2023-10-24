package com.tencoding.ADayOfLearning.repository.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Review {
	private int reviewId;
	private int lectureId;
	private int userId;
	private int score;
	private String content;
	private Timestamp createdAt;
}
