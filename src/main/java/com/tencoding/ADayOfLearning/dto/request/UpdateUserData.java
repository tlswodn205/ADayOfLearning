package com.tencoding.ADayOfLearning.dto.request;

import java.sql.Date;

import com.tencoding.ADayOfLearning.repository.model.Person;
import com.tencoding.ADayOfLearning.repository.model.User;

import lombok.Data;

@Data
public class UpdateUserData {
	private String password;
	private String passwordCheck;
	private String name;
	private String email;
	private String address;
	private String addressDetail;
	private String phoneNumber;
	private Date birthday;
}
