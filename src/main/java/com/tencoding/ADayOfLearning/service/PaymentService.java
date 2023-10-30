package com.tencoding.ADayOfLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.dto.request.PaymentRequestDto;
import com.tencoding.ADayOfLearning.repository.interfaces.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	PaymentRepository paymentRepository;
	
	
	public int insertPayment(PaymentRequestDto paymentRequestDto) {
		
		return 0;
	}
	
}
