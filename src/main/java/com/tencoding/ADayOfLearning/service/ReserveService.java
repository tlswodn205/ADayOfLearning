package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.ADayOfLearning.dto.response.PagingResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ReserveListPageResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ReserveListResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ReserveResponseDto;
import com.tencoding.ADayOfLearning.repository.interfaces.ReserveRepository;
import com.tencoding.ADayOfLearning.repository.model.Reserve;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReserveService {

	@Autowired
	ReserveRepository reserveRepository;
	
	public int existReserveByUserId(int userId, int lectureSessionId) {
		return reserveRepository.existReserveByUserId(userId, lectureSessionId);
	}
	
	@Transactional
	public int insertReserve(int lectureSessionId, int userId) {
		Reserve reserveEntity = Reserve.builder()
				.lectureSessionId(lectureSessionId)
				.userId(userId)
				.build();
		reserveRepository.insert(reserveEntity);
		return reserveEntity.getReserveId();
		
//		현재 예약된 내역이 없을 때 예약 가능
//		int result = 0;
//		if (reserveRepository.existReserveByUserId(userId, lectureSessionId) == 0) {
//			Reserve reserveEntity = Reserve.builder()
//					.lectureSessionId(lectureSessionId)
//					.userId(userId)
//					.build();
//			reserveRepository.insert(reserveEntity);
//			result = reserveEntity.getReserveId();
//		}
//		
//		return result;
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
