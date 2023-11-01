package com.tencoding.ADayOfLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.ADayOfLearning.dto.request.CancelRequestDto;
import com.tencoding.ADayOfLearning.dto.request.PaymentRequestDto;
import com.tencoding.ADayOfLearning.dto.response.PaymentResponseDto;
import com.tencoding.ADayOfLearning.repository.interfaces.PaymentRepository;
import com.tencoding.ADayOfLearning.repository.model.Payment;

@Service
public class PaymentService {
	
	@Autowired
	PaymentRepository paymentRepository;
	
	
	@Transactional
	public int insertPayment(PaymentRequestDto paymentRequestDto, int reserveId, String payMethod, String tid) {
		Payment payment = Payment.builder()
				.reserveId(reserveId)
				.amount(Integer.parseInt(paymentRequestDto.getPrice()))
				.paymentKind(payMethod)
				.tid(tid)
				.state("결제 완료")
				.build();
		
		int result = paymentRepository.insert(payment);
		return result;
	}
	
	public PaymentResponseDto findPaymentByPaymentId(int paymentId) {
		PaymentResponseDto paymentResponseDto = paymentRepository.findAllByPaymentId(paymentId);
		return paymentResponseDto;
	}
	
	public void updatePayment(CancelRequestDto cancelRequestDto) {
		Payment payment = paymentRepository.findByPaymentId(cancelRequestDto.getPaymentId());
		payment.setState("결제 취소");
		payment.setRefundInfo(cancelRequestDto.getRefundInfo());
		paymentRepository.update(payment);
	}
	
}
