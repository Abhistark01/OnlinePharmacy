package com.jsp.onlinepharmacy.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacy.entity.Admin;
import com.jsp.onlinepharmacy.repo.AdminRepo;

@Repository
public class Admindao {
	
	@Autowired
	private AdminRepo repo;
	
	public Admin saveAdmin(Admin admin) {
		return  repo.save(admin);
	}

	public Admin fetchAdminbyid(int id) {
		Optional<Admin> optional=repo.findById(id);
		if(optional.isPresent()) {
			return  optional.get();
		}
			return null;
	}

	public Admin updateAdminbyId(int id, Admin admin) {
		Optional<Admin> optional=repo.findById(id);
		if(optional.isPresent()) {
			admin.setAdminid(id);
			return repo.save(admin);
		}
		return null;
		
	}

	public Admin deleteAdminbyId(int id) {
		Optional<Admin> optional=  repo.findById(id);
		if(optional.isPresent()) {
			repo.deleteById(id);
			return optional.get();
		}
		return null;
	}

	public List<Admin> getallAdmin() {
	 return repo.findAll();
		
	}


	

	public Admin finaAdminbyEmail(String email) {
	Optional<Admin> optional=repo.findAdminbyEmail(email);
		if(optional.isPresent()) {
			return optional.get();
			
		}
		return null;
	}

}
