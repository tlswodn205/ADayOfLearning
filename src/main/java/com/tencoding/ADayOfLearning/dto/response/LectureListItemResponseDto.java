package com.tencoding.ADayOfLearning.dto.response;

import lombok.Data;

@Data
public class LectureListItemResponseDto {
	private Integer lectureId;
	private String title;
	private String categoryName;
	private Integer price;
	private String address;
	private Integer reviewCount;
	private double reviewScore;
	private String businessName;
	private String img;
}
