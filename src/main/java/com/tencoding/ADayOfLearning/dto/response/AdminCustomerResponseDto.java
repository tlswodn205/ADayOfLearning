package com.tencoding.ADayOfLearning.dto.response;

import java.sql.Date;

import lombok.Data;

@Data
public class AdminCustomerResponseDto {
	private String username;
	private int userId;
	private String name;
	private String address;
	private String addressDetail;
	private String email;
	private String phoneNumber;
	private Date birthday;
}
