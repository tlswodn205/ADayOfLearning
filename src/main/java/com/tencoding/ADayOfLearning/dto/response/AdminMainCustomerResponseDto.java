package com.tencoding.ADayOfLearning.dto.response;

import lombok.Data;

@Data
public class AdminMainCustomerResponseDto{
	int userId;
	String username;
	String name;
	String email;
	String phoneNumber;
}