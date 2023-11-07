package com.tencoding.ADayOfLearning.dto.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BusinessLectureResponseDto {
	private int lectureSessionId; 			// 클래스 ID
	private int reserveId; 				// 예약 ID
	private String name; 					// 신청자 이름
	private String phoneNumber; 			// 신청자 전화번호
	private String email; 					// 신청자 이메일 
	private int paymentId;					// 결제 ID
	private int amount; 					// 결제 금액
	private String state; 					// 결제 상태 (결제 완료, 취소 요청, 취소 완료)
	private String refundInfo; 				// 취소 사유
	private Timestamp refundDate; 			// 취소 승인일
	private Timestamp paymentDate; 			// 결제일
}
