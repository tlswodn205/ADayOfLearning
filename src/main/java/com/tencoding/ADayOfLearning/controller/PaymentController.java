package com.tencoding.ADayOfLearning.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tencoding.ADayOfLearning.dto.request.PaymentRequestDto;
import com.tencoding.ADayOfLearning.dto.request.ReserveRequestDto;
import com.tencoding.ADayOfLearning.service.PaymentService;
import com.tencoding.ADayOfLearning.service.ReserveService;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@Autowired
	ReserveService reserveService;
	
	@Autowired
	HttpSession session;
	
	
	@GetMapping("/payRequest")
	public String paymentRequest(Model model) {
		PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
		model.addAttribute("payRequest", paymentRequestDto);
		
		return "payment/payRequest";
	}
	
	@PostMapping("/payResult")
	public String paymentResult(ReserveRequestDto reserveRequestDto) {
		reserveService.insertReserve(reserveRequestDto);
		
		return "payment/payResult";
//		return "redirect:../reserve/list";
	}
}
