package com.jsp.onlinepharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StaffPasswordNotMatchException extends RuntimeException {
	
	private String message;

	public StaffPasswordNotMatchException(String message) {
		super();
		this.message = message;
	}
	

}
