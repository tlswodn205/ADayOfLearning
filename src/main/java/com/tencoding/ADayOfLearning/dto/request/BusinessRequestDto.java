package com.tencoding.ADayOfLearning.dto.request;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import com.tencoding.ADayOfLearning.repository.model.Business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessRequestDto {
	private String businessName;
	private String CEOname;
	private String businessAddress;
	private String businessAddressDetail;
	private String businessNumber;
	private String businessRegistrationNumber;
	private MultipartFile businessRegistration;
	
	public Business toEntity(int userId, String businessRegistrationImg) {
		
		return Business.builder()
				.userId(userId)
				.businessName(this.businessName)
				.CEOname(this.CEOname)
				.businessAddress(this.businessAddress)
				.businessAddressDetail(this.businessAddressDetail)
				.businessRegistrationImg(businessRegistrationImg)
				.businessNumber(this.businessNumber)
				.businessRegistrationNumber(this.businessRegistrationNumber)
				.state("등록요청")
				.build();
	}
}
