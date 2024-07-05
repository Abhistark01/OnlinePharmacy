package com.jsp.onlinepharmacy.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.onlinepharmacy.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{

	@Query("select a from Admin a where a.adminEmail=?1")
	Optional<Admin> findAdminbyEmail(String email);
	

}
