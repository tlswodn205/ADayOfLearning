package com.tencoding.ADayOfLearning.dto.request;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class revisePhotoRequestDto implements Serializable {
	private MultipartFile file;
	private Integer lecturePhotoId;
	private String state;
}
