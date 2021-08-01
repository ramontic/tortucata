package es.practicando.apirest.tortucata.exception.customized;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * 
 * probando un tipo de excepcion personalizada
 * HTTP 404
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public final class MyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3827834054734122096L;

	public MyNotFoundException(Long id) {
        super("El elemento con identificador: " + id + ", no se encuentra en Base de datos");
    }
	
	public MyNotFoundException(String name) {
        super("El elemento con nombre: " + name + ", no se encuentra en Base de datos");
    }
}