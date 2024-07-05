package com.jsp.onlinepharmacy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.onlinepharmacy.util.ResponseStructure;

@RestControllerAdvice
public class OnlinePharmacyExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> adminIdNotFoundException(AdminIdNotFoundException exception){
    	ResponseStructure<String> structure=new ResponseStructure<String>();
    	structure.setMessage("Admin id is not present");
    	structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
    	structure.setData(exception.getMessage());
    	return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
    	
    }
	@ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> adminPasswordNotFoundException(AdminPasswordNotValidException exception){
    	ResponseStructure<String> structure=new ResponseStructure<String>();
    	structure.setMessage("Admin id is not present");
    	structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
    	structure.setData(exception.getMessage());
    	return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
    	
    }
	@ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> adminEmailNotFoundException(AdminNotFoundWiththisEmail exception){
    	ResponseStructure<String> structure=new ResponseStructure<String>();
    	structure.setMessage("Admin id is not present");
    	structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
    	structure.setData(exception.getMessage());
    	return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
    	
    }
	@ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> addressIdNotFoundException(AddressIdNotFoundException exception){
    	ResponseStructure<String> structure=new ResponseStructure<String>();
    	structure.setMessage("Address id is not present");
    	structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
    	structure.setData(exception.getMessage());
    	return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
    	
     }
	@ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> medicalStoreIdNotFoundException(MedicalStoreIdNotFoundException exception){
    	ResponseStructure<String> structure=new ResponseStructure<String>();
    	structure.setMessage("medicalStore id is not present");
    	structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
    	structure.setData(exception.getMessage());
    	return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
}
	@ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> medicalIdNotFoundException(MedicineIdnotFoundException exception){
    	ResponseStructure<String> structure=new ResponseStructure<String>();
    	structure.setMessage("medicine id is not present");
    	structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
    	structure.setData(exception.getMessage());
    	return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
      }
	@ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> staffIdNotFoundException(StaffIdNotFoundException exception){
    	ResponseStructure<String> structure=new ResponseStructure<String>();
    	structure.setMessage("Staff id is not present");
    	structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
    	structure.setData(exception.getMessage());
    	return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
}
	
	@ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> staffPasswordNotMatchException(StaffPasswordNotMatchException exception){
    	ResponseStructure<String> structure=new ResponseStructure<String>();
    	structure.setMessage("Staff password is not match");
    	structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
    	structure.setData(exception.getMessage());
    	return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
}
	@ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> staffEmailNotFoundException(StaffEmailNotFoundException exception){
    	ResponseStructure<String> structure=new ResponseStructure<String>();
    	structure.setMessage("Staff email is not match");
    	structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
    	structure.setData(exception.getMessage());
    	return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
}
	@ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> customerIdNotFoundException(CustomerIdNotFoundException exception){
    	ResponseStructure<String> structure=new ResponseStructure<String>();
    	structure.setMessage("customer id is not present");
    	structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
    	structure.setData(exception.getMessage());
    	return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
     }
	
	@ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> customerEmailNotMatchException(CustomerEmailNotMatchedException exception){
    	ResponseStructure<String> structure=new ResponseStructure<String>();
    	structure.setMessage("customer email is not matched");
    	structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
    	structure.setData(exception.getMessage());
    	return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
     }
	@ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> customerPasswordNotMatchException(CustomerPasswordNotMatchedException exception){
    	ResponseStructure<String> structure=new ResponseStructure<String>();
    	structure.setMessage("customer password is not matched");
    	structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
    	structure.setData(exception.getMessage());
    	return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
     }
	@ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> customerPhoneNumberNotMatchException(CustomerPhoneNumberNotMatchedException exception){
    	ResponseStructure<String> structure=new ResponseStructure<String>();
    	structure.setMessage("customer phone number is not matched");
    	structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
    	structure.setData(exception.getMessage());
    	return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
     }
	@ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> bookingIdNotFoundException(BookingIdNotFoundException exception){
    	ResponseStructure<String> structure=new ResponseStructure<String>();
    	structure.setMessage("Booking id is not present");
    	structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
    	structure.setData(exception.getMessage());
    	return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
    	
    }
	
	@ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> bookingIsAlreadyCancelledException(BookingIsAlreadyCancelledException exception){
    	ResponseStructure<String> structure=new ResponseStructure<String>();
    	structure.setMessage("Booking is already cancelled");
    	structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
    	structure.setData(exception.getMessage());
    	return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
    	
    }
	@ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> bookingIsAlreadyDeliveredException(BookingIsAlreadyDeliveredException exception){
    	ResponseStructure<String> structure=new ResponseStructure<String>();
    	structure.setMessage("Booking is already delivered");
    	structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
    	structure.setData(exception.getMessage());
    	return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
    	
    }
	@ExceptionHandler
    public ResponseEntity<ResponseStructure<String>> bookingCantCancelledException(BookingCantCancelException exception){
    	ResponseStructure<String> structure=new ResponseStructure<String>();
    	structure.setMessage("Booking cannot be cancelled");
    	structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
    	structure.setData(exception.getMessage());
    	return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
    	
    }
}
