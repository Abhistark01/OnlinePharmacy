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

import com.jsp.onlinepharmacy.entity.Medicine;
import com.jsp.onlinepharmacy.service.MedicineService;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@RestController
@RequestMapping("/medicine")
public class MedicineController {
	
   @Autowired
   private MedicineService service;
   
   @PostMapping
   public ResponseEntity<ResponseStructure<Medicine>> addMedicinetothemedicalStore(@RequestParam int storeId, 
		   @RequestBody Medicine medicine){
	   return service.addMedicineToStore(storeId,medicine);
	   
   }
   
  @PutMapping
  public ResponseEntity<ResponseStructure<Medicine>> updateMedicinetotheMedicalStore(@RequestParam int medicineId,
		   @RequestBody Medicine medicine ){
	  return service.updateMedicineTostore(medicineId,medicine);
  }
  @GetMapping
  public ResponseEntity<ResponseStructure<Medicine>> findMedicineTotheMedicalStore(@RequestParam int medicineId){
	  return service.findMedicineToStore(medicineId);
	  
  }
  
  @DeleteMapping
  public ResponseEntity<ResponseStructure<Medicine>> deleteMedicineTotheMedicalStore(@RequestParam int medicineId){
	  return service.deleteMedicineToStore(medicineId);
	  
  }
  @GetMapping("/findbyname")
  public ResponseEntity<ResponseStructure<Medicine>> findMedicinebyNameToMedicalStore(@RequestParam String MedicineName){
	  return service.findMedicinebyName(MedicineName);
	  
  }
  

}
