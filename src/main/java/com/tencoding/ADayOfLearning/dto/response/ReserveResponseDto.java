package com.tencoding.ADayOfLearning.dto.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReserveResponseDto {
	private int reserveId;           	// 예약번호
	private String state;            	// 결제 상태
	private String img;              	// 클래스 이미지
	private Timestamp createdAt;      	// 예약 신청일
	private String title;            	// 클래스명
	private int amount;              	// 가격
	private Timestamp sessionDate;   	// 강의일
	private String name; 			  	// 판매자명
	private String phoneNumber;			// 판매자 전화번호
	private String address; 			// 판매자 주소
	private String addressDetail; 		// 판매자 상세주소
	private int lectureId; 			// 클래스 번호
	private int lectureSessionId; 		// 클래스 상세번호
	private String paymentKind;			// 결제수단
	private int paymentId; 			// 결제 번호
	private int userId;				// 강사의 userId
}
