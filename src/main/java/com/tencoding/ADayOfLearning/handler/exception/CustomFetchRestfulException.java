package com.tencoding.ADayOfLearning.handler.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomFetchRestfulException extends RuntimeException {
	private HttpStatus status;
	
	public CustomFetchRestfulException(String message, HttpStatus httpStatus) {
		super(message);
		this.status = httpStatus;
	}
}
