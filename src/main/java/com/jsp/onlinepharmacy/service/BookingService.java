package com.jsp.onlinepharmacy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.BookingDao;
import com.jsp.onlinepharmacy.dao.CustomerDao;
import com.jsp.onlinepharmacy.dao.MedicineDao;
import com.jsp.onlinepharmacy.dto.BookingsDto;
import com.jsp.onlinepharmacy.dto.CustomerDto;
//import com.jsp.onlinepharmacy.dto.MedicineDto;
import com.jsp.onlinepharmacy.entity.Bookings;
import com.jsp.onlinepharmacy.entity.Customer;
import com.jsp.onlinepharmacy.entity.Medicine;
import com.jsp.onlinepharmacy.enums.BookingStatus;
import com.jsp.onlinepharmacy.exception.BookingCantCancelException;
import com.jsp.onlinepharmacy.exception.BookingIdNotFoundException;
import com.jsp.onlinepharmacy.exception.BookingIsAlreadyCancelledException;
import com.jsp.onlinepharmacy.exception.BookingIsAlreadyDeliveredException;
import com.jsp.onlinepharmacy.exception.CustomerIdNotFoundException;
import com.jsp.onlinepharmacy.exception.MedicineIdnotFoundException;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class BookingService {
	
	@Autowired
	private BookingDao dao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private MedicineDao medicineDao;
	
	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<BookingsDto>> addBooking(int customerId, int medicineId,
			Bookings bookings) {
		LocalDate expectedDate=LocalDate.now().plusDays(7);
		Customer dbcustomer=customerDao.findCustomer(customerId);
		if(dbcustomer!=null) {
			bookings.setExpectedDate(expectedDate);
			bookings.setBookingStatus(BookingStatus.ACTIVE);
			bookings.setCustomer(dbcustomer);
//	    	customer is having a bookings
//	    	booking is also having the customer
//	    	update customer details
			
			Medicine dbMedicine=medicineDao.findMedicine(medicineId);
			List<Medicine> medicines =new ArrayList<Medicine>();
			if(dbMedicine!=null) {
				medicines.add(dbMedicine);
				bookings.setMedicines(medicines);
				Bookings dBookings=dao.saveBookings(bookings);
//	    		its time to update customer details
				List<Bookings> bookingList=dbcustomer.getBookings();
//		    	im taking the customer old bookings also
//	    		now im goiong to add new booking along with the old bookings
				bookingList.add(dBookings);
//	    		now inside this bookingss we are having old and new bookings
//	    		again im going to reset the bookings
				dbcustomer.setBookings(bookingList);//Because of the Bidirectional table
				customerDao.updateCustomer(customerId, dbcustomer);//Because of the Bidirectional table
				
				ResponseStructure<BookingsDto> structure=new ResponseStructure<BookingsDto>();
				structure.setMessage("Booking is saved succesfully");
				structure.setHttpstatus(HttpStatus.CREATED.value());
				BookingsDto bookingDto=this.mapper.map(dBookings, BookingsDto.class);
	    		bookingDto.setMedicine(medicines);
	    		bookingDto.setCustomerDto(this.mapper.map(dBookings.getCustomer(), CustomerDto.class));
	    		structure.setData(bookingDto);
				
				
				return new ResponseEntity<ResponseStructure<BookingsDto>>(structure, HttpStatus.CREATED);
				
			}
			else {
				throw new MedicineIdnotFoundException("sorry failed to add the booking");
			}
		}
		else {
			throw new CustomerIdNotFoundException("sorry failed to add the booking");
		}
		
	}

	public ResponseEntity<ResponseStructure<BookingsDto>> cancelBooking(int bookingId) {
		Bookings dbBookings=dao.findBooking(bookingId);
		if(dbBookings!=null) {
		 
			LocalDate cantCancelledDate=dbBookings.getExpectedDate().minusDays(2);
			if(dbBookings.getBookingStatus().equals(BookingStatus.CANCELLED)) {
				throw new BookingIsAlreadyCancelledException("sorry failed to cancel the booking");
			}
			else if(dbBookings.getBookingStatus().equals(BookingStatus.DELIVERED)) {
				throw new BookingIsAlreadyDeliveredException("sorry failed to cancel the booking");
				
			}
			else if(LocalDate.now().equals(cantCancelledDate) || LocalDate.now().isAfter(cantCancelledDate)) {
				throw new BookingCantCancelException("sorry failed to cancel the booking");
			}
			else {
				Bookings deleteBooking=dao.deleteBooking(bookingId);
				
			}
			ResponseStructure<BookingsDto> structure=new ResponseStructure<BookingsDto>();
			structure.setMessage("Booking is cancel successfully");
			structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
			BookingsDto bookingDto=this.mapper.map(dbBookings, BookingsDto.class);
            bookingDto.setMedicine(dbBookings.getMedicines());
    		bookingDto.setCustomerDto(this.mapper.map(dbBookings.getCustomer(), CustomerDto.class));
			structure.setData(bookingDto);
			return new ResponseEntity<ResponseStructure<BookingsDto>>(structure,HttpStatus.FORBIDDEN);
			
		}
		else {
			throw new BookingIdNotFoundException("sorry failed to cancel the booking");
		}
	
	}

}
