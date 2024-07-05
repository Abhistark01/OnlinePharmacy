package com.jsp.onlinepharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminNotFoundWiththisEmail extends RuntimeException {

	
	private String message;

	public AdminNotFoundWiththisEmail(String message) {
		super();
		this.message = message;
	}
	
}
