package com.tencoding.ADayOfLearning.dto.request;

import java.sql.Date;

import com.tencoding.ADayOfLearning.repository.model.Person;
import com.tencoding.ADayOfLearning.repository.model.User;

import lombok.Data;

@Data
public class SignUpRequestDto {
	private int userId;
	private String username;
	private String password;
	private String passwordCheck;
	private String name;
	private String email;
	private String address;
	private String addressDetail;
	private String phoneNumber;
	private Date birthday;
	
	public User toUserEntity(String hashPwd) {
		return User.builder()
				.username(username)
				.password(hashPwd)
				.identity("customer")
				.build();
	}
	
	public Person toPersonEntity(int userId) {
		return Person.builder()
				.userId(userId)
				.name(name)
				.email(email)
				.address(address)
				.addressDetail(addressDetail)
				.phoneNumber(phoneNumber)
				.birthday(birthday)
				.build();
	}
}
