package com.tencoding.ADayOfLearning.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tencoding.ADayOfLearning.dto.ReserveListPageResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ReserveListResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ReserveResponseDto;
import com.tencoding.ADayOfLearning.repository.model.User;
import com.tencoding.ADayOfLearning.service.ReserveService;
import com.tencoding.ADayOfLearning.util.Define;

@Controller
@RequestMapping("/reserve")
public class ReserveController {

	@Autowired
	ReserveService reserveService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/list")
	public String getReserveList(@RequestParam(required = false) String type, @RequestParam(required = false) String keyword, @RequestParam(defaultValue = "1") Integer page,@RequestParam(required = false) String status, Model model) {
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		if (principal == null) {
			return "redirect:/user/signIn";
		}
		ReserveListPageResponseDto<ReserveListResponseDto> reserveList = reserveService.findReserveByUserId(type, keyword, page, status, principal.getUserId());
		model.addAttribute("reserveList", reserveList);
		
		return "reserve/reserveList";
	}
	
	@GetMapping("/detail/{id}")
	public String getReserveDetail(Model model, @PathVariable Integer id) {
		ReserveResponseDto reserve = reserveService.findReserveByReserveId(id);
		model.addAttribute("reserve", reserve);
		return "reserve/reserveDetail";
	}
	
}
