package com.tencoding.ADayOfLearning.repository.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class User {
	private int userId;
	private String username;
	private String password;
	private String identity;
	private Timestamp createdAt;
}
