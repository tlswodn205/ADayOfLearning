package com.tencoding.ADayOfLearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.ADayOfLearning.dto.request.UpdateBusinessRequestDto;
import com.tencoding.ADayOfLearning.dto.request.UpdateUserData;
import com.tencoding.ADayOfLearning.dto.response.AdminBusinessResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminCustomerResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminLectureListResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminMainBusinessResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminMainCustomerResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminMainRequestBusinessResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminMainResponseDto;
import com.tencoding.ADayOfLearning.dto.response.AdminRequestBusinessResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessSalesResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessUserDetailResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ChartResponseDto;
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
import com.tencoding.ADayOfLearning.repository.model.Business;

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

	//user start

	public ListPagingResponseDto<AdminCustomerResponseDto> findAdminCustomerList(String type, String keyword,
			Integer page) {
		PagingResponseDto pagingResponseDto = userRepository.findCustomerPaging(type, keyword, page);
		int startNum = (page-1)*10;
		List<AdminCustomerResponseDto> adminCustomerResponseDto = userRepository.findCustomerList(type, keyword, startNum);
		ListPagingResponseDto<AdminCustomerResponseDto> listPagingResponseDto = 
				new ListPagingResponseDto<AdminCustomerResponseDto>(pagingResponseDto, type, keyword, "", adminCustomerResponseDto);
		return listPagingResponseDto;
	}

	public AdminCustomerResponseDto findAdminCustomerDetail(Integer userId) {
		AdminCustomerResponseDto adminCustomerResponseDto = userRepository.findCustomerByUserId(userId);
		return adminCustomerResponseDto;
	}

	public int deleteCustomer(Integer userId) {
		int result = userRepository.deleteByUserId(userId);
		return result;
	}

	public void updateCustomer(UpdateUserData updateUserData, int userId) {
		if(updateUserData.getPassword() ==null && updateUserData.getPassword().equals(null)){
			userRepository.updatePasswordByUserId(userId, updateUserData.getPassword());
		}
		personRepository.updateByUserId(updateUserData, userId);
	}
	
	//user end
	
	
	//business start
	
	public ListPagingResponseDto<AdminRequestBusinessResponseDto> findAdminRequestBusinessList(String type,
			String keyword, Integer page) {
		PagingResponseDto pagingResponseDto = businessRepository.findRequestBusinessPaging(type, keyword, page);
		int startNum = (page-1)*10;
		List<AdminRequestBusinessResponseDto> adminRequestBusinessListResponseDto = businessRepository.findRequestBusinessList(type, keyword, startNum);
		ListPagingResponseDto<AdminRequestBusinessResponseDto> listPagingResponseDto = 
				new ListPagingResponseDto<AdminRequestBusinessResponseDto>(pagingResponseDto, type, keyword, "", adminRequestBusinessListResponseDto);
		return listPagingResponseDto;
	}

	public BusinessUserDetailResponseDto findAdminRequestBusinessDetail(Integer businessId) {
		BusinessUserDetailResponseDto businessUserDetailResponseDto = businessRepository.findRequestBusinessByBusinessId(businessId);
		return businessUserDetailResponseDto;
	}

	public ListPagingResponseDto<AdminBusinessResponseDto> findAdminBusinessList(String type, String keyword,
			Integer page) {
		PagingResponseDto pagingResponseDto = businessRepository.findBusinessPaging(type, keyword, page);
		int startNum = (page-1)*10;
		List<AdminBusinessResponseDto> adminRequestBusinessListResponseDto = businessRepository.findBusinessList(type, keyword, startNum);
		ListPagingResponseDto<AdminBusinessResponseDto> listPagingResponseDto = 
				new ListPagingResponseDto<AdminBusinessResponseDto>(pagingResponseDto, type, keyword, "", adminRequestBusinessListResponseDto);
		return listPagingResponseDto;
	}

	public BusinessUserDetailResponseDto findAdminBusinessDetail(Integer businessId) {
		BusinessUserDetailResponseDto businessUserDetailResponseDto = businessRepository.findBusinessByBusinessId(businessId);
		return businessUserDetailResponseDto;
	}

	public int agreeBusiness(Integer userId) {
		businessRepository.agreeBusiness(userId);
		userRepository.agreeBusiness(userId);
		Business business = businessRepository.findByUserId(userId);
		return business.getUserId();
	}
	
	public void disagreeBusiness(Integer userId) {
		businessRepository.disagreeBusiness(userId);
	}

	public int updateBusiness(UpdateBusinessRequestDto updateBusinessRequestDto) {
		if(updateBusinessRequestDto.getPassword() != null) {
			userRepository.updatePasswordByUserId(updateBusinessRequestDto.getUserId(), updateBusinessRequestDto.getPassword());
		}
		
		businessRepository.updateAtAdmin(updateBusinessRequestDto);
		Business business = businessRepository.findByUserId(updateBusinessRequestDto.getUserId());
		
		
		return business.getBusinessId();
	}

	public void deleteBusiness(int userId) {
		userRepository.deleteCustomerByUserId(userId);
	}

	public ListPagingResponseDto<AdminLectureListResponseDto> lectureList(String type, String keyword, Integer page) {
		PagingResponseDto pagingResponseDto = lectureRepository.findLecturePaging(type, keyword, page);
		int startNum = (page-1)*10;
		List<AdminLectureListResponseDto> adminLectureListResponseDto = lectureRepository.findLectureList(type, keyword, startNum);
		ListPagingResponseDto<AdminLectureListResponseDto> listPagingResponseDto = 
				new ListPagingResponseDto<AdminLectureListResponseDto>(pagingResponseDto, type, keyword, "", adminLectureListResponseDto);
		return listPagingResponseDto;
	}

	public List<BusinessSalesResponseDto> getMonthlySales() {
		List<BusinessSalesResponseDto> businessSalesResponseDto = reserveRepository.monthlySales();
		return businessSalesResponseDto;
	}

	public List<BusinessSalesResponseDto> getPastSevenDaysSales() {
		List<BusinessSalesResponseDto> businessSalesResponseDto = reserveRepository.sevenDaysSales();
		return businessSalesResponseDto;
	}

	public Integer getMonthlySalesTotal() {
		return reserveRepository.monthlySalesTotal();
	}
	
	public Integer getPastSevenDaysSalesTotal() {
		return reserveRepository.sevenDaysSalesTotal();
	}
	
	public Integer getLastMonthSalesTotal() {
		return reserveRepository.lastMonthSalesTotal();
	}

	public Integer getNextSevenDaysSalesTotal() {
		return reserveRepository.nextSevenDaysSalesTotal();
	}

	public List<ChartResponseDto> getCustomerChartData() {
		List<ChartResponseDto> userChartData = userRepository.customerChartData();
		return userChartData;
	}

	public List<ChartResponseDto> getBusinessChartData() {
		List<ChartResponseDto> userChartData = userRepository.businessChartData();
		return userChartData;
	}

	public List<ChartResponseDto> getRequestBusinessChartData() {
		List<ChartResponseDto> userChartData = userRepository.requestBusinessChartData();
		return userChartData;
	}
	//business end


}
