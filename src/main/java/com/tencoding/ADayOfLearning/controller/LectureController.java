package com.tencoding.ADayOfLearning.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencoding.ADayOfLearning.dto.request.ListSearchRequestDto;
import com.tencoding.ADayOfLearning.dto.request.MapBoundsRequestDto;
import com.tencoding.ADayOfLearning.dto.request.ReserveDataRequestDto;
import com.tencoding.ADayOfLearning.dto.response.LectureListItemResponseDto;
import com.tencoding.ADayOfLearning.dto.response.LectureSessionResponseDto;
import com.tencoding.ADayOfLearning.dto.response.ReviewResponseDto;
import com.tencoding.ADayOfLearning.repository.model.Lecture;
import com.tencoding.ADayOfLearning.repository.model.LecturePhoto;
import com.tencoding.ADayOfLearning.service.LectureOptionService;
import com.tencoding.ADayOfLearning.service.LecturePhotoService;
import com.tencoding.ADayOfLearning.service.LectureService;
import com.tencoding.ADayOfLearning.service.LectureSessionService;
import com.tencoding.ADayOfLearning.service.ReviewService;

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
	LectureSessionService lectureSessionService;

	@Autowired
	ReviewService reviewService;
	
	@Autowired
	HttpSession session;

	@Autowired
	ObjectMapper objectMapper;

	/**
	 * /list get요청
	 * 
	 * @param Model, 리스트 페이지, 최저가, 최고가, 카테고리, 제목, 지역, 날짜
	 * @return list 페이지
	 */
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

	/**
	 * /detail get요청
	 * 
	 * @param Model, 강의id
	 * @return detail 페이지
	 */
	@GetMapping("/detail")
	public String getDetail(Model model, @RequestParam Integer id) throws JsonProcessingException {
		Lecture lecture = lectureService.getLectureById(id);
		List<LecturePhoto> lecturePhotos = lecturePhotoService.getLecturePhotosById(id);

		model.addAttribute("lecture", objectMapper.writeValueAsString(lecture));
		model.addAttribute("lecturePhotos", objectMapper.writeValueAsString(lecturePhotos));

		List<ReviewResponseDto> reviews = reviewService.getReviewsByLectureId(lecture.getLectureId());
		model.addAttribute("reviews", objectMapper.writeValueAsString(reviews));
		return "lecture/detail";
	}

	/**
	 * 세션 리스트 요청
	 * 
	 * @param ReserveDataRequestDto
	 * @return 클래스의 세션 리스트
	 */
	@PostMapping("/reserve-data")
	@ResponseBody
	public List<LectureSessionResponseDto> postReserveData(@RequestBody ReserveDataRequestDto reserveDataRequestDto) {

		return lectureSessionService.findByLectureIdAndDate(reserveDataRequestDto);
	}

	/**
	 * 지도의 위치정보로 해당 위치의 클래스 리스트 조회
	 * @param mapBoundsRequestDto
	 * @return List<LectureSessionResponseDto>
	 */
	@GetMapping("/findMap")
	public @ResponseBody List<LectureListItemResponseDto> findMap(MapBoundsRequestDto mapBoundsRequestDto) {
		return lectureService.getLectureListByMap(mapBoundsRequestDto);
	}
}
