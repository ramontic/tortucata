package es.practicando.apirest.tortucata.exception;
	
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import es.practicando.apirest.tortucata.exception.customized.MyConflictException;
import es.practicando.apirest.tortucata.exception.customized.MyNotFoundException;


@ControllerAdvice
public class MyExcepcionHandler extends ResponseEntityExceptionHandler{
	
    // 400
    @ExceptionHandler({ ConstraintViolationException.class }) 
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
    	logger.info(ex.getClass().getName());
    	//
    	List<String> errors = new ArrayList<String>();
    	for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " + 
              violation.getPropertyPath() + ": " + violation.getMessage());
        }

        MensajeError mensajeError = 
          new MensajeError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        
        return new ResponseEntity<Object>(mensajeError, new HttpHeaders(), mensajeError.getStatus());        
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
    	logger.info(ex.getClass().getName());
    	//
    	
    	final String error = " " + ex.getClass();
    	MensajeError mensajeError = 
    	          new MensajeError(HttpStatus.BAD_REQUEST, 
    	        		  "El elemento que deseas incluir, ya está contenido en Base de datos. " 
    	        		  + ex.getRootCause().getMessage(), error);

        return new ResponseEntity<Object>(mensajeError, new HttpHeaders(), mensajeError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, 
    							final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    	logger.info(ex.getClass().getName());
    	//
    
    	final String error = " " + ex.getClass();
    	MensajeError mensajeError = 
    	          new MensajeError(HttpStatus.BAD_REQUEST, "El siguiente token es un tipo de dato distinto del esperado: " 
    	        		  + ex.getRootCause().getMessage()
    	        		  , error);
   
        return new ResponseEntity<Object>(mensajeError, headers, mensajeError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, 
    							final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    	logger.info(ex.getClass().getName());
    	//
    
		final List<String> errores = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errores.add(error.getField() + ": " + error.getDefaultMessage());
		}

		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errores.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		final MensajeError mensajeError = new MensajeError(HttpStatus.BAD_REQUEST,
				"Los siguientes campos tienen errores de validación: ", errores);
		
		return handleExceptionInternal(ex, mensajeError, headers, mensajeError.getStatus(), request);
	}

    // 404
    @ExceptionHandler(value = { EntityNotFoundException.class, MyNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
    	logger.info(ex.getClass().getName());
    	//
    	
    	final String error = " " + ex.getClass();
    	MensajeError mensajeError = 
    	          new MensajeError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
    	 
        return new ResponseEntity<Object>(mensajeError, new HttpHeaders(), mensajeError.getStatus());
    }
    
   
    // 405
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex, 
    										final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    	logger.info(ex.getClass().getName());
    	//
    	final String error = " " + ex.getClass();
    	MensajeError mensajeError = 
    	          new MensajeError(HttpStatus.METHOD_NOT_ALLOWED, "Este método no está permitido, "
    	          		+ "estás usando un método http de forma inadecuada", error);
    
    	 return new ResponseEntity<Object>(mensajeError, new HttpHeaders(), mensajeError.getStatus());
    }

    
    // 409
    @ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class, MyConflictException.class })
    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
    	logger.info(ex.getClass().getName());
    	//
    	final String error = " " + ex.getClass();
    	MensajeError mensajeError = 
    	          new MensajeError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), error);
    	
        return new ResponseEntity<Object>(mensajeError, new HttpHeaders(), mensajeError.getStatus());
    }

    // 412

    // 500
    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
    	logger.info(ex.getClass().getName());
    	//
    	
    	final String error = " " + ex.getClass();
    	MensajeError mensajeError = 
    	          new MensajeError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), error);
       
        return new ResponseEntity<Object>(mensajeError, new HttpHeaders(), mensajeError.getStatus());
    }
    
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
    	logger.info(ex.getClass().getName());
    	//
        MensajeError mensajeError = new MensajeError(
          HttpStatus.INTERNAL_SERVER_ERROR, "Ay lo kapasao" + ex.getLocalizedMessage(), ex.getClass().toString());
        return new ResponseEntity<Object>(mensajeError, new HttpHeaders(), mensajeError.getStatus());
    }


}
