package com.tencoding.ADayOfLearning.dto.request;

import lombok.Data;

@Data
public class UpdateBusinessRequestDto {
	private int userId;
	private String password;
	private String businessName;
	private String CEOname;
	private String businessAddress;
	private String businessAddressDetail;
	private String businessRegistrationImg;
	private String businessNumber;
	private String businessRegistrationNumber;
}
