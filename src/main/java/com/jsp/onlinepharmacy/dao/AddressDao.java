package com.jsp.onlinepharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacy.entity.Address;
import com.jsp.onlinepharmacy.repo.AddressRepo;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepo repo;

	public Address saveAdress(Address address) {
		return repo.save(address);
		
	}

	public Address updateAddress(int addressId, Address address) {
		Optional<Address> optional=repo.findById(addressId);
		if(optional.isPresent()) {
			address.setAddressId(addressId);
			return repo.save(address);
		}
		return null;
	}

	public Address fetchAddressbyId(int addressId) {
		Optional<Address> optional=repo.findById(addressId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public Address deleteAddressbyId(int addressId) {
		Optional<Address> optional=repo.findById(addressId);
		if(optional.isPresent()) {
			repo.deleteById(addressId);
			return optional.get();
		}
		return null;
	}
}
