package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.dto.request.ReserveRequestDto;
import com.tencoding.ADayOfLearning.dto.response.ReserveListResponseDto;
import com.tencoding.ADayOfLearning.repository.interfaces.ReserveRepository;
import com.tencoding.ADayOfLearning.repository.model.Reserve;

@Service
public class ReserveService {

	@Autowired
	ReserveRepository reserveRepository;
	
	public int insertReserve(ReserveRequestDto reserveRequestDto) {
		Reserve reserve = new Reserve();
		int result = reserveRepository.insert(reserve);
		
		return result;
	}
	
	public List<ReserveListResponseDto> findReserveByUserId(int userId) { 
		List<ReserveListResponseDto> reserveList = reserveRepository.findByUserId(userId);
		return reserveList;
	}
}
