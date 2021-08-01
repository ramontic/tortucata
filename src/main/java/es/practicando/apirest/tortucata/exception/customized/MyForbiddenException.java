package es.practicando.apirest.tortucata.exception.customized;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * 
 * probando un tipo de excepcion personalizada
 * HTTP 403
 *
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public final class MyForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 3827834054734122096L;

	public MyForbiddenException(String mensaje) {
        super(mensaje);
    }

}