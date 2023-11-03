package com.tencoding.ADayOfLearning.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tencoding.ADayOfLearning.dto.response.BusinessLectureListResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessLectureResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessMainUserDataResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessUserDetailResponseDto;
import com.tencoding.ADayOfLearning.repository.model.User;
import com.tencoding.ADayOfLearning.service.BusinessService;
import com.tencoding.ADayOfLearning.util.Define;

@Controller
@RequestMapping("/business")
public class BusinessController {

	@Autowired
	BusinessService businessService;
	
	@Autowired
	HttpSession session;
	
	//main start 
	
	@GetMapping("")
	public String getMain(Model model) {	
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		BusinessMainUserDataResponseDto userData = businessService.findUserData(1);
		model.addAttribute("userData", userData);
		
		// 예약 관련 메인
		int countTodayLecture = businessService.countTodayLecture(1);
		int countTodayUser = businessService.countTodayUser(1);
		model.addAttribute("countTodayLecture", countTodayLecture);
		model.addAttribute("countTodayUser", countTodayUser);
		
		return "/business/main"; 
	}
	
	//main end
	
	//user start
	

	@GetMapping("/userDetail")
	public String getUserDetail(Model model) {
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		BusinessUserDetailResponseDto businessUserDetailRequestDto =  businessService.findBusinessByUserID(user);
		model.addAttribute("businessUserData", businessUserDetailRequestDto);
		return "/business/user/userDetail";
	}
	
	//user end
	
	// lecture start
	
	@GetMapping("/lectureList")
	public String getLectureList(Model model) {
		List<BusinessLectureListResponseDto> lectureList = businessService.findLectureByUserId(1);
		model.addAttribute("lectureList", lectureList);
		return "/business/lecture/list";
	}
	
	@GetMapping("/lectureDetail/{id}")
	public String getLectureDetail(Model model, @PathVariable Integer id) {
		List<BusinessLectureResponseDto> lecture = businessService.findByLectureSessionId(id);
		model.addAttribute("lecture", lecture);
		return "/business/lecture/detail";
	}
	
	// lecture end
	
}
