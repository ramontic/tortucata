package es.practicando.apirest.tortucata.exception.customized;

public class ForbiddenException extends RuntimeException {
    private static final String DESCRIPTION = "Forbidden Exception";

    public ForbiddenException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}