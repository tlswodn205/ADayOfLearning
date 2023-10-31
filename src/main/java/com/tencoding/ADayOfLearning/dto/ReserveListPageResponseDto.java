package com.tencoding.ADayOfLearning.dto;

import java.util.List;

import com.tencoding.ADayOfLearning.dto.response.PagingResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReserveListPageResponseDto<T>{
	
	int totalPage;
	int currentPage;
	int startPageNum;
	int lastPageNum;
	boolean isFirst;
	boolean isLast;
	String keyword;
	String type;
	String status;
	List<T> list;
	
	public ReserveListPageResponseDto(PagingResponseDto pagingResponseDto, String keyword, String type, String status, List<T> list){
		this.totalPage = pagingResponseDto.getTotalPage();
		this.currentPage = pagingResponseDto.getCurrentPage();
		this.isFirst = pagingResponseDto.isFirst();
		this.isLast = pagingResponseDto.isLast();
		this.keyword = keyword;
		this.type = type;
		this.status = status;
		this.list = list;
		
		int blockCount = 10;

		int currentBlock = (this.currentPage-1) / blockCount;
		this.startPageNum = 1 + blockCount * currentBlock;
		this.lastPageNum = 10 + blockCount * currentBlock;

		if (totalPage < lastPageNum) {
			this.lastPageNum = totalPage;
		}
	}
}