package com.tencoding.ADayOfLearning.dto.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class PaymentResponseDto {
	private int paymentId;
	private int amount;
	private String tid;
	private Timestamp createdAt;
	private String state;
	private int reserveId;
	private Timestamp sessionDate;
	private String title;
	private String img;
	private String name;
}


