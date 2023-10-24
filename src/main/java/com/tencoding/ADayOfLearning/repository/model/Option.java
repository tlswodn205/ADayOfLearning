package com.tencoding.ADayOfLearning.repository.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Option {
	private int optionId;
	private String optionName;
	private Timestamp createdAt;
}
