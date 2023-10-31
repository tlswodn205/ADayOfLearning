package com.tencoding.ADayOfLearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PagingResponseDto {
	private int totalPage;
	private int currentPage;
	private boolean isFirst;
	private boolean isLast;
}
