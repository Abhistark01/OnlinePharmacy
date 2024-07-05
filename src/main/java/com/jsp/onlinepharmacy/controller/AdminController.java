package com.jsp.onlinepharmacy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.onlinepharmacy.dao.Admindao;
import com.jsp.onlinepharmacy.entity.Admin;
import com.jsp.onlinepharmacy.dto.AdminDto;
import com.jsp.onlinepharmacy.service.AdminService;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<AdminDto>>  signupAdmin(@RequestBody Admin admin) {
		
		 return service.signupAdmin(admin);
	}
	@GetMapping("/find")
	public ResponseEntity<ResponseStructure<AdminDto>> fetchAdminbyid(@RequestParam int adminId){
		return service.fetchAdminbyid(adminId);
	}
	@PutMapping("/update")
	public  ResponseEntity<ResponseStructure<AdminDto>> updateAdminbyId(@RequestParam int adminId, @RequestBody Admin admin){
		return service.updateAdminbyId(adminId,admin);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdminbyId(@RequestParam int adminId){
		return service.deleteAdminbyId(adminId);
	}
	@GetMapping("/findall")
	public ResponseEntity<ResponseStructure<List<AdminDto>>> findallAdmins(){
		return service.fetchAllAdmin();
	}
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<AdminDto>> loginAdmin(@RequestParam String email, @RequestParam String password){
		return service.loginAdmin(email,password);
	}

}
