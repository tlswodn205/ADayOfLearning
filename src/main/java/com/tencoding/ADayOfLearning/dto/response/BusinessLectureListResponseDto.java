package com.tencoding.ADayOfLearning.dto.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BusinessLectureListResponseDto {
	private int lectureSessionId;
	private Timestamp sessionDate;
	private String title;
	private int price;
	private String categoryName;
	private int student;
	private int maximum;
	private String state;
	private Timestamp createdAt;
}
