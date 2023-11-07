package com.tencoding.ADayOfLearning.dto.response;

import lombok.Data;

@Data
public class ReviewResponseDto {
	private int reviewId;
	private int userId;
	private int score;
	private String content;
	private String createdAt;
	private String username;
}
