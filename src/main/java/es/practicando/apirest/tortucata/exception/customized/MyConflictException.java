package es.practicando.apirest.tortucata.exception.customized;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * 
 * probando un tipo de excepcion personalizada
 * HTTP 409
 *
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public final class MyConflictException extends RuntimeException {

	private static final long serialVersionUID = 3827834054734122096L;

	public MyConflictException(String mensaje) {
        super("No está permitido que " + mensaje + " sea modificado");
    }

}