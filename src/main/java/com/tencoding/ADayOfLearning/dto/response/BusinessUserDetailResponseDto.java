package com.tencoding.ADayOfLearning.dto.response;


import com.tencoding.ADayOfLearning.repository.model.Business;
import com.tencoding.ADayOfLearning.repository.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusinessUserDetailResponseDto {
	private String userId;
	private boolean isKakao;
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

		if(username.indexOf("_kakao")==username.length()-6) {
			this.isKakao = true;
		}else {
			this.isKakao = false;
		}
	}
}
