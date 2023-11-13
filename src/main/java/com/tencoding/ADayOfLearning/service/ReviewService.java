package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.ADayOfLearning.dto.request.ReviewRequestDto;
import com.tencoding.ADayOfLearning.dto.response.ReviewResponseDto;
import com.tencoding.ADayOfLearning.handler.exception.CustomFetchRestfulException;
import com.tencoding.ADayOfLearning.repository.interfaces.ReviewRepository;
import com.tencoding.ADayOfLearning.repository.model.Review;

@Service
public class ReviewService {

	@Autowired
	ReviewRepository reviewRepository;
	
	/**
	 * 리뷰 리스트 조회
	 * @param lectureId
	 * @return
	 */
	public List<ReviewResponseDto> getReviewsByLectureId(Integer lectureId) {
		return reviewRepository.findByLectureId(lectureId);
	}

	/**
	 * 리뷰 등록
	 * @param reviewRequestDto
	 * @return reviewId
	 */
	@Transactional
	public ReviewResponseDto insert(ReviewRequestDto reviewRequestDto) {
		reviewCheck(reviewRequestDto.getLectureId(), reviewRequestDto.getUserId());
	
		reviewRepository.reviewInsert(reviewRequestDto);
		return reviewRepository.findById(reviewRequestDto.getReviewId());
	}

	/**
	 * 리뷰 삭제
	 * @param reviewId
	 * @param userId 
	 */
	@Transactional
	public void delete(int reviewId, int userId) {
		reviewUserCheck(reviewId, userId);
		reviewRepository.deleteByReviewId(reviewId);
	}

	/**
	 * 리뷰 수정
	 * @param reviewRequestDto
	 */
	@Transactional
	public void update(ReviewRequestDto reviewRequestDto) {
		reviewUserCheck(reviewRequestDto.getReviewId(), reviewRequestDto.getUserId());
		Review review = reviewRequestDto.toReview();
		reviewRepository.updateByReviewId(review);
	}
	
	/**
	 * 리뷰 중복 여부 있는지 확인
	 * @param lectureId
	 * @param userId
	 * @return
	 */
	public void reviewCheck(int lectureId, int userId) {
		Review reviewEntity = reviewRepository.findByLectureIdAndUserId(lectureId, userId);
		if(reviewEntity != null) {
			throw new CustomFetchRestfulException("이미 등록된 리뷰가 있습니다.", HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * 해당 리뷰가 내 리뷰인지 확인
	 * @param reviewId
	 * @param userId
	 */
	public void reviewUserCheck(int reviewId, int userId) {
		Review reviewEntity = reviewRepository.findByReviewIdAndUserId(reviewId, userId);
		if(reviewEntity == null) {
			throw new CustomFetchRestfulException("리뷰 권한 오류", HttpStatus.BAD_REQUEST);
		}
	}
}
