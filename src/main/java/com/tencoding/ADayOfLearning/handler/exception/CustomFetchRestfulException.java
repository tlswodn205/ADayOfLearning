package com.tencoding.ADayOfLearning.handler.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomFetchRestfulException extends RuntimeException {
	private HttpStatus staus;
	
	public CustomFetchRestfulException(String message, HttpStatus httpStatus) {
		super(message);
		this.staus = httpStatus;
	}
}
