package es.practicando.apirest.tortucata.exception;



import javax.persistence.EntityNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import es.practicando.apirest.tortucata.exception.customized.ErrorResponse2;


@ControllerAdvice //("es.practicando.apirest.tortucata.controller") // (annotations = Advised.class)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse2 errorResponse2 = new ErrorResponse2(
		        HttpStatus.UNPROCESSABLE_ENTITY.value(), 
		        "Validation error. Check 'errors' field for details."
	    );
	    
	    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
	      errorResponse2.addValidationError(fieldError.getField(), 
	          fieldError.getDefaultMessage());
	    }
	    return ResponseEntity.unprocessableEntity().body(errorResponse2);
	}

   
}
