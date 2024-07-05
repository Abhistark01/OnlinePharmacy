package com.jsp.onlinepharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.Admindao;
import com.jsp.onlinepharmacy.dao.MedicalStoreDao;
import com.jsp.onlinepharmacy.dao.StaffDao;
import com.jsp.onlinepharmacy.dto.AdminDto;
import com.jsp.onlinepharmacy.dto.MedicalStoreDto;
import com.jsp.onlinepharmacy.dto.StaffDto;
import com.jsp.onlinepharmacy.entity.Admin;
import com.jsp.onlinepharmacy.entity.MedicalStore;
import com.jsp.onlinepharmacy.entity.Staff;
import com.jsp.onlinepharmacy.exception.AdminIdNotFoundException;
import com.jsp.onlinepharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.onlinepharmacy.exception.StaffEmailNotFoundException;
import com.jsp.onlinepharmacy.exception.StaffIdNotFoundException;
import com.jsp.onlinepharmacy.exception.StaffPasswordNotMatchException;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class StaffService {
	
	@Autowired
	private StaffDao dao;
	
	@Autowired
	private Admindao admindao;
	
	@Autowired
	private MedicalStoreDao storeDao;
	
	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<StaffDto>> signUpStaff(int adminid, int storeId, Staff staff) {
		Admin dbAdmin=admindao.fetchAdminbyid(adminid);
		if(dbAdmin!=null) {
			MedicalStore dbMedicalStore=storeDao.findMedicalStore(storeId);
			if(dbMedicalStore!=null) {
				
				
				staff.setAdmin(dbAdmin);
				staff.setMedicalStore(dbMedicalStore);
				Staff dbStaff=dao.saveStaff(staff);
				StaffDto dto=this.mapper.map(dbStaff, StaffDto.class);
				AdminDto adminDto =this.mapper.map(dbStaff.getAdmin(), AdminDto.class);
				MedicalStoreDto storedto=this.mapper.map(dbStaff.getMedicalStore(),MedicalStoreDto.class);
				
				dto.setAdmindto(adminDto);
				dto.setMedicalStoredto(storedto);
				
				ResponseStructure<StaffDto> structure=new ResponseStructure<StaffDto>();
				structure.setMessage("staff data is saved successfully");
                structure.setHttpstatus(HttpStatus.CREATED.value());
                structure.setData(dto);
                return new ResponseEntity<ResponseStructure<StaffDto>>(structure, HttpStatus.CREATED);
		
			}
			else {
				throw new MedicalStoreIdNotFoundException("sorry failed to find the medicalStore");
			}
			
		}
		else {
			throw new AdminIdNotFoundException("sorry failed to find the admin");
		}
		
	}

	public ResponseEntity<ResponseStructure<StaffDto>> updateStaff(int staffId, Staff staff) {
		Staff dbStaff=dao.updateStaff(staffId,staff);
		if(dbStaff!=null) {
			StaffDto dto=this.mapper.map(dbStaff, StaffDto.class);
			AdminDto adminDto =this.mapper.map(dbStaff.getAdmin(), AdminDto.class);
			MedicalStoreDto storedto=this.mapper.map(dbStaff.getMedicalStore(),MedicalStoreDto.class);
			
			dto.setAdmindto(adminDto);
			dto.setMedicalStoredto(storedto);
			
			ResponseStructure<StaffDto> structure=new ResponseStructure<StaffDto>();
			structure.setMessage("staff data is updated successfully");
            structure.setHttpstatus(HttpStatus.OK.value());
            structure.setData(dto);
            return new ResponseEntity<ResponseStructure<StaffDto>>(structure, HttpStatus.OK);
	
		}else {
			throw new StaffIdNotFoundException("sorry failed to update the staff data");
		}
	}

	public ResponseEntity<ResponseStructure<StaffDto>> findStaff(int staffId) {
		Staff dbStaff=dao.findStaffbyid(staffId);
		if(dbStaff!=null) {
			StaffDto dto=this.mapper.map(dbStaff, StaffDto.class);
			AdminDto adminDto =this.mapper.map(dbStaff.getAdmin(), AdminDto.class);
			MedicalStoreDto storedto=this.mapper.map(dbStaff.getMedicalStore(),MedicalStoreDto.class);
			
			dto.setAdmindto(adminDto);
			dto.setMedicalStoredto(storedto);
			
			ResponseStructure<StaffDto> structure=new ResponseStructure<StaffDto>();
			structure.setMessage("staff data is saved successfully");
            structure.setHttpstatus(HttpStatus.FOUND.value());
            structure.setData(dto);
            return new ResponseEntity<ResponseStructure<StaffDto>>(structure, HttpStatus.FOUND);
	
		}else {
			throw new StaffIdNotFoundException("sorry failed to find the staff data");
		}
		
	}

	public ResponseEntity<ResponseStructure<StaffDto>> deleteStaff(int staffId) {
		Staff dbStaff=dao.deleteStaff(staffId);
		if(dbStaff!=null) {
			StaffDto dto=this.mapper.map(dbStaff, StaffDto.class);
			AdminDto adminDto =this.mapper.map(dbStaff.getAdmin(), AdminDto.class);
			MedicalStoreDto storedto=this.mapper.map(dbStaff.getMedicalStore(),MedicalStoreDto.class);
			
			dto.setAdmindto(adminDto);
			dto.setMedicalStoredto(storedto);
			
			ResponseStructure<StaffDto> structure=new ResponseStructure<StaffDto>();
			structure.setMessage("staff data is saved successfully");
            structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
            structure.setData(dto);
            return new ResponseEntity<ResponseStructure<StaffDto>>(structure, HttpStatus.FORBIDDEN);
	
		}else {
			throw new StaffIdNotFoundException("sorry failed to delete the staff data");
		}
		
	}

	public ResponseEntity<ResponseStructure<StaffDto>> loginStaff(String staffEmail, String staffPassword) {
		Staff dbStaff= dao.finaAdminbyEmail(staffEmail);
		if(dbStaff!=null) {
			if(staffPassword.equals(dbStaff.getStaffPassword())) {
				StaffDto dto=this.mapper.map(dbStaff, StaffDto.class);
				ResponseStructure<StaffDto> structure=new ResponseStructure<>();
				 structure.setMessage("Staff data fetched Successfully");
				 structure.setHttpstatus(HttpStatus.FOUND.value());
				 structure.setData(dto);
				return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.FOUND);
				
			}
			else {
				throw new StaffPasswordNotMatchException("sorry failed to match password of the staff");
			}
		}
		else {
			throw new StaffEmailNotFoundException("sorry failed to found this email in staff data");
		}
	}

}
