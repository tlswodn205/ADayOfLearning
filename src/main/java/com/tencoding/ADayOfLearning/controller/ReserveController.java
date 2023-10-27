package com.tencoding.ADayOfLearning.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tencoding.ADayOfLearning.dto.request.ReserveRequestDto;
import com.tencoding.ADayOfLearning.dto.response.ReserveListResponseDto;
import com.tencoding.ADayOfLearning.service.ReserveService;

@Controller
@RequestMapping("/reserve")
public class ReserveController {

	@Autowired
	ReserveService reserveService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/reserveList")
	public String reserveList(Model model) {
		List<ReserveListResponseDto> reserveList = reserveService.findReserveByUserId(1);
		model.addAttribute("reserveList", reserveList);
		
		return "reserve/reserveList";
	}
	
	public ResponseEntity<?> insertReserve(ReserveRequestDto reserveRequestDto) {
		return null;
	}
}
