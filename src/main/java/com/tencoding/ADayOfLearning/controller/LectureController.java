package com.tencoding.ADayOfLearning.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencoding.ADayOfLearning.dto.request.LectureRegistarionRequestDto;
import com.tencoding.ADayOfLearning.dto.request.ListSearchRequestDto;
import com.tencoding.ADayOfLearning.dto.response.LectureListItemResponseDto;
import com.tencoding.ADayOfLearning.repository.model.Lecture;
import com.tencoding.ADayOfLearning.repository.model.LecturePhoto;
import com.tencoding.ADayOfLearning.repository.model.User;
import com.tencoding.ADayOfLearning.service.LectureOptionService;
import com.tencoding.ADayOfLearning.service.LecturePhotoService;
import com.tencoding.ADayOfLearning.service.LectureService;
import com.tencoding.ADayOfLearning.util.Define;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/lecture")
public class LectureController {

	@Autowired
	LectureService lectureService;

	@Autowired
	LecturePhotoService lecturePhotoService;

	@Autowired
	LectureOptionService lectureOptionService;

	@Autowired
	HttpSession session;

	@Autowired
	ObjectMapper objectMapper;

	@GetMapping("/list")
	public String getList(Model model, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(required = false) Integer min_price, @RequestParam(required = false) Integer max_price,
			@RequestParam(required = false, defaultValue = "전체") String category,
			@RequestParam(required = false) String title,
			@RequestParam(required = false, defaultValue = "전체") String location,
			@RequestParam(required = false) String date) throws JsonProcessingException {

		if (location.equals("전체")) {
			location = null;
		}

		if (category.equals("전체")) {
			category = null;
		}

		ListSearchRequestDto listSearchRequestDto = new ListSearchRequestDto().builder().minPrice(min_price)
				.maxPrice(max_price).categoryName(category).title(title).location(location).build();

		List<LectureListItemResponseDto> list = lectureService.getLectureListBySearch(listSearchRequestDto);

		model.addAttribute("lectures", objectMapper.writeValueAsString(list));
		model.addAttribute("page", page);

		return "lecture/list";
	}

	@GetMapping("/detail")
	public String getDetail(Model model, @RequestParam Integer id) throws JsonProcessingException {
		Lecture lecture = lectureService.getLectureById(id);
		List<LecturePhoto> lecturePhotos = lecturePhotoService.getLecturePhotosById(id);

		model.addAttribute("lecture", objectMapper.writeValueAsString(lecture));
		model.addAttribute("lecturePhotos", objectMapper.writeValueAsString(lecturePhotos));

		return "lecture/detail";
	}

	@GetMapping("/registration")
	public String getRegistration() {
		// TODO : 로그인 정보가 없다면 뒤로가기 보내기

		return "lecture/registration";
	}

	@Transactional
	@PostMapping("/registration")
	public String postRegistration(LectureRegistarionRequestDto dto, @RequestParam("files") MultipartFile[] files,
			@RequestParam("file") MultipartFile thumbnail) {

		// 1. 강의 등록 부분
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		// 강의를 등록하고 등록된 id를 반환받음
		int registeredLectureId = lectureService.insertLecture(dto, user.getUserId());

		// 2. 사진 등록 부분
		lecturePhotoService.insertLecturePhoto(thumbnail, registeredLectureId);
		if (files != null) {
			lecturePhotoService.insertLecturePhotos(files, registeredLectureId);
		}

		// 3 .옵션 등록 부분
		lectureOptionService.insertLectureOption(dto, registeredLectureId);

		return "redirect:list";
	}

}
