package es.practicando.apirest.tortucata.exception.customized;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * 
 * probando un tipo de excepcion personalizada
 * HTTP 500
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public final class MyInternalServerErrorException extends RuntimeException {

	private static final long serialVersionUID = 3827834054734122096L;

	public MyInternalServerErrorException(String mensaje) {
        super("Error interno del servidor:" + mensaje);
    }

}