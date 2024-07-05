package com.jsp.onlinepharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MedicalStoreIdNotFoundException extends RuntimeException {
	
	private String message;

	public MedicalStoreIdNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
	

}
