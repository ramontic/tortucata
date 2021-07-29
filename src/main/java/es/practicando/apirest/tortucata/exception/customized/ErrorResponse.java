package es.practicando.apirest.tortucata.exception.customized;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorResponse {

    private final String error;
    private final String message;
    private final Integer code;

    public ErrorResponse(Exception exception, Integer code) {
        this.error = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.code = code;
    }
    
}