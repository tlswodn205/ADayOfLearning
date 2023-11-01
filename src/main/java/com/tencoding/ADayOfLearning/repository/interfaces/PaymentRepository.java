package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tencoding.ADayOfLearning.dto.response.PaymentResponseDto;
import com.tencoding.ADayOfLearning.repository.model.Payment;

@Mapper
public interface PaymentRepository {
	public int insert(Payment payment);
	public int updateByPaymentId(Payment payment);
	public int deleteByPaymentId(int paymentId);
	public Payment findByPaymentId(int paymentId);
	public List<Payment> findByAll();
	public PaymentResponseDto findAllByPaymentId(int paymentId);
	public int update(Payment payment);
}