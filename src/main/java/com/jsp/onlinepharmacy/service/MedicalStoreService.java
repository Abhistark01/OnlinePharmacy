package com.jsp.onlinepharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.MedicalStoreDao;
import com.jsp.onlinepharmacy.dto.AddressDto;
import com.jsp.onlinepharmacy.dto.AdminDto;
import com.jsp.onlinepharmacy.dto.MedicalStoreDto;
import com.jsp.onlinepharmacy.entity.MedicalStore;
import com.jsp.onlinepharmacy.exception.AddressIdNotFoundException;
import com.jsp.onlinepharmacy.exception.AdminIdNotFoundException;
import com.jsp.onlinepharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.onlinepharmacy.dao.Admindao;
import com.jsp.onlinepharmacy.dao.AddressDao;
import com.jsp.onlinepharmacy.entity.Address;
import com.jsp.onlinepharmacy.entity.Admin;
import com.jsp.onlinepharmacy.util.ResponseStructure;


@Service
public class MedicalStoreService {
	
	@Autowired
	private MedicalStoreDao dao;
	
	@Autowired
	private Admindao admindao;
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> establishMedicalStore(int adminId, int addressId, MedicalStore medicalStore) {
		Admin dbadmin=admindao.fetchAdminbyid(adminId);
		if(dbadmin!=null) {
			//id is present
			Address dbAddress=addressDao.fetchAddressbyId(addressId);
			if(dbAddress!=null) {
				//address id is present
				medicalStore.setAdmin(dbadmin);
				medicalStore.setAddress(dbAddress);
				MedicalStore dbMedicalStore=dao.saveMedicalStore(medicalStore);
				MedicalStoreDto medicalStoreDto=this.mapper.map(dbMedicalStore, MedicalStoreDto.class);
				AddressDto addressDto=this.mapper.map(dbMedicalStore.getAddress(), AddressDto.class);
				AdminDto adminDto=this.mapper.map(dbMedicalStore.getAdmin(), AdminDto.class);
				
				medicalStoreDto.setAddressDto(addressDto);
				medicalStoreDto.setAdminDto(adminDto);
				ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<MedicalStoreDto>();
				structure.setMessage("medical store is established succesfully");
				structure.setHttpstatus(HttpStatus.CREATED.value());
				structure.setData(medicalStoreDto);
				return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure, HttpStatus.CREATED);
			}
			else {
				throw new AddressIdNotFoundException("sorry failed to establish the store");
			}
			
		}else {
			throw new AdminIdNotFoundException("sorry failed to establish the store");
		}
		
		
	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> updateTheMedicalStore(int storeId,
			MedicalStore medicalStore) {
		MedicalStore dbMedicalStore=dao.updateMedicalStore(storeId,medicalStore);
		if(dbMedicalStore!=null) {
			MedicalStoreDto medicalStoreDto=this.mapper.map(dbMedicalStore, MedicalStoreDto.class);
			AddressDto addressDto=this.mapper.map(dbMedicalStore.getAddress(), AddressDto.class);
			AdminDto adminDto=this.mapper.map(dbMedicalStore.getAdmin(), AdminDto.class);
			
			medicalStoreDto.setAddressDto(addressDto);
			medicalStoreDto.setAdminDto(adminDto);
			
			ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("Medical store is updated succesfully");
			structure.setHttpstatus(HttpStatus.OK.value());
			structure.setData(medicalStoreDto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure, HttpStatus.OK);
		}
		else {
			throw new MedicalStoreIdNotFoundException("sorry failed to update the store");
		}
		
	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> findTheMedicalStore(int storeId) {
		MedicalStore dbMedicalStore=dao.findMedicalStore(storeId);
		if(dbMedicalStore!=null) {
			MedicalStoreDto medicalStoreDto=this.mapper.map(dbMedicalStore, MedicalStoreDto.class);
			AddressDto addressDto=this.mapper.map(dbMedicalStore.getAddress(), AddressDto.class);
			AdminDto adminDto=this.mapper.map(dbMedicalStore.getAdmin(),AdminDto.class);
			
			medicalStoreDto.setAddressDto(addressDto);
			medicalStoreDto.setAdminDto(adminDto);
			ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("Medical store is fetched successfully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(medicalStoreDto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure, HttpStatus.FOUND);
		}
		else {
			throw new MedicalStoreIdNotFoundException("sorry failed to fetch the store");
		}
	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> deleteTheMedicalStore(int storeId) {
		MedicalStore dbMedicalStore=dao.deleteMedicalStore(storeId);
		if(dbMedicalStore!=null) {
			MedicalStoreDto medicalStoreDto =this.mapper.map(dbMedicalStore, MedicalStoreDto.class);
			AddressDto addressDto=this.mapper.map(dbMedicalStore.getAddress(),AddressDto.class);
			AdminDto adminDto=this.mapper.map(dbMedicalStore.getAdmin(), AdminDto.class);
			medicalStoreDto.setAddressDto(addressDto);
			medicalStoreDto.setAdminDto(adminDto);
			ResponseStructure<MedicalStoreDto> structure =new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("Medical store is deleted successfully");
			structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
			structure.setData(medicalStoreDto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure, HttpStatus.FORBIDDEN);
			
			
		}
		else {
			throw new MedicalStoreIdNotFoundException("sorry failed to delete the store");
		}
	}

}
