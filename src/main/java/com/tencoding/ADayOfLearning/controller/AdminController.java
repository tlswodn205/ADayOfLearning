package com.tencoding.ADayOfLearning.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tencoding.ADayOfLearning.dto.response.AdminBusinessResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminCustomerResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminMainResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminRequestBusinessResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessUserDetailResponseDto;
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
	
	//user start


	@GetMapping("/customerList")
	public String getCustomer(@RequestParam(required = false) String type, @RequestParam(required = false) String keyword,@RequestParam(defaultValue = "1") Integer page, Model model) {
		ListPagingResponseDto<AdminCustomerResponseDto> listPagingResponseDto = adminService.findAdminCustomerList(type, keyword, page);
		
		model.addAttribute("listPagingResponseDto", listPagingResponseDto);
		return "admin/user/customerList";
	}
	
	@GetMapping("/customerDetail/{userId}")
	public String getCustomerDetail(Model model, @PathVariable Integer userId) {
		AdminCustomerResponseDto adminCustomerResponseDto = adminService.findAdminCustomerDetail(userId);
		model.addAttribute("customerUserData", adminCustomerResponseDto);
		return "admin/user/customerDetail";
	}
	
	//user end
	
	//business start
	
	@GetMapping("/requestBusinessList")
	public String getRequestBusinessList(@RequestParam(required = false) String type, @RequestParam(required = false) String keyword,@RequestParam(defaultValue = "1") Integer page, Model model) {
		ListPagingResponseDto<AdminRequestBusinessResponseDto> listPagingResponseDto = adminService.findAdminRequestBusinessList(type, keyword, page);
		
		model.addAttribute("listPagingResponseDto", listPagingResponseDto);
		return "admin/business/requestBusinessList";
	}
	
	@GetMapping("/requestBusinessDetail/{businessId}")
	public String getRequestBusinessDetail(Model model, @PathVariable Integer businessId) {
		BusinessUserDetailResponseDto businessUserDetailRequestDto = adminService.findAdminRequestBusinessDetail(businessId);
		model.addAttribute("businessUserData", businessUserDetailRequestDto);
		return "admin/business/requestBusinessDetail";
	}
	
	@PostMapping("/agreeBusiness")
	public String postAgreeBusiness() {
		return "admin/agreeBusiness";
	}
	
	@GetMapping("/businessList")
	public String getBusinessList(@RequestParam(required = false) String type, @RequestParam(required = false) String keyword,@RequestParam(defaultValue = "1") Integer page, Model model) {
		ListPagingResponseDto<AdminBusinessResponseDto> listPagingResponseDto = adminService.findAdminBusinessList(type, keyword, page);
		
		model.addAttribute("listPagingResponseDto", listPagingResponseDto);
		return "admin/business/requestBusinessList";
	}
	
	@GetMapping("/businessDetail/{businessId}")
	public String getBusinessDetail(Model model, @PathVariable Integer businessId) {
		BusinessUserDetailResponseDto businessUserDetailRequestDto = adminService.findAdminBusinessDetail(businessId);
		model.addAttribute("businessUserData", businessUserDetailRequestDto);
		return "admin/business/BusinessDetail";
	}
	//business end
	
	
}
