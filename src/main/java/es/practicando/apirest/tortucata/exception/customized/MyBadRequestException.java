package es.practicando.apirest.tortucata.exception.customized;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * 
 * probando un tipo de excepcion personalizada
 * HTTP 400
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public final class MyBadRequestException extends RuntimeException {

	private static final long serialVersionUID = 3827834054734122096L;

	public MyBadRequestException(String mensaje) {
        super("La solicitud no es adecuada debido a: " + mensaje);
    }
}