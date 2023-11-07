package com.tencoding.ADayOfLearning.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tencoding.ADayOfLearning.dto.request.BusinessUserRequestDto;
import com.tencoding.ADayOfLearning.dto.request.LectureRegistarionRequestDto;
import com.tencoding.ADayOfLearning.dto.request.NewChatRequestDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessLectureListResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessLectureResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessMainUserDataResponseDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessReserveResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ChatRoomResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ListPagingResponseDto;
import com.tencoding.ADayOfLearning.dto.request.BusinessUserRequestDto;
import com.tencoding.ADayOfLearning.dto.request.CancelRequestDto;
import com.tencoding.ADayOfLearning.dto.response.BusinessUserDetailResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ChatRoomResponseDto;
import com.tencoding.ADayOfLearning.repository.model.User;
import com.tencoding.ADayOfLearning.service.BusinessService;
import com.tencoding.ADayOfLearning.service.ChatRoomService;
import com.tencoding.ADayOfLearning.service.LectureOptionService;
import com.tencoding.ADayOfLearning.service.LecturePhotoService;
import com.tencoding.ADayOfLearning.service.UserService;
import com.tencoding.ADayOfLearning.util.Define;

@Controller
@RequestMapping("/business")
public class BusinessController {

	@Autowired
	BusinessService businessService;
	@Autowired
	UserService userService;
	@Autowired
	ChatRoomService chatRoomService;
	@Autowired
	LecturePhotoService lecturePhotoService;
	@Autowired
	LectureOptionService lectureOptionService;
	@Autowired
	HttpSession session;

	// main start

	@GetMapping("")
	public String getMain(Model model) {
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		BusinessMainUserDataResponseDto userData = businessService.findUserData(user.getUserId());
		model.addAttribute("userData", userData);
		// 예약 관련 메인
		int countTodayLecture = businessService.countTodayLecture(1);
		int countTodayUser = businessService.countTodayUser(1);
		model.addAttribute("countTodayLecture", countTodayLecture);
		model.addAttribute("countTodayUser", countTodayUser);
		return "/business/main";
	}
	// main end

	// chat start
	@GetMapping("/chatRoom")
	public String businessChatRoom(NewChatRequestDto newChatRequestDto, Model model) {
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		if (principal == null) {
			return "redirect:/user/signIn";
		}
		if (newChatRequestDto.getUserId() > 0) {
			// 새로운 채팅방 생성 - chatRoom, chatRoomUser 데이터 생성
			int chatRoomId = chatRoomService.insert(principal.getUserId(), newChatRequestDto.getUserId());

			// 해당 채팅방 연결을 위한 데이터 세팅
			User chatUser = userService.findByUserId(newChatRequestDto.getUserId());
			newChatRequestDto = NewChatRequestDto.builder().chatRoomId(chatRoomId).userId(newChatRequestDto.getUserId())
					.username(chatUser.getUsername()).build();
			model.addAttribute("newChat", newChatRequestDto);
		}

		List<ChatRoomResponseDto> chatRoomList = chatRoomService.findByUserId(principal.getUserId());
		model.addAttribute("chatRoomList", chatRoomList);
		return "business/chat/chatRoom";
	}
	// chat end

	// user start

	@GetMapping("/userDetail")
	public String getUserDetail(Model model) {
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		BusinessUserDetailResponseDto businessUserDetailRequestDto = businessService.findBusinessByUserID(user);
		model.addAttribute("businessUserData", businessUserDetailRequestDto);
		return "/business/user/userDetail";
	}

	@PostMapping("/businessUpdate")
	public String businessUpdate(BusinessUserRequestDto businessUserRequestDto) {
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		businessService.updateBusinessUserData(businessUserRequestDto, user.getUserId());
		return "redirect:/business/userDetail";
	}

	// user end

	// lecture start

	@GetMapping("/lectureList")
	public String getLectureList(@RequestParam(required = false) String type, @RequestParam(required = false) String keyword,@RequestParam(defaultValue = "1") Integer page, Model model) {
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		ListPagingResponseDto<BusinessLectureListResponseDto> listPagingResponseDto = businessService.findProgressLectureByUserId(type, keyword, page, user.getUserId());
		model.addAttribute("listPagingResponseDto", listPagingResponseDto);
		return "/business/lecture/list";
	}

	@GetMapping("/completedList")
	public String getCompletedLectureList(@RequestParam(required = false) String type, @RequestParam(required = false) String keyword,@RequestParam(defaultValue = "1") Integer page, Model model) {
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		ListPagingResponseDto<BusinessLectureListResponseDto> listPagingResponseDto = businessService.findCompletedLectureByUserId(type, keyword, page, user.getUserId());
		model.addAttribute("listPagingResponseDto", listPagingResponseDto);
		return "/business/lecture/list";
	}

	@GetMapping("/lectureDetail/{id}")
	public String getLectureDetail(Model model, @PathVariable Integer id) {
		List<BusinessLectureResponseDto> lecture = businessService.findByLectureSessionId(id);
		model.addAttribute("lecture", lecture);
		return "/business/lecture/detail";
	}

	@GetMapping("/registration")
	public String getRegistration() {

		return "/business/lecture/registration";
	}

	@Transactional
	@PostMapping("/registration")
	public String postRegistration(LectureRegistarionRequestDto dto, @RequestParam("files") MultipartFile[] files,
			@RequestParam("file") MultipartFile thumbnail) {

		User user = (User) session.getAttribute(Define.PRINCIPAL);

		int result = businessService.insertLecture(dto, user.getUserId(), files, thumbnail);

		return "redirect:lectureDetail/" + result;
	}

	// lecture end

	// reserve, payment start

	@GetMapping("/reserveDetail/{id}")
	public String getReserveDetail(Model model, @PathVariable Integer id) {
		BusinessReserveResponseDto reserve = businessService.findByReserveId(id);
		model.addAttribute("reserve", reserve);
		return "/business/reserve/reserveDetail";
	}

	@PostMapping("/cancelResult")
	public String cancelResult(@Param(value = "paymentId") Integer paymentId) {
		businessService.updateRefundByPaymentId(paymentId);
		return "business/reserve/cancelResult";
	}

	// reserve, payment end

}
