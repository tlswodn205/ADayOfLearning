package com.tencoding.ADayOfLearning.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminMainResponseDto {
	List<AdminMainCustomerResponseDto> adminMainCustomerListResponseDto;
	List<AdminMainBusinessResponseDto> adminMainBusinessListResponseDto;
	List<AdminMainRequestBusinessResponseDto> adminMainRequestBusinessListResponseDto;
}
