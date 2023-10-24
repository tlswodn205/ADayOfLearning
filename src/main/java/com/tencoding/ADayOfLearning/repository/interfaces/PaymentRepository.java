package com.tencoding.ADayOfLearning.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tencoding.ADayOfLearning.repository.model.Payment;

@Mapper
public interface PaymentRepository {
	public int insert(Payment payment);
	public int updateByPersonId(Payment payment);
	public int deleteByPersonId(int paymentId);
	public Payment findByPersonId(int paymentId);
	public List<Payment> findByAll();
}