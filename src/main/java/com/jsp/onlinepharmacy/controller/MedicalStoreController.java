package com.jsp.onlinepharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.onlinepharmacy.dto.MedicalStoreDto;
import com.jsp.onlinepharmacy.entity.MedicalStore;
import com.jsp.onlinepharmacy.service.MedicalStoreService;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@RestController
@RequestMapping("/store")
public class MedicalStoreController {
	
	
	@Autowired
	private MedicalStoreService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> establishTheMedicalstore(@RequestParam int adminId,
	@RequestParam int addressId,@RequestBody MedicalStore medicalStore){
		return service.establishMedicalStore(adminId,addressId,medicalStore);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> updateTheMedicalstore(@RequestParam int storeId,
			@RequestBody MedicalStore medicalStore){
				return service.updateTheMedicalStore(storeId, medicalStore);
			}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> findTheMedicalstore(@RequestParam int storeId){
				return service.findTheMedicalStore(storeId);
			}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> deleteTheMedicalstore(@RequestParam int storeId){
		return service.deleteTheMedicalStore(storeId);
	}
	
	

}
