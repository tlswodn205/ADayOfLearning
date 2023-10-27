package com.tencoding.ADayOfLearning.dto.response;

import lombok.Data;

@Data
public class LectureListItemResponseDto {
	private String title;
	private String categoryName;
	private Integer price;
	private String address;
	private String username;
	private String img;
}
