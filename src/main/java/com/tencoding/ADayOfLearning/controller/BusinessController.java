package com.tencoding.ADayOfLearning.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tencoding.ADayOfLearning.dto.request.BusinessUserRequestDto;
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
	
	@PostMapping("/businessUpdate")
	public String businessUpdate(BusinessUserRequestDto businessUserRequestDto) {
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		businessService.updateBusinessUserData(businessUserRequestDto, user.getUserId());
		return "redirect:/business/userDetail";
	}
	
	//user end
	
}
