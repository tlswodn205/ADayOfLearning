package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.dto.response.BusinessLectureListResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessLectureResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessMainUserDataResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessUserDetailResponseDto;
import com.tencoding.ADayOfLearning.repository.interfaces.BusinessRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.CategoryRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.ChatRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.ChatRoomRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.ChatRoomUserRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.LectureOptionRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.LecturePhotoRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.LectureRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.LectureSessionRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.OptionRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.PaymentRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.PersonRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.ReserveRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.ReviewRepository;
import com.tencoding.ADayOfLearning.repository.interfaces.UserRepository;
import com.tencoding.ADayOfLearning.repository.model.Business;
import com.tencoding.ADayOfLearning.repository.model.User;

@Service
public class BusinessService {
	
	@Autowired
	BusinessRepository businessRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ChatRepository chatRepository;
	
	@Autowired
	ChatRoomRepository chatRoomRepository;
	
	@Autowired
	ChatRoomUserRepository chatRoomUserRepository;
	
	@Autowired
	LectureRepository lectureRepository;
	
	@Autowired
	LectureOptionRepository lectureOptionRepository;
	
	@Autowired
	LecturePhotoRepository lecturePhotoRepository;
	
	@Autowired
	LectureSessionRepository lectureSessionRepository;
	
	@Autowired
	OptionRepository optionRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	ReserveRepository reserveRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	UserRepository userRepository;

	public BusinessMainUserDataResponseDto findUserData(int userId) {
		return userRepository.findUserDataByUserId(userId);
	}

	public BusinessUserDetailResponseDto findBusinessByUserID(User user) {
		Business business = businessRepository.findByUserId(user.getUserId());
		BusinessUserDetailResponseDto businessUserDetailResponseDto = new BusinessUserDetailResponseDto(user,business);
		return businessUserDetailResponseDto;
	}

	// 판매자의 오늘 강의 수
	public int countTodayLecture(int userId) {
		return businessRepository.countTodayLecture(userId);
	}
	
	// 판매자의 오늘 학생 수
	public int countTodayUser(int userId) {
		return businessRepository.countTodayUser(userId);
	}
	
	// 판매자의 강의 리스트
	public List<BusinessLectureListResponseDto> findLectureByUserId(int userId) {
		List<BusinessLectureListResponseDto> businessLectureListResponseDto = businessRepository.findLectureByUserId(userId);
		return businessLectureListResponseDto;
	}
	
	// 강의 상세보기
	public List<BusinessLectureResponseDto> findByLectureSessionId(int lectureSessionId) {
		List<BusinessLectureResponseDto> businessLectureResponseDto = businessRepository.findByLectureSessionId(lectureSessionId); 
		return businessLectureResponseDto;
	}
}
