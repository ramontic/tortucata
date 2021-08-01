package es.practicando.apirest.tortucata.exception.customized;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * 
 * probando un tipo de excepcion personalizada
 * HTTP 401
 *
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public final class MyUnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 3827834054734122096L;

	public MyUnauthorizedException(String mensaje) {
        super("No tienes autorizacion: " + mensaje);
    }

}