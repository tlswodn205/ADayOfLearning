package com.tencoding.ADayOfLearning.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tencoding.ADayOfLearning.dto.response.BusinessMainUserDataResponseDto;
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
	
	@GetMapping("")
	public String getMain(Model model) {
		
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		BusinessMainUserDataResponseDto userData = businessService.findUserData(1);
		model.addAttribute("userData", userData);
		return "/business/main"; 
	}
}
