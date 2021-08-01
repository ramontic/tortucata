package es.practicando.apirest.tortucata.exception.customized;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * 
 * probando un tipo de excepcion personalizada
 * HTTP 503
 *
 */
@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public final class MyServiceUnavailableException extends RuntimeException {

	private static final long serialVersionUID = 3827834054734122096L;

	public MyServiceUnavailableException(String mensaje) {
        super("No está disponible el servicio: " + mensaje);
    }

}