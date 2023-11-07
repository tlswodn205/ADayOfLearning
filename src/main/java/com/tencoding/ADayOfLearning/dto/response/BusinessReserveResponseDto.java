package com.tencoding.ADayOfLearning.dto.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BusinessReserveResponseDto {
	private int reserveId;
	private Timestamp sessionDate;
	private String title;
	private String name;
	private String phoneNumber;
	private String email;
	private int amount;
	private int paymentId;
	private String state;
	private Timestamp paymentDate;
	private String paymentKind;
	private Timestamp refundDate;
	private String refundInfo;
	private String tid;
}
