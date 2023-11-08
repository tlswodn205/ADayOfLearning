package com.tencoding.ADayOfLearning.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tencoding.ADayOfLearning.dto.request.ReviewRequestDto;
import com.tencoding.ADayOfLearning.dto.response.ReviewResponseDto;
import com.tencoding.ADayOfLearning.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@Autowired
	HttpSession session;

	@PostMapping("/insert")
	public @ResponseBody ReviewResponseDto insert(@RequestBody ReviewRequestDto reviewRequestDto) {
//		User user = (User) session.getAttribute(Define.PRINCIPAL);
		// 유저 null이면 실행 종료
		reviewRequestDto.setUserId(1);
		ReviewResponseDto reviewResponseDto = reviewService.insert(reviewRequestDto);
		return reviewResponseDto;
	}
	
	@DeleteMapping("/delete")
	public @ResponseBody ResponseEntity<?> delete(@RequestBody Map<String, Integer> reviewIdMap) {
//		User user = (User) session.getAttribute(Define.PRINCIPAL);
		// 유저 인가 확인
		reviewService.delete(reviewIdMap.get("reviewId"));
		return ResponseEntity.ok().body("삭제 성공");
	}
	
	@PutMapping("/update")
	public @ResponseBody ResponseEntity<?> update(@RequestBody ReviewRequestDto reviewRequestDto) {
//		User user = (User) session.getAttribute(Define.PRINCIPAL);
		// 유저 인가 확인
		reviewService.update(reviewRequestDto);
		System.out.println(reviewRequestDto);;
		return ResponseEntity.ok().body("삭제 성공");
	}
	
}
