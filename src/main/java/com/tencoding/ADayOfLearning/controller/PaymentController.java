package com.tencoding.ADayOfLearning.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tencoding.ADayOfLearning.dto.request.CancelRequestDto;
import com.tencoding.ADayOfLearning.dto.request.PaymentRequestDto;
import com.tencoding.ADayOfLearning.dto.request.ReserveRequestDto;
import com.tencoding.ADayOfLearning.dto.response.PaymentResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ReserveResponseDto;
import com.tencoding.ADayOfLearning.repository.model.User;
import com.tencoding.ADayOfLearning.service.PaymentService;
import com.tencoding.ADayOfLearning.service.ReserveService;
import com.tencoding.ADayOfLearning.util.Define;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		// TODO - 로그인한 사용자 정보 불러오기
		// 1. 로그인 정보가 없으면 로그인 페이지로 이동 
		// 2. PaymentRequestDto에 구매자 정보 변경 필요
//		User principal = (User) session.getAttribute(Define.PRINCIPAL);
//		if (principal == null) {
//			return "redirect:/user/signIn";
//		}
		
		PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
		model.addAttribute("payRequest", paymentRequestDto);
		
		return "payment/payRequest";
	}
	
	@PostMapping("/payResult")
	public String paymentResult(ReserveRequestDto reserveRequestDto, HttpServletRequest request) {
		String payMethod = (String)request.getParameter("PayMethod"); 	// 결제수단
		String tid = (String)request.getParameter("TxTid"); 			// 거래 ID
		
		int reserveId = reserveService.insertReserve(reserveRequestDto);
		System.out.println("reserveId값 =====> " + reserveId);
		PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
		paymentService.insertPayment(paymentRequestDto, reserveId, payMethod, tid);
		
		return "payment/payResult";
	}
	
	@GetMapping("/cancelRequest/{id}")
	public String cancelRequest(Model model, @PathVariable Integer id) {
		PaymentResponseDto payment = paymentService.findPaymentByPaymentId(id);
		model.addAttribute("payment", payment);
		return "payment/cancelRequest";
	}
	
	@PostMapping("/cancelResult")
	public String cancelResult(CancelRequestDto cancelRequestDto) {
		log.info("cancelRequestDto 받아온 값 {}", cancelRequestDto);
		
		paymentService.updatePayment(cancelRequestDto);
		return "payment/cancelResult";
	}
	
}
