package com.jsp.onlinepharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AdminIdNotFoundException extends RuntimeException {
	
	private String message;

	public AdminIdNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
	
	
	

}
