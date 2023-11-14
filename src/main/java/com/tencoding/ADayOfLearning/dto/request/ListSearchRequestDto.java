package com.tencoding.ADayOfLearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListSearchRequestDto {
	private Integer minPrice;
	private Integer maxPrice;
	private String categoryName;
	private String title;
	private String location;
	private Integer startNum;
	private String date;
}
