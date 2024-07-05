package com.jsp.onlinepharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class CustomerEmailNotMatchedException extends RuntimeException {
	
	
	private String message;

	public CustomerEmailNotMatchedException(String message) {
		super();
		this.message = message;
	}

}