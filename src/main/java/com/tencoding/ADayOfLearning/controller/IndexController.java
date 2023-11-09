package com.tencoding.ADayOfLearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencoding.ADayOfLearning.dto.response.LastLectureResponseDto;
import com.tencoding.ADayOfLearning.dto.response.LectureListItemResponseDto;
import com.tencoding.ADayOfLearning.service.LectureService;

@Controller
public class IndexController {

	@Autowired
	LectureService lectureService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@GetMapping({"", "index"})
	public String index(Model model) throws JsonProcessingException {
		List<LectureListItemResponseDto> newLectureList = lectureService.getNewLecture();
		
		model.addAttribute("newLectures", objectMapper.writeValueAsString(newLectureList));
		return "index";
	}
	
	@GetMapping("/map")
	public String map(Model model) throws JsonProcessingException {
		LastLectureResponseDto lastLecture = lectureService.getLastLecture();
		System.out.println(lastLecture);
		model.addAttribute("lastLecture", objectMapper.writeValueAsString(lastLecture));
		return "map";
	}
}
