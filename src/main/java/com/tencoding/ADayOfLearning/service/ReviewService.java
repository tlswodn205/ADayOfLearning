package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.ADayOfLearning.dto.request.ReviewRequestDto;
import com.tencoding.ADayOfLearning.dto.response.ReviewResponseDto;
import com.tencoding.ADayOfLearning.repository.interfaces.ReviewRepository;
import com.tencoding.ADayOfLearning.repository.model.Review;

@Service
public class ReviewService {

	@Autowired
	ReviewRepository reviewRepository;

	public List<ReviewResponseDto> getReviewsByLectureId(Integer lectureId) {
		return reviewRepository.findByLectureId(lectureId);
	}

	@Transactional
	public ReviewResponseDto insert(ReviewRequestDto reviewRequestDto) {
		reviewRepository.reviewInsert(reviewRequestDto);
		return reviewRepository.findById(reviewRequestDto.getReviewId());
	}

	@Transactional
	public void delete(int reviewId) {
		reviewRepository.deleteByReviewId(reviewId);
	}

	@Transactional
	public void update(ReviewRequestDto reviewRequestDto) {
		Review review = reviewRequestDto.toReview();
		reviewRepository.updateByReviewId(review);
	}
}
