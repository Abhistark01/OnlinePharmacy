package com.jsp.onlinepharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerPhoneNumberNotMatchedException extends RuntimeException {
	
	private String message;

	public CustomerPhoneNumberNotMatchedException(String message) {
		super();
		this.message = message;
	}
	
	
	

}
