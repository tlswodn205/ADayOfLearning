package com.tencoding.ADayOfLearning.repository.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Payment {
	private int paymentId;
	private int reserveId;
	private int amount;
	private String paymentKind;
	private int tid;
	private Timestamp paymentDate;
	private String state;
	private Timestamp refundDate;
	private String refundInfo;
	private Timestamp createdAt;
}

