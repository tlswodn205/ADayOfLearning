package com.tencoding.ADayOfLearning.repository.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Category {
	private int categoryId;
	private String categoryName;
	private Timestamp createdAt;
}
