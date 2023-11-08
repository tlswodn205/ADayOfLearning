package com.tencoding.ADayOfLearning.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencoding.ADayOfLearning.dto.request.CancelRequestDto;
import com.tencoding.ADayOfLearning.dto.request.PaymentRequestDto;
import com.tencoding.ADayOfLearning.dto.response.PaymentResponseDto;
import com.tencoding.ADayOfLearning.repository.model.Lecture;
import com.tencoding.ADayOfLearning.repository.model.LectureSession;
import com.tencoding.ADayOfLearning.repository.model.Person;
import com.tencoding.ADayOfLearning.repository.model.User;
import com.tencoding.ADayOfLearning.service.PaymentService;
import com.tencoding.ADayOfLearning.service.PersonService;
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
	PersonService personService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ObjectMapper objectMapper;
	
	/**
	 * 클래스 예약 및 결제 정보
	 * @param model, lectureSessionId
	 * @return payRequest 페이지
	 */
	@GetMapping("/payRequest")
	public String paymentRequest(Model model, @RequestParam Integer lectureSessionId) throws JsonProcessingException {
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		if (principal == null) {
			return "redirect:/user/signIn";
		}
		
		Person person = personService.findPersonByUserId(principal.getUserId());
		Lecture lectureModel = paymentService.getLectureBySessionId(lectureSessionId);
		LectureSession lectureSessionModel = paymentService.getSessionbyLectureSessionId(lectureSessionId);
		PaymentRequestDto paymentRequestDto = new PaymentRequestDto(lectureModel.getTitle(),lectureModel.getPrice().toString(),person.getName(), person.getPhoneNumber(), person.getEmail());
		
		model.addAttribute("payRequest", paymentRequestDto);
		model.addAttribute("buyer", person);
		model.addAttribute("lecture", lectureModel);
		model.addAttribute("session", lectureSessionModel);
		model.addAttribute("thumbnail", paymentService.getLectureThumbnail(lectureSessionId));
		
		
		return "payment/payRequest";
	}
	
	/**
	 * 예약 및 결제 요청
	 * @param lectureSessionId, paymentRequestDto, request
	 * @return 
	 */
	@PostMapping("/payResult")
	public String paymentResult(@RequestParam Integer lectureSessionId, @RequestParam String Amt, HttpServletRequest request) {
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		if (principal == null) {
			return "redirect:/user/signIn";
		}
		String payMethod = (String)request.getParameter("PayMethod"); 	// 결제수단
		String tid = (String)request.getParameter("TxTid"); 			// 거래 ID
		log.info("payMethod 값이 뭘까 ========> {}", payMethod);
		
		int reserveId = reserveService.insertReserve(lectureSessionId, principal.getUserId());
		paymentService.insertPayment(Amt, reserveId, payMethod, tid);
		
		return "payment/payResult";
	}
	
	/**
	 * 결제 취소 폼
	 * @param model, id
	 * @return
	 */
	@GetMapping("/cancelRequest/{id}")
	public String cancelRequest(Model model, @PathVariable Integer id) {
		PaymentResponseDto payment = paymentService.findPaymentByPaymentId(id);
		model.addAttribute("payment", payment);
		return "payment/cancelRequestForm";
	}
	
	/**
	 * 결제 취소 요청 
	 * @param cancelRequestDto, reserveId
	 * @return
	 */
	@PostMapping("/cancelResult")
	public String cancelResult(CancelRequestDto cancelRequestDto, @Param(value = "reserveId") Integer reserveId) {
		paymentService.updateRefundInfoByPaymentId(cancelRequestDto);
		return "redirect:/reserve/detail/" + reserveId;
	}
	
	
}
