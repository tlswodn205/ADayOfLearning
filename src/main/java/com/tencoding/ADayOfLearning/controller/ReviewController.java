package com.tencoding.ADayOfLearning.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.tencoding.ADayOfLearning.handler.exception.CustomFetchRestfulException;
import com.tencoding.ADayOfLearning.repository.model.User;
import com.tencoding.ADayOfLearning.service.ReviewService;
import com.tencoding.ADayOfLearning.util.Define;

@Controller
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@Autowired
	HttpSession session;

	@PostMapping("/insert")
	public @ResponseBody ResponseEntity<?> insert(@RequestBody ReviewRequestDto reviewRequestDto) {
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		if(user == null) {
			throw new CustomFetchRestfulException("로그인을 먼저 해야 합니다.", HttpStatus.BAD_REQUEST);
		}
		
		reviewRequestDto.setUserId(user.getUserId());
		ReviewResponseDto reviewResponseDto = reviewService.insert(reviewRequestDto);
		
		return ResponseEntity.ok().body(reviewResponseDto);
	}
	
	@DeleteMapping("/delete")
	public @ResponseBody ResponseEntity<?> delete(@RequestBody Map<String, Integer> reviewIdMap) {
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		if(user == null) {
			throw new CustomFetchRestfulException("로그인을 먼저 해야 합니다.", HttpStatus.BAD_REQUEST);
		}
		
		reviewService.delete(reviewIdMap.get("reviewId"), user.getUserId());
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/update")
	public @ResponseBody ResponseEntity<?> update(@RequestBody ReviewRequestDto reviewRequestDto) {
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		if(user == null) {
			throw new CustomFetchRestfulException("로그인을 먼저 해야 합니다.", HttpStatus.BAD_REQUEST);
		}

		reviewRequestDto.setUserId(user.getUserId());
		reviewService.update(reviewRequestDto);
		return ResponseEntity.ok().build();
	}
	
}
