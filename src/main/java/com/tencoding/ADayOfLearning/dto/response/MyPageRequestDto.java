package com.tencoding.ADayOfLearning.dto.response;

import java.sql.Date;

import com.tencoding.ADayOfLearning.repository.model.Person;
import com.tencoding.ADayOfLearning.repository.model.User;

import lombok.Data;

@Data
public class MyPageRequestDto {
	private String username;
	private boolean isKakao;
	private String name;
	private String email;
	private String address;
	private String addressDetail;
	private String phoneNumber;
	private Date birthday;
	
	public MyPageRequestDto(User user, Person person) {
		this.username = user.getUsername();
		this.name = person.getName();
		this.email = person.getEmail();
		this.address = person.getAddress();
		this.addressDetail = person.getAddressDetail();
		this.phoneNumber = person.getPhoneNumber();
		this.birthday = person.getBirthday();
		if(username.indexOf("_kakao")==username.length()-6) {
			this.isKakao = true;
		}else {
			this.isKakao = false;
		}
		
	}
}
