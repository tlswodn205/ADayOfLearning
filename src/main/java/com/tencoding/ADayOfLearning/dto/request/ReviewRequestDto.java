package com.tencoding.ADayOfLearning.dto.request;

import com.tencoding.ADayOfLearning.repository.model.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDto {
	private int reviewId;
	private int lectureId;
	private int userId;
	private int score;
	private String content;
	
	public Review toReview() {
		return Review.builder()
					.reviewId(reviewId)
					.lectureId(lectureId)
					.userId(userId)
					.score(score)
					.content(content)
					.build();
	}
}
