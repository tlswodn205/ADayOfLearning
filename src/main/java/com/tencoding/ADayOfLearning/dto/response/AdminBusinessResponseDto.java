package com.tencoding.ADayOfLearning.dto.response;

import lombok.Data;

@Data
public class AdminBusinessResponseDto {
	private String username;
	private int businessId;
	private int userId;
	private String businessName;
	private String CEOname;
	private String businessAddress;
	private String businessAddressDetail;
	private String businessNumber;
	private String businessRegistrationNumber;
	private String state;
}
