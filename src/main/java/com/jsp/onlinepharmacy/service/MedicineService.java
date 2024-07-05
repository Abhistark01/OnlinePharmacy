package com.jsp.onlinepharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.MedicalStoreDao;
import com.jsp.onlinepharmacy.dao.MedicineDao;
import com.jsp.onlinepharmacy.entity.MedicalStore;
import com.jsp.onlinepharmacy.entity.Medicine;
import com.jsp.onlinepharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.onlinepharmacy.exception.MedicineIdnotFoundException;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class MedicineService {

	@Autowired
	private MedicineDao dao;
	
	@Autowired
	private MedicalStoreDao storeDao;

	public ResponseEntity<ResponseStructure<Medicine>> addMedicineToStore(int storeId, Medicine medicine) {
		
		MedicalStore dbmedicalStore=storeDao.findMedicalStore(storeId);
		if(dbmedicalStore!=null) {
			medicine.setMedicalStore(dbmedicalStore);
			Medicine dbmedicine=dao.saveMedicines(medicine);
			ResponseStructure<Medicine> structure=new ResponseStructure<Medicine>();
			structure.setMessage("data is saved succesfully");
			structure.setHttpstatus(HttpStatus.CREATED.value());
			structure.setData(dbmedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure, HttpStatus.CREATED);
		}
		else {
			throw new MedicalStoreIdNotFoundException("sorry failed to save the medical store");
		}
	    
	}

	public ResponseEntity<ResponseStructure<Medicine>> updateMedicineTostore(int medicineId, Medicine medicine) {
		Medicine dbMedicine=dao.updateMedicine(medicineId,medicine);
		if(dbMedicine!=null) {
				ResponseStructure<Medicine> structure=new ResponseStructure<Medicine>();
				structure.setMessage("medicine is updated successfully");
				structure.setHttpstatus(HttpStatus.OK.value());
				structure.setData(dbMedicine);
				return new ResponseEntity<ResponseStructure<Medicine>>(structure, HttpStatus.OK);
		}
		else {
			throw new MedicineIdnotFoundException("sorry failed to update the medicine");
		}
		
	}

	public ResponseEntity<ResponseStructure<Medicine>> findMedicineToStore(int medicineId) {
		Medicine dbMedicine=dao.findMedicine(medicineId);
		if(dbMedicine!=null) {
			ResponseStructure<Medicine> structure=new ResponseStructure<Medicine>();
			structure.setMessage("medicine is found successfully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure, HttpStatus.FOUND);
		}
		else {
			throw new MedicineIdnotFoundException("sorry failed to find the medicine");
		}
	}

	public ResponseEntity<ResponseStructure<Medicine>> deleteMedicineToStore(int medicineId) {
		Medicine dbMedicine=dao.deleteMedicine(medicineId);
		if(dbMedicine!=null) {
			ResponseStructure<Medicine> structure=new ResponseStructure<Medicine>();
			structure.setMessage("medicine is deleted successfully");
			structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure, HttpStatus.FORBIDDEN);
		}
		else {
			throw new MedicineIdnotFoundException("sorry failed to delete the medicine");
		}
		
	}

	public ResponseEntity<ResponseStructure<Medicine>> findMedicinebyName(String medicineName) {
        Medicine dbMedicine=dao.findMedicinebyName(medicineName);
        if(dbMedicine!=null) {
			ResponseStructure<Medicine> structure=new ResponseStructure<Medicine>();
			structure.setMessage("medicine is updated successfully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure, HttpStatus.FOUND);
		}
		else {
			throw new MedicineIdnotFoundException("sorry failed to fetch the medicine");
		}
	
	}
}
