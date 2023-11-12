package com.tencoding.ADayOfLearning.dto.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AdminLectureListResponseDto {
	private int lectureId;
	private String title;
	private int userId;
	private String businessName;
	private String address;
	private String addressDetail;
	private String price;
	private String categoryName;
	private String state;
	private Timestamp createdAt;
}
