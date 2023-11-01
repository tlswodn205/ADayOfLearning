package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.dto.ReserveListPageResponseDto;
import com.tencoding.ADayOfLearning.dto.request.ReserveRequestDto;
import com.tencoding.ADayOfLearning.dto.response.PagingResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ReserveListResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ReserveResponseDto;
import com.tencoding.ADayOfLearning.repository.interfaces.ReserveRepository;
import com.tencoding.ADayOfLearning.repository.model.Reserve;

@Service
public class ReserveService {

	@Autowired
	ReserveRepository reserveRepository;
	
	public int insertReserve(ReserveRequestDto reserveRequestDto) {
		Reserve reserve = new Reserve();
		reserve.setUserId(1);
		reserve.setLectureSessionId(1);
		int result = reserveRepository.insert(reserve);
		
		return result;
	}
	
	public ReserveListPageResponseDto<ReserveListResponseDto> findReserveByUserId(String type, String keyword,Integer page, String status, int userId) { 
		if(page <= 0) {
			page = 1;
		}
		PagingResponseDto pagingResponseDto = reserveRepository.findPaging(type, keyword, page, status, userId);
		int startNum = (page-1)*10;
		List<ReserveListResponseDto> reserveList = reserveRepository.findByUserId(type, keyword, page, status, userId, startNum);
		
		
		System.out.println(userId);
		
		
		ReserveListPageResponseDto<ReserveListResponseDto> reserveListPageResponseDto = new ReserveListPageResponseDto<ReserveListResponseDto>(pagingResponseDto, keyword, type, status, reserveList); 
		
		return reserveListPageResponseDto;
	}
	
	public ReserveResponseDto findReserveByReserveId(int reserveId) {
		ReserveResponseDto reserve = reserveRepository.findAllByReserveId(reserveId);
		return reserve;
	}
}
