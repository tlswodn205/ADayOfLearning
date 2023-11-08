package com.tencoding.ADayOfLearning.handler.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class UnMatchingException extends RuntimeException{
	private HttpStatus staus;
	
	public UnMatchingException(String message, HttpStatus httpStatus) {
		super(message);
		this.staus = httpStatus;
	}

}
