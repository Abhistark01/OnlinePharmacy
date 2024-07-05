package com.jsp.onlinepharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StaffEmailNotFoundException extends RuntimeException {
	
	private String message;

	public StaffEmailNotFoundException(String message) {
		super();
		this.message = message;
	}
	

}
