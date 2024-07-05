package com.jsp.onlinepharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookingIsAlreadyDeliveredException extends  RuntimeException {
	
	private String message;

	public BookingIsAlreadyDeliveredException(String message) {
		super();
		this.message = message;
	}
	
	

}
