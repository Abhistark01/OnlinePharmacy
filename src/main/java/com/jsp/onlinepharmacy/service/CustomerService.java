package com.jsp.onlinepharmacy.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.AddressDao;
import com.jsp.onlinepharmacy.dao.CustomerDao;
import com.jsp.onlinepharmacy.dto.AddressDto;
import com.jsp.onlinepharmacy.dto.BookingsDto;
import com.jsp.onlinepharmacy.dto.CustomerDto;
import com.jsp.onlinepharmacy.entity.Address;
import com.jsp.onlinepharmacy.entity.Bookings;
import com.jsp.onlinepharmacy.entity.Customer;
import com.jsp.onlinepharmacy.exception.AddressIdNotFoundException;
import com.jsp.onlinepharmacy.exception.CustomerEmailNotMatchedException;
import com.jsp.onlinepharmacy.exception.CustomerIdNotFoundException;
import com.jsp.onlinepharmacy.exception.CustomerPasswordNotMatchedException;
import com.jsp.onlinepharmacy.exception.CustomerPhoneNumberNotMatchedException;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao dao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(int addressId, Customer customer) {
		
	     Address dbAddress=addressDao.fetchAddressbyId(addressId);
	     
		if(dbAddress!=null) {
			List<Address> list=new ArrayList<Address>();
			list.add(dbAddress);
			customer.setAddresses(list);
			
			Customer dbCustomer=dao.saveCustomer(customer);
			dbAddress.setCustomer(dbCustomer);
			addressDao.updateAddress(addressId, dbAddress);
		    CustomerDto dto=this.mapper.map(dbCustomer, CustomerDto.class);
		   List<AddressDto> list1=new ArrayList<AddressDto>();
		  for (Address address : dbCustomer.getAddresses()) {
			  AddressDto addressDto= this.mapper.map(address, AddressDto.class);
			  list1.add(addressDto);
		}
		  dto.setAddressDtos(list1);
//		  List<BookingsDto> bookingsDtos=new ArrayList<BookingsDto>();
//		  for (Bookings bookings : dbCustomer.getBookings()) {
//			  BookingsDto bookingsDto =this.mapper.map(bookings, BookingsDto.class);
//			  bookingsDtos.add(bookingsDto);
//			
//		}
//		  dto.setBookingsDtos(bookingsDtos);
//		   
		   
		   ResponseStructure<CustomerDto> structure =new ResponseStructure<CustomerDto>();
		   structure.setMessage("customer is signed up successfully");
		   structure.setHttpstatus(HttpStatus.CREATED.value());
		   structure.setData(dto);
		   
		   return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.CREATED);
			
		}
		else {
			throw new AddressIdNotFoundException("Sorry failed to find the address");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> findCustomer(int customerId) {
		Customer dbCustomer=dao.findCustomer(customerId);
		if(dbCustomer!=null) {
			CustomerDto dto=this.mapper.map(dbCustomer, CustomerDto.class);
			ResponseStructure<CustomerDto> structure =new ResponseStructure<CustomerDto>();
			   structure.setMessage("customer is found successfully");
			   structure.setHttpstatus(HttpStatus.FOUND.value());
			   structure.setData(dto);
			   
			   return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.FOUND);
				
		}
		else {
			throw new CustomerIdNotFoundException("sorry failed to find the customer");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomer(int customerId) {
		Customer dbCustomer=dao.deleteCusetomer(customerId);
		if(dbCustomer!=null) {
			CustomerDto dto=this.mapper.map(dbCustomer, CustomerDto.class);
			ResponseStructure<CustomerDto> structure =new ResponseStructure<CustomerDto>();
			   structure.setMessage("customer is deleted successfully");
			   structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
			   structure.setData(dto);
			   
			   return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.FORBIDDEN);
				
		}
		else {
			throw new CustomerIdNotFoundException("sorry failed to delete the customer");
		}
		
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(int customerId, Customer customer) {
		Customer dbCustomer=dao.updateCustomer(customerId,customer);
		if(dbCustomer!=null) {
			List<Address> listAddresses=dbCustomer.getAddresses();
			
			CustomerDto dto=this.mapper.map(dbCustomer, CustomerDto.class);
			List<AddressDto> list2=new ArrayList<AddressDto>();
			
			for (Address address : listAddresses) {
				AddressDto addressDto=this.mapper.map(address,AddressDto.class);
				list2.add(addressDto);
				
			}
			dto.setAddressDtos(list2);
			
			 ResponseStructure<CustomerDto> structure =new ResponseStructure<CustomerDto>();
			   structure.setMessage("customer is updated successfully");
			   structure.setHttpstatus(HttpStatus.OK.value());
			   structure.setData(dto);
			   
			   return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.OK);
		}
		else {
			throw new CustomerIdNotFoundException("sorry failed to update the customer data");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> loginCustomer(String customerEmail, String password) {
		Customer dbCustomer=dao.findByEmail(customerEmail);
		if(dbCustomer!=null) {
			if(password.equals(dbCustomer.getPassword())){
				CustomerDto dto=this.mapper.map(dbCustomer, CustomerDto.class);
				ResponseStructure<CustomerDto> structure =new ResponseStructure<CustomerDto>();
				   structure.setMessage("customer is login successfully");
				   structure.setHttpstatus(HttpStatus.FOUND.value());
				   structure.setData(dto);
				   
				   return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.FOUND);
				
			}
			else {
				throw new CustomerPasswordNotMatchedException("sorry failed to match the password");
			}
		}
		else {
			throw new CustomerEmailNotMatchedException("sorry failed to match the email ");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> resetPassword(String customerEmail, long phoneNumber,
			String newPassword) {
		Customer dbCustomer =dao.findByEmail(customerEmail);
		if(dbCustomer!=null) {
			if(phoneNumber==dbCustomer.getPhoneNumber()) {
				
				dbCustomer.setPassword(newPassword);
				CustomerDto dto=this.mapper.map(dbCustomer, CustomerDto.class);
				ResponseStructure<CustomerDto> structure =new ResponseStructure<CustomerDto>();
				   structure.setMessage("customer password changed successfully");
				   structure.setHttpstatus(HttpStatus.FOUND.value());
				   structure.setData(dto);
				   return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.FOUND);
				
			}
				
			else {
				throw new CustomerPhoneNumberNotMatchedException("sorry failed to match the phone number");
			}
			
			}
		    else {
			throw new CustomerEmailNotMatchedException("sorry failed to match the email ");
			
		   }
		
		}
	
}
