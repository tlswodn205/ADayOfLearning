package com.tencoding.ADayOfLearning.dto.request;

import lombok.Data;

@Data
public class CancelRequestDto {
	private int paymentId;
	private String refundInfo;
}
