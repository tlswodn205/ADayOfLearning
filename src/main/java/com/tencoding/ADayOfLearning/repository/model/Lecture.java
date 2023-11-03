package com.tencoding.ADayOfLearning.repository.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lecture {
	private Integer lectureId;
	private Integer categoryId;
	private Integer userId;
	private String title;
	private String content;
	private String address;
	private String addressDetail;
	private Double latitude;
	private Double longitude;
	private Integer maximum;
	private Integer price;
	private String phoneNumber;
	private Integer state;
	private Timestamp createdAt;
	private Integer duration;
}
