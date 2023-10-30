package com.tencoding.ADayOfLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.ADayOfLearning.dto.request.PaymentRequestDto;
import com.tencoding.ADayOfLearning.repository.interfaces.PaymentRepository;
import com.tencoding.ADayOfLearning.repository.model.Payment;

@Service
public class PaymentService {
	
	@Autowired
	PaymentRepository paymentRepository;
	
	
	@Transactional
	public int insertPayment(PaymentRequestDto paymentRequestDto) {
		Payment payment = Payment.builder()
				.amount(Integer.parseInt(paymentRequestDto.getPrice()))
				.build();
		return 0;
	}
	
	
}
