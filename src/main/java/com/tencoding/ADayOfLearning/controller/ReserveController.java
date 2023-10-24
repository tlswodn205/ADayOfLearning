package com.tencoding.ADayOfLearning.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tencoding.ADayOfLearning.service.ReserveService;

@Controller
@RequestMapping("/reserve")
public class ReserveController {

	@Autowired
	ReserveService reserveService;
	
	@Autowired
	HttpSession session;
}
