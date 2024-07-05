package com.jsp.onlinepharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.AddressDao;
import com.jsp.onlinepharmacy.entity.Address;
import com.jsp.onlinepharmacy.exception.AddressIdNotFoundException;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	private AddressDao dao;

	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address) {
		Address dbAddress=dao.saveAdress(address);
		ResponseStructure<Address> structure =new ResponseStructure<Address>();
		structure.setMessage("Address saved successfully");
		structure.setHttpstatus(HttpStatus.CREATED.value());
		structure.setData(dbAddress);
		return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Address>> updateAddress(int addressId, Address address) {
		Address dbAddress=dao.updateAddress(addressId,address);
		if(dbAddress!=null) {
		ResponseStructure<Address> structure =new ResponseStructure<Address>();
		structure.setMessage("Address saved successfully");
		structure.setHttpstatus(HttpStatus.OK.value());
		structure.setData(dbAddress);
		return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
	}
	else {
		throw new AddressIdNotFoundException("Sorry failed to update the Address");
	}
}

	public ResponseEntity<ResponseStructure<Address>> fetchAddressbyid(int addressId) {
		Address dbAddress=dao.fetchAddressbyId(addressId);
		if(dbAddress!=null) {
			ResponseStructure<Address> structure =new ResponseStructure<Address>();
			structure.setMessage("Address saved successfully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(dbAddress);
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.FOUND);
		}
		else {
			throw new AddressIdNotFoundException("Sorry failed to fetch the Address");
		}
	}

	public ResponseEntity<ResponseStructure<Address>> deleteAddressbyid(int addressId) {
		Address dbAddress=dao.deleteAddressbyId(addressId);
		if(dbAddress!=null) {
			ResponseStructure<Address> structure =new ResponseStructure<Address>();
			structure.setMessage("Address saved successfully");
			structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
			structure.setData(dbAddress);
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.FORBIDDEN);
		}
		else {
			throw new AddressIdNotFoundException("Sorry failed to delete the Address");
		}
	}
}
	

