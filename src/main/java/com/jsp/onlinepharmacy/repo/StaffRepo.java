package com.jsp.onlinepharmacy.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.onlinepharmacy.entity.Staff;

public interface StaffRepo extends JpaRepository<Staff, Integer>{

	@Query("select s from Staff s where s.staffEmail=?1")
	Optional<Staff> findbyEmail(String staffEmail);

}
