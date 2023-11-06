package com.tencoding.ADayOfLearning.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPagingResponseDto<T>{

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
	
	public ListPagingResponseDto(PagingResponseDto pagingResponseDto, String type, String keyword, String status, List<T> list){
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