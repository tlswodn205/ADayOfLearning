package com.tencoding.ADayOfLearning.handler;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tencoding.ADayOfLearning.handler.exception.CustomFetchRestfulException;
import com.tencoding.ADayOfLearning.handler.exception.CustomRestfulException;
import com.tencoding.ADayOfLearning.handler.exception.UnAuthorizedException;
import com.tencoding.ADayOfLearning.handler.exception.UnMatchingException;
import com.tencoding.ADayOfLearning.handler.exception.UnSignUpException;

/**
 * 예외 발생시 (Json,Xml)
 * 데이터를 가공해서 내려 줄 수 있다.
 *
*/ 

@RestControllerAdvice // IoC 대상 + App 대상
@Order(1)
public class MyRestfullExceptionHandler {
	
//	@ExceptionHandler(Exception.class)
//	public void exception(Exception e) {
//		System.out.println("======== 예외 발생 확인 =======");
//		System.out.println(e.getMessage());
//		System.out.println("---------------------------");
//	}
	
	@ExceptionHandler(CustomRestfulException.class)
	public String basicException(CustomRestfulException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('"+e.getMessage() + "');");
		sb.append("history.back();");
		sb.append("</script>");
		return sb.toString();
	}
	
	@ExceptionHandler(CustomFetchRestfulException.class)
	public ResponseEntity<?> fetchException(CustomFetchRestfulException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
	}
	
	//로그인을 하지 않았을 경우 예외 처리
	@ExceptionHandler(UnAuthorizedException.class)
	public String notLoginException(UnAuthorizedException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('"+e.getMessage() + "');");
		sb.append("location.href='/user/signIn';");
		sb.append("</script>");
		return sb.toString();
	}
	
	@ExceptionHandler(UnSignUpException.class)
	public String notSignUpException(UnSignUpException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('"+e.getMessage() + "');");
		sb.append("location.href='/user/signUp';");
		sb.append("</script>");
		return sb.toString();
	}
	
	@ExceptionHandler(UnMatchingException.class)
	public String notMatchingException(UnMatchingException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('"+e.getMessage() + "');");
		sb.append("window.history.back();");
		sb.append("</script>");
		return sb.toString();
	}
	
}
