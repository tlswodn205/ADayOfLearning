package com.tencoding.ADayOfLearning.repository.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Lecture {
	private int lectureId;
	private int categoryId;
	private int userId;
	private String title;
	private String content;
	private String address;
	private String addressDetail;
	private double latitude;
	private double longitude;
	private int maximum;
	private int price;
	private String phoneNumber;
	private String state;
	private Timestamp createdAt;
}
