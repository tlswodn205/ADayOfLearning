package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tencoding.ADayOfLearning.dto.request.ReviewRequestDto;
import com.tencoding.ADayOfLearning.dto.response.ReviewResponseDto;
import com.tencoding.ADayOfLearning.repository.model.Review;


@Mapper
public interface ReviewRepository {
	public int insert(Review review);
	public int updateByReviewId(Review review);
	public int deleteByReviewId(int reviewId);
	public Review findByReviewId(int reviewId);
	public List<Review> findByAll();
	public List<ReviewResponseDto> findByLectureId(Integer lectureId);
	public void reviewInsert(ReviewRequestDto reviewRequestDto);
	public ReviewResponseDto findById(int reviewId);
	public Review findByLectureIdAndUserId(@Param("lectureId") int lectureId, @Param("userId") int userId);
	public Review findByReviewIdAndUserId(@Param("reviewId") int reviewId, @Param("userId") int userId);
}