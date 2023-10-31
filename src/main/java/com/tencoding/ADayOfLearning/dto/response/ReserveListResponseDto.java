package com.tencoding.ADayOfLearning.dto.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReserveListResponseDto {
	private int reserveId;           	// 예약번호
	private String state;            	// 결제 상태
	private String img;              	// 클래스 이미지
	private Timestamp paymentDate;   	// 결제일
	private String title;            	// 클래스명
	private int amount;              	// 가격
	private Timestamp sessionDate;   	// 강의일
}
