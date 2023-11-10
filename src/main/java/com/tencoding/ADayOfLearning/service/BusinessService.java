package com.tencoding.ADayOfLearning.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tencoding.ADayOfLearning.dto.request.BusinessUserRequestDto;
import com.tencoding.ADayOfLearning.dto.request.LectureRegistarionRequestDto;
import com.tencoding.ADayOfLearning.dto.request.SessionRequestDto;
import com.tencoding.ADayOfLearning.dto.request.revisePhotoListRequestDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessLectureListResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessLectureResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessMainUserDataResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessReserveResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessUserDetailResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ListPagingResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessSalesResponseDto;
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
import com.tencoding.ADayOfLearning.repository.model.Lecture;
import com.tencoding.ADayOfLearning.repository.model.LectureOption;
import com.tencoding.ADayOfLearning.repository.model.LecturePhoto;
import com.tencoding.ADayOfLearning.repository.model.LectureSession;
import com.tencoding.ADayOfLearning.repository.model.Payment;
import com.tencoding.ADayOfLearning.repository.model.User;
import com.tencoding.ADayOfLearning.util.Define;

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
		BusinessUserDetailResponseDto businessUserDetailResponseDto = new BusinessUserDetailResponseDto(user, business);
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

	// 판매자의 진행중인 강의 리스트
	public ListPagingResponseDto<BusinessLectureListResponseDto> findProgressLectureByUserId(String type,
			String keyword, Integer page, int userId) {
		PagingResponseDto pagingResponseDto = businessRepository.findProgressLecturePaging(type, keyword, page, userId);
		int startNum = (page - 1) * 10;
		List<BusinessLectureListResponseDto> businessLectureListResponseDto = businessRepository
				.findProgressLectureByUserId(type, keyword, startNum, userId);
		ListPagingResponseDto<BusinessLectureListResponseDto> listPagingResponseDto = new ListPagingResponseDto<BusinessLectureListResponseDto>(
				pagingResponseDto, type, keyword, "", businessLectureListResponseDto);
		return listPagingResponseDto;
	}

	// 판매자의 완료된 강의 리스트
	public ListPagingResponseDto<BusinessLectureListResponseDto> findCompletedLectureByUserId(String type,
			String keyword, Integer page, int userId) {
		PagingResponseDto pagingResponseDto = businessRepository.findCompletedLecturePaging(type, keyword, page,
				userId);
		int startNum = (page - 1) * 10;
		List<BusinessLectureListResponseDto> businessLectureListResponseDto = businessRepository
				.findCompletedLectureByUserId(type, keyword, startNum, userId);
		ListPagingResponseDto<BusinessLectureListResponseDto> listPagingResponseDto = new ListPagingResponseDto<BusinessLectureListResponseDto>(
				pagingResponseDto, type, keyword, "", businessLectureListResponseDto);
		return listPagingResponseDto;
	}

	// 강의 상세보기
	public List<BusinessLectureResponseDto> findByLectureSessionId(int lectureSessionId) {
		List<BusinessLectureResponseDto> businessLectureResponseDto = businessRepository
				.findByLectureSessionId(lectureSessionId);
		return businessLectureResponseDto;
	}

	public void updateBusinessUserData(BusinessUserRequestDto businessUserRequestDto, int userId) {

		if (businessUserRequestDto.getPassword() != null) {
			userRepository.updatePasswordByUserId(userId, businessUserRequestDto.getPassword());
		}

		Business businessEntity = businessUserRequestDto.toBusinessEntity(userId);

		int result = businessRepository.updateByUserId(businessEntity);
	}

	// 학생의 예약 및 결제 상세보기
	public BusinessReserveResponseDto findByReserveId(int reserveId) {
		return businessRepository.findByReserveId(reserveId);
	}

	public void updateRefundByPaymentId(int paymentId) {
		Payment payment = paymentRepository.findByPaymentId(paymentId);
		payment.setState("취소 완료");
		paymentRepository.updateRefundByPaymentId(payment);
	}

	/**
	 * 강의 등록
	 * 
	 * @param LectureRegistarionRequestDto
	 * @return 생성된 강의의 id
	 */
	@Transactional
	public int insertLecture(LectureRegistarionRequestDto dto, int userId, MultipartFile[] files,
			MultipartFile thumbnail) {

		// 1. 강의 정보
		Lecture lecture = new Lecture().builder().categoryId(dto.getCategoryId()).userId(userId).title(dto.getTitle())
				.content(dto.getContent()).address(dto.getAddress()).addressDetail(dto.getAddressDetail())
				.maximum(dto.getMaximum()).price(dto.getPrice()).phoneNumber(dto.getPhoneNumber()).state(1)
				.latitude(dto.getLatitude()).longitude(dto.getLongitude()).duration(dto.getDuration()).build();

		lectureRepository.insert(lecture);

		int registeredLectureId = lecture.getLectureId();

		// 2. 사진
		insertLecturePhoto(thumbnail, registeredLectureId, true);

		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				insertLecturePhoto(file, registeredLectureId, false);
			}
		}

		// 3. 옵션
		insertOptionIfTrue(dto.getParkingSpaceAvailable(), 1, registeredLectureId);
		insertOptionIfTrue(dto.getRecordingProvided(), 2, registeredLectureId);
		insertOptionIfTrue(dto.getMaterialsProvided(), 3, registeredLectureId);
		insertOptionIfTrue(dto.getKidsPlayAreaAvailable(), 4, registeredLectureId);
		insertOptionIfTrue(dto.getWomenOnly(), 5, registeredLectureId);
		insertOptionIfTrue(dto.getMenOnly(), 6, registeredLectureId);
		insertOptionIfTrue(dto.getNoKidsZone(), 7, registeredLectureId);

		return registeredLectureId;
	}

	public void insertLecturePhoto(MultipartFile file, int registeredLectureId, boolean isThumbnail) {
		try {
			String fileName = uploadLocal(file, registeredLectureId);

			// sql 업로드
			LecturePhoto lecturePhoto = LecturePhoto.builder().isThumbnail(isThumbnail).lectureId(registeredLectureId)
					.img(fileName).originName(file.getOriginalFilename()).build();

			lecturePhotoRepository.insert(lecturePhoto);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String uploadLocal(MultipartFile file, Integer lectureId) throws IOException {
		byte[] bytes = file.getBytes();
		// 파일 저장 경로 설정
		String uploadDir = Define.LOCALPATH + "ADayOfLearning\\src\\main\\resources\\static\\images\\lectureImages\\";
		// sql에 올릴 경로
		String sqlPath = "/images/lectureImages/";
		// 파일 이름 : UUID 형식(128비트 길이의 고유 식별자)
		String identifier = Integer.toString(lectureId) + "_";

		String fileType = file.getContentType().substring(6);
		String fileName = identifier + UUID.nameUUIDFromBytes(file.getOriginalFilename().getBytes()) + "." + fileType;

		// 서버 로컬폴더에 업로드
		java.nio.file.Path path = Paths.get(uploadDir + fileName);
		Files.write(path, bytes);

		return sqlPath + fileName;
	}

	public void insertOptionIfTrue(Boolean condition, int optionId, int lectureId) {
		Boolean conditionIsNotNull = condition != null ? condition : false;
		if (conditionIsNotNull) {
			LectureOption option = LectureOption.builder().optionId(optionId).lectureId(lectureId).build();

			lectureOptionRepository.insert(option);
		}
	}

	public int insertSession(SessionRequestDto sessionRequestDto) throws ParseException {
		String tempDate = sessionRequestDto.getSessionDate();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

		Date date = sdf.parse(tempDate);
		Timestamp timestamp = new Timestamp(date.getTime());

		LectureSession lectureSession = LectureSession.builder().lectureId(sessionRequestDto.getLectureId())
				.sessionDate(timestamp).build();

		int result = lectureSessionRepository.insert(lectureSession);

		return result;
	}
  
	public List<BusinessSalesResponseDto> getMonthlySales(int userId) {
		List<BusinessSalesResponseDto> businessSalesResponseDto = businessRepository.monthlySales(userId);
		return businessSalesResponseDto;
	}
	
	public List<BusinessSalesResponseDto> getPastSevenDaysSales(int userId) {
		List<BusinessSalesResponseDto> businessSalesResponseDto = businessRepository.sevenDaysSales(userId);
		return businessSalesResponseDto;
	}
	
	public int getMonthlySalesTotal(int userId) {
		return businessRepository.monthlySalesTotal(userId);
	}
	
	public int getPastSevenDaysSalesTotal(int userId) {
		return businessRepository.sevenDaysSalesTotal(userId);
	}
	
	public int getLastMonthSalesTotal(int userId) {
		return businessRepository.lastMonthSalesTotal(userId);
	}

	@Transactional
	public int updateLecture(LectureRegistarionRequestDto dto, revisePhotoListRequestDto revisePhotoListRequestDto,
			Integer lectureId) {

		// 1. 강의 정보
		Lecture lecture = new Lecture().builder().lectureId(lectureId).categoryId(dto.getCategoryId()).title(dto.getTitle())
				.content(dto.getContent()).address(dto.getAddress()).addressDetail(dto.getAddressDetail())
				.maximum(dto.getMaximum()).price(dto.getPrice()).phoneNumber(dto.getPhoneNumber())
				.latitude(dto.getLatitude()).longitude(dto.getLongitude()).state(dto.getState()).duration(dto.getDuration()).build();

		lectureRepository.updateByLectureId(lecture);

		// 2. 사진 처리 로직
		revisePhotoListRequestDto.getRevisePhotoList().forEach(item -> {
			if (item.getState().equals(Define.DELETE)) {
				if (item.getLecturePhotoId() != null) {
					lecturePhotoRepository.deleteByLecturePhotoId(item.getLecturePhotoId());
				}

			} else if (item.getState().equals(Define.UPDATE)) {
				try {
					String fileName = uploadLocal(item.getFile(), lectureId);
					LecturePhoto lecturePhoto = lecturePhotoRepository.findByLecturePhotoId(item.getLecturePhotoId());
					lecturePhoto.builder().img(fileName).build();
					lecturePhotoRepository.updateByLecturePhotoId(lecturePhoto);

				} catch (IOException e) {
					e.printStackTrace();
				}

			} else if (item.getState().equals(Define.INSERT)) {
				try {
					String fileName = uploadLocal(item.getFile(), lectureId);
					LecturePhoto lecturePhoto = LecturePhoto.builder().isThumbnail(false).lectureId(lectureId)
							.img(fileName).originName(item.getFile().getOriginalFilename()).build();
					lecturePhotoRepository.insert(lecturePhoto);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		// 3. 옵션 처리 로직
		lectureOptionRepository.deleteByLectureId(lectureId);
		insertOptionIfTrue(dto.getParkingSpaceAvailable(), 1, lectureId);
		insertOptionIfTrue(dto.getRecordingProvided(), 2, lectureId);
		insertOptionIfTrue(dto.getMaterialsProvided(), 3, lectureId);
		insertOptionIfTrue(dto.getKidsPlayAreaAvailable(), 4, lectureId);
		insertOptionIfTrue(dto.getWomenOnly(), 5, lectureId);
		insertOptionIfTrue(dto.getMenOnly(), 6, lectureId);
		insertOptionIfTrue(dto.getNoKidsZone(), 7, lectureId);

		return 1;

	}

}
