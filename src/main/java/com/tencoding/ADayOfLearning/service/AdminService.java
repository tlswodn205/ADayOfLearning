package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.dto.response.AdminMainBusinessResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminMainCustomerResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminMainRequestBusinessResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminMainResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminRequestBusinessResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ListPagingResponseDto;
import com.tencoding.ADayOfLearning.dto.response.PagingResponseDto;
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

@Service
public class AdminService {
	
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

	public AdminMainResponseDto getMainData() {
		List<AdminMainCustomerResponseDto> amAdminMainCustomerResponseDtos = userRepository.findCustomer();
		List<AdminMainBusinessResponseDto> adminMainBusinessResponseDtos = userRepository.findBusiness();
		List<AdminMainRequestBusinessResponseDto> adminMainRequestBusinessResponseDtos = businessRepository.findRequestBusiness();
		
		AdminMainResponseDto adminMainResponseDto = AdminMainResponseDto.builder().adminMainCustomerListResponseDto(amAdminMainCustomerResponseDtos)
																					.adminMainBusinessListResponseDto(adminMainBusinessResponseDtos)
																					.adminMainRequestBusinessListResponseDto(adminMainRequestBusinessResponseDtos)
																					.build();
		return adminMainResponseDto;
	}

	public ListPagingResponseDto<AdminRequestBusinessResponseDto> findAdminRequestBusinessList(String type,
			String keyword, Integer page) {
		PagingResponseDto pagingResponseDto = businessRepository.findPaging(type, keyword, page);
		int startNum = (page-1)*10;
		List<AdminRequestBusinessResponseDto> adminRequestBusinessListResponseDto = businessRepository.findRequestBusinessAtdetail(type, keyword, startNum);
		ListPagingResponseDto<AdminRequestBusinessResponseDto> listPagingResponseDto = 
				new ListPagingResponseDto<AdminRequestBusinessResponseDto>(pagingResponseDto, type, keyword, "", adminRequestBusinessListResponseDto);
		return listPagingResponseDto;
	}
}
