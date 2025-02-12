package com.jsp.onlinepharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminPasswordNotValidException extends RuntimeException {
	
	private String message;

	public AdminPasswordNotValidException(String message) {
		super();
		this.message = message;
	}
	

}
