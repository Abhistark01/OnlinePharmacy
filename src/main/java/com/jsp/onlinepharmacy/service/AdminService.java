package com.jsp.onlinepharmacy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.Admindao;
import com.jsp.onlinepharmacy.dto.AdminDto;
import com.jsp.onlinepharmacy.entity.Admin;
import com.jsp.onlinepharmacy.exception.AdminIdNotFoundException;
import com.jsp.onlinepharmacy.exception.AdminNotFoundWiththisEmail;
import com.jsp.onlinepharmacy.exception.AdminPasswordNotValidException;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class AdminService {
	
//	it will take the data from the controller then it will pass that to the dao
//	it will receive the data returned by the dao
//	it will add some extra things to the Response 
//	it will return the ResponseStructure of Admin

	@Autowired
	private Admindao admindao;
	
	@Autowired
	private AdminDto admindto;

	public ResponseEntity<ResponseStructure<AdminDto>> signupAdmin(Admin admin) {
		 Admin dbAdmin =admindao.saveAdmin(admin);
//			dbADMIN IS THE SAVED ADMIN
		 admindto.setAdminId(dbAdmin.getAdminid());
		 admindto.setAdminName(dbAdmin.getAdminName());
		 admindto.setAdminAddress(dbAdmin.getAdminAddress());
		 
		 ResponseStructure<AdminDto> structure=new ResponseStructure();
		 structure.setMessage("Admin signedup Successfully");
		 structure.setHttpstatus(HttpStatus.CREATED.value());
		 structure.setData(admindto);
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<AdminDto>> fetchAdminbyid(int id)  {
		 Admin dbadmin= admindao.fetchAdminbyid(id);
		if(dbadmin!=null) {
			//id is present
			admindto.setAdminId(dbadmin.getAdminid());
			 admindto.setAdminName(dbadmin.getAdminName());
			 admindto.setAdminAddress(dbadmin.getAdminAddress());
			 
			ResponseStructure<AdminDto> structure=new ResponseStructure();
			 structure.setMessage("Admin data fetched Successfully");
			 structure.setHttpstatus(HttpStatus.FOUND.value());
			 structure.setData(admindto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FOUND);
			
		}
		else {
//			id is not present	
			throw new AdminIdNotFoundException("Sorry failed to fetch the data");
		}
	}

	public ResponseEntity<ResponseStructure<AdminDto>> updateAdminbyId(int id, Admin admin) {
		Admin dbAdmin=   admindao.updateAdminbyId(id,admin);
		if(dbAdmin!=null) {
			admindto.setAdminId(dbAdmin.getAdminid());
			 admindto.setAdminName(dbAdmin.getAdminName());
			 admindto.setAdminAddress(dbAdmin.getAdminAddress());
			 
//				id is present and the data updated successfully
			ResponseStructure<AdminDto> structure =new ResponseStructure<AdminDto>();
			structure.setMessage("Data updated succesfully");
			structure.setHttpstatus(HttpStatus.OK.value());
			structure.setData(admindto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.OK);
			
		}
		else {
//			id is not present
//			raise an exception
			throw new AdminIdNotFoundException("sorry failed to update the data");
		}
	}

	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdminbyId(int id) {
		
		Admin dbAdmin=   admindao.deleteAdminbyId(id);
		if(dbAdmin!=null) {
			admindto.setAdminId(dbAdmin.getAdminid());
			 admindto.setAdminName(dbAdmin.getAdminName());
			 admindto.setAdminAddress(dbAdmin.getAdminAddress());
			ResponseStructure<AdminDto> structure =new ResponseStructure<AdminDto>();
			structure.setMessage("Admin data is deleted successfully");
			structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
			structure.setData(admindto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.FORBIDDEN);
		}
		else {
			throw new AdminIdNotFoundException("sorry failed to delete the data");
		}
		
	}

	public ResponseEntity<ResponseStructure<List<AdminDto>>> fetchAllAdmin() {
		List<Admin> list = admindao.getallAdmin();
		List<AdminDto> adminDtos=new ArrayList<>();
		for (Admin admin : list) {
			AdminDto dto =new AdminDto();
		    dto.setAdminId(admin.getAdminid());
			dto.setAdminName(admin.getAdminName());
			dto.setAdminAddress(admin.getAdminAddress());
			adminDtos.add(dto);
			
		}
		ResponseStructure<List<AdminDto>> structure =new ResponseStructure<>();
		structure.setMessage("Admin data is fetched successfully");
		structure.setHttpstatus(HttpStatus.FOUND.value());
		structure.setData(adminDtos);
		return new ResponseEntity<ResponseStructure<List<AdminDto>>>(structure, HttpStatus.FOUND);
		
	}

	public ResponseEntity<ResponseStructure<AdminDto>> loginAdmin(String email, String password) {
		Admin dbAdmin= admindao.finaAdminbyEmail(email);
		if(dbAdmin!=null) {
			//admin is present then i can check with the password
			if(password.equals(dbAdmin.getAdminPassword())) {
//				pasword also matches then login success
				admindto.setAdminId(dbAdmin.getAdminid());
				 admindto.setAdminName(dbAdmin.getAdminName());
				 admindto.setAdminAddress(dbAdmin.getAdminAddress());
				 
				ResponseStructure<AdminDto> structure=new ResponseStructure<>();
				 structure.setMessage("Admin data fetched Successfully");
				 structure.setHttpstatus(HttpStatus.FOUND.value());
				 structure.setData(admindto);
				return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FOUND);
			}
			else {
//				login failure invalid password
				throw new AdminPasswordNotValidException("sorry faild to login");
			}
			
		}
		else {
//			admin is not present with this email
//			invalid Email	
			throw new AdminNotFoundWiththisEmail("sorry failed to login");
		}
		
		
	}
	
	
	
	  
	
}
