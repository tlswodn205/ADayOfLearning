package com.tencoding.ADayOfLearning.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tencoding.ADayOfLearning.handler.exception.CustomRestfulException;
import com.tencoding.ADayOfLearning.handler.exception.UnAuthorizedException;
import com.tencoding.ADayOfLearning.repository.model.User;
import com.tencoding.ADayOfLearning.util.Define;

@Component
public class BusinessInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle() 메서드 호출");
		HttpSession session = request.getSession(); 
				        
		User principal = (User)session.getAttribute(Define.PRINCIPAL);
		if(principal == null) {
			throw new UnAuthorizedException("로그인을 해주세요.",
					HttpStatus.UNAUTHORIZED);
		}
		
		if(principal.getIdentity() =="business") {
			throw new CustomRestfulException("판매자만 접근가능합니다.",
					HttpStatus.UNAUTHORIZED);
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}
