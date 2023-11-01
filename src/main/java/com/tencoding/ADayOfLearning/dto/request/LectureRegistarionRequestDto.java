package com.tencoding.ADayOfLearning.dto.request;

import lombok.Data;

@Data
public class LectureRegistarionRequestDto {
	private String title;
	private String content;
	private Integer categoryId;
	private String address;
	private String addressDetail;
	private Integer maximum;
	private Integer price;
	private String phoneNumber;
	private Double latitude;
	private Double longitude;
	private Boolean parkingSpaceAvailable;
	private Boolean recordingProvided;
	private Boolean materialsProvided;
	private Boolean kidsPlayAreaAvailable;
	private Boolean womenOnly;
	private Boolean menOnly;
	private Boolean noKidsZone;
}