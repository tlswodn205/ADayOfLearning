package com.tencoding.ADayOfLearning.repository.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Business {
	private int businessId;
	private int userId;
	private String businessName;
	private String CEOname;
	private String businessAddress;
	private String businessAddressDetail;
	private String businessRegistrationImg;
	private String businessNumber;
	private String businessRegistrationNumber;
	private String state;
	private Timestamp createdAt;
}
