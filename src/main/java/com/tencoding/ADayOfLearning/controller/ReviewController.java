package com.tencoding.ADayOfLearning.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
		System.out.println(reviewRequestDto);
		ReviewResponseDto reviewResponseDto = reviewService.insert(reviewRequestDto);
		return reviewResponseDto;
	}
}
