package com.jsp.onlinepharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookingCantCancelException extends RuntimeException {
	
	private String message;

	public BookingCantCancelException(String message) {
		super();
		this.message = message;
	}
	
	

}
