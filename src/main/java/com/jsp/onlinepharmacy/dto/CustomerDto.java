package com.jsp.onlinepharmacy.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class CustomerDto {
	
	private int customerId;
	private String customerName;
	
	@OneToMany
	private List<AddressDto> addressDtos;
	
	@OneToMany
	private List<BookingsDto> bookingsDtos;
	
	
	

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<AddressDto> getAddressDtos() {
		return addressDtos;
	}

	public void setAddressDtos(List<AddressDto> addressDtos) {
		this.addressDtos = addressDtos;
	}

	public List<BookingsDto> getBookingsDtos() {
		return bookingsDtos;
	}

	public void setBookingsDtos(List<BookingsDto> bookingsDtos) {
		this.bookingsDtos = bookingsDtos;
	}
	
	
	

}
