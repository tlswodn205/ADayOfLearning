package com.tencoding.ADayOfLearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureResponseDto {
	private String title;
	private String address;
	private String addressDetail;
	private Integer price;
	private String phoneNumber;
	private String Content;

}
