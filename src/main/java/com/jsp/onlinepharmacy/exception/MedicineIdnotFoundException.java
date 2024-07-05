package com.jsp.onlinepharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MedicineIdnotFoundException extends RuntimeException {
	
	private String message;

	public MedicineIdnotFoundException(String message) {
		super();
		this.message = message;
	}


}
