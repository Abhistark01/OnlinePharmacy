package com.jsp.onlinepharmacy.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

public class MedicineDto {
	
	private int medicineId;
	private String MedicineName;
	private double cost;
	private LocalDate expiryDate;
	private int stockquantity;
	private String manufacture;
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private MedicalStoreDto medicalStoreDto;

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return MedicineName;
	}

	public void setMedicineName(String medicineName) {
		MedicineName = medicineName;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getStockquantity() {
		return stockquantity;
	}

	public void setStockquantity(int stockquantity) {
		this.stockquantity = stockquantity;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MedicalStoreDto getMedicalStoreDto() {
		return medicalStoreDto;
	}

	public void setMedicalStoreDto(MedicalStoreDto medicalStoreDto) {
		this.medicalStoreDto = medicalStoreDto;
	}
    
    

}
