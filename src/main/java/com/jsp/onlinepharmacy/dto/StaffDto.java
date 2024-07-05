package com.jsp.onlinepharmacy.dto;

import org.springframework.stereotype.Component;

import com.jsp.onlinepharmacy.dto.AdminDto;
import com.jsp.onlinepharmacy.dto.MedicalStoreDto;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class StaffDto {
	
	private int staffId;
	private String staffName;
	
	
	@ManyToOne
	private AdminDto admindto;
	
	@OneToOne
	private MedicalStoreDto medicalStoredto;

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public AdminDto getAdmindto() {
		return admindto;
	}

	public void setAdmindto(AdminDto admindto) {
		this.admindto = admindto;
	}

	public MedicalStoreDto getMedicalStoredto() {
		return medicalStoredto;
	}

	public void setMedicalStoredto(MedicalStoreDto medicalStoredto) {
		this.medicalStoredto = medicalStoredto;
	}
	

}
