package com.tencoding.ADayOfLearning.dto.request;

import com.tencoding.ADayOfLearning.repository.model.Business;

import lombok.Data;

@Data
public class BusinessUserRequestDto {
	private String password;
	private String businessName;
	private String CEOname;
	private String businessAddress;
	private String businessAddressDetail;
	private String businessNumber;
	
	public Business toBusinessEntity(int userId) {
		return Business.builder()
				.businessName(businessName)
				.CEOname(CEOname)
				.businessAddress(businessAddress)
				.businessAddressDetail(businessAddressDetail)
				.businessNumber(businessNumber)
				.userId(userId)
				.build();
	}
}
