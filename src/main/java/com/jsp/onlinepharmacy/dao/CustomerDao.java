package com.jsp.onlinepharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacy.entity.Customer;
import com.jsp.onlinepharmacy.repo.CustomerRepo;

@Repository
public class CustomerDao {

	
	@Autowired
	private CustomerRepo repo;

	public Customer saveCustomer(Customer customer) {
		
		return repo.save(customer);
	}

	public Customer findCustomer(int customerId) {
		Optional<Customer> optional=repo.findById(customerId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public Customer deleteCusetomer(int customerId) {
		Optional<Customer> optional=repo.findById(customerId);
		if(optional.isPresent()) {
			repo.deleteById(customerId);
			return optional.get();
		}
		return null;
	}

	public Customer updateCustomer(int customerId, Customer customer) {
		Optional<Customer> optional=repo.findById(customerId);
		if(optional.isPresent()) {
			Customer dbCustomer=optional.get();
			customer.setCustomerId(customerId);
			customer.setAddresses(dbCustomer.getAddresses());
			return repo.save(customer);
		}
		return null;
	}

	public Customer findByEmail(String customerEmail) {
		Optional<Customer> optional=repo.findEmail(customerEmail);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
}
