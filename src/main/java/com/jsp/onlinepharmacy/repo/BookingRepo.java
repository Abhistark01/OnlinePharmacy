package com.jsp.onlinepharmacy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.onlinepharmacy.entity.Bookings;

public interface BookingRepo extends JpaRepository<Bookings, Integer>{

	
}
