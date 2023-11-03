package com.tencoding.ADayOfLearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveDataRequestDto {
	private Integer lecuteId;
	private String date;
}
