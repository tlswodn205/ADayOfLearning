package com.tencoding.ADayOfLearning.repository.model;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
	private int personId;
	private int userId;
	private String name;
	private String address;
	private String addressDetail;
	private String email;
	private String phoneNumber;
	private Date birthday;
	private Timestamp createdAt;
}
