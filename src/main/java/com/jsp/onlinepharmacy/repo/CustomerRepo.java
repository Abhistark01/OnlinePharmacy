package com.jsp.onlinepharmacy.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.onlinepharmacy.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	@Query("select c from Customer c where c.customerEmail=?1")
	Optional<Customer> findEmail(String customerEmail);

}
