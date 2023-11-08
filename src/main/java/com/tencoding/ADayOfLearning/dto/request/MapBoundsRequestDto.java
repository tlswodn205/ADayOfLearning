package com.tencoding.ADayOfLearning.dto.request;

import lombok.Data;

@Data
public class MapBoundsRequestDto {
	// 최소 위도
	private double qa;
	// 최대 위도
	private double pa;
	// 최소 경도
	private double ha;
	// 최대 경도
	private double oa;
}
