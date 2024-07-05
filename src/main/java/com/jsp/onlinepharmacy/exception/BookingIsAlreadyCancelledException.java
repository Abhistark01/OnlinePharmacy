package com.jsp.onlinepharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookingIsAlreadyCancelledException extends RuntimeException {
	
	private String message;

	public BookingIsAlreadyCancelledException(String message) {
		super();
		this.message = message;
	}
	
	

}
