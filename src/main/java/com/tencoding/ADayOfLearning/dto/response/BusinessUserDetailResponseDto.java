package com.tencoding.ADayOfLearning.dto.response;


import com.tencoding.ADayOfLearning.repository.model.Business;
import com.tencoding.ADayOfLearning.repository.model.User;

import lombok.Data;

@Data
public class BusinessUserDetailResponseDto {
	private String username;
	private String businessName;
	private String CEOname;
	private String businessAddress;
	private String businessAddressDetail;
	private String businessRegistrationImg;
	private String businessNumber;
	private String businessRegistrationNumber;
	private String state;
	
	public BusinessUserDetailResponseDto(User user, Business business){
		this.username = user.getUsername();
		this.businessName = business.getBusinessName();
		this.CEOname = business.getCEOname();
		this.businessAddress = business.getBusinessAddress();
		this.businessAddressDetail = business.getBusinessAddressDetail();
		this.businessRegistrationImg = business.getBusinessRegistrationImg();
		this.businessNumber = business.getBusinessNumber();
		this.businessRegistrationNumber = business.getBusinessRegistrationNumber();
		this.state = business.getState();
	}
}
