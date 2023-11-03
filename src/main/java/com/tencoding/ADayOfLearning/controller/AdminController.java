package com.tencoding.ADayOfLearning.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tencoding.ADayOfLearning.dto.response.AdminMainResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminRequestBusinessResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ListPagingResponseDto;
import com.tencoding.ADayOfLearning.service.AdminService;

import lombok.Getter;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("")
	public String getMain(Model model) {
		AdminMainResponseDto adminMainResponseDto =  adminService.getMainData();
		model.addAttribute("adminMainResponseDto", adminMainResponseDto);
		return "admin/main";
	}
	
	@GetMapping("/requestBusiness")
	public String getRequestBusiness(@RequestParam(required = false) String type, @RequestParam(required = false) String keyword,@RequestParam(defaultValue = "1") Integer page, Model model) {
		ListPagingResponseDto<AdminRequestBusinessResponseDto> listPagingResponseDto = adminService.findAdminRequestBusinessList(type, keyword, page);
		System.out.println("==============================================");
		System.out.println(listPagingResponseDto.getList().get(0).getUsername());
		
		model.addAttribute("listPagingResponseDto", listPagingResponseDto);
		return "admin/business/requestBusiness";
	}
	
	@GetMapping("/agreeBusiness")
	public String getAgreeBusiness() {
		return "admin/agreeBusiness";
	}
}
