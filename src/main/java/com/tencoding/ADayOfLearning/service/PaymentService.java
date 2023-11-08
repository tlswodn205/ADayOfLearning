package com.tencoding.ADayOfLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.ADayOfLearning.dto.request.CancelRequestDto;
import com.tencoding.ADayOfLearning.dto.request.PaymentRequestDto;
import com.tencoding.ADayOfLearning.dto.response.PaymentResponseDto;
import com.tencoding.ADayOfLearning.repository.interfaces.PaymentRepository;
import com.tencoding.ADayOfLearning.repository.model.Lecture;
import com.tencoding.ADayOfLearning.repository.model.LectureSession;
import com.tencoding.ADayOfLearning.repository.model.Payment;

@Service
public class PaymentService {
	
	@Autowired
	PaymentRepository paymentRepository;
	
	
	@Transactional
	public int insertPayment(String Amt, int reserveId, String payMethod, String tid) {
		Payment payment = Payment.builder()
				.reserveId(reserveId)
				.amount(Integer.parseInt(Amt))
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
	
	@Transactional
	public void updateRefundInfoByPaymentId(CancelRequestDto cancelRequestDto) {
		Payment payment = paymentRepository.findByPaymentId(cancelRequestDto.getPaymentId());
		payment.setState("취소 요청");
		payment.setRefundInfo(cancelRequestDto.getRefundInfo());
		paymentRepository.updateRefundInfoByPaymentId(payment);
	}
	
	public Lecture getLectureBySessionId(int lectureSessionId) {
		return paymentRepository.findLectureByLectureSessionId(lectureSessionId);
	}
	
	public LectureSession getSessionbyLectureSessionId(int lectureSessionId) {
		return paymentRepository.findSessionbyLectureSessionId(lectureSessionId);
	}
	
	public String getLectureThumbnail(int lectureSessionId) {
		return paymentRepository.findLectureThumbnailbyLectureSessionId(lectureSessionId);
	}
	
}
