package com.tencoding.ADayOfLearning.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencoding.ADayOfLearning.dto.request.ListSearchRequestDto;
import com.tencoding.ADayOfLearning.dto.response.LectureListItemResponseDto;
import com.tencoding.ADayOfLearning.repository.model.Lecture;
import com.tencoding.ADayOfLearning.repository.model.LecturePhoto;
import com.tencoding.ADayOfLearning.service.LecturePhotoService;
import com.tencoding.ADayOfLearning.service.LectureService;

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
	HttpSession session;

	@Autowired
	ObjectMapper objectMapper;
	
	@GetMapping("/list")
	public String list(Model model, @RequestParam(defaultValue = "1") Integer page,
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
	public String detail(Model model, @RequestParam Integer id) throws JsonProcessingException {
		Lecture lecture = lectureService.getLectureById(id);
		List<LecturePhoto> lecturePhotos = lecturePhotoService.getLecturePhotosById(id);

		model.addAttribute("lecture", objectMapper.writeValueAsString(lecture));
		model.addAttribute("lecturePhotos", objectMapper.writeValueAsString(lecturePhotos));
		
		return "lecture/detail";
	}

}
