package es.practicando.apirest.tortucata.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import es.practicando.apirest.tortucata.exception.customized.BadGatewayException;
import es.practicando.apirest.tortucata.exception.customized.BadRequestException;
import es.practicando.apirest.tortucata.exception.customized.ConflictException;
import es.practicando.apirest.tortucata.exception.customized.ErrorResponse;
import es.practicando.apirest.tortucata.exception.customized.ForbiddenException;
import es.practicando.apirest.tortucata.exception.customized.NotFoundException;
import es.practicando.apirest.tortucata.exception.customized.UnauthorizedException;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)	
public class MyExcepcionHandler {
	/*
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            UnauthorizedException.class         
    })
    //@ResponseBody
    public void unauthorizedRequest() {
        //Empty. Nothing to do
    }*/

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NotFoundException.class
    })
    //@ResponseBody
    public ErrorResponse notFoundRequest(Exception exception) {
        return new ErrorResponse(exception, HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            org.springframework.dao.DuplicateKeyException.class,
            org.springframework.web.bind.support.WebExchangeBindException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class,
            org.springframework.web.server.ServerWebInputException.class
    })
    //@ResponseBody
    public ErrorResponse badRequest(Exception exception) {
        return new ErrorResponse(exception, HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({
            ConflictException.class
    })
    //@ResponseBody
    public ErrorResponse conflict(Exception exception) {
        return new ErrorResponse(exception, HttpStatus.CONFLICT.value());
    }


    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({
            ForbiddenException.class
    })
    //@ResponseBody
    public ErrorResponse forbidden(Exception exception) {
        return new ErrorResponse(exception, HttpStatus.FORBIDDEN.value());
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler({
            BadGatewayException.class
    })
    //@ResponseBody
    public ErrorResponse badGateway(Exception exception) {
        return new ErrorResponse(exception, HttpStatus.BAD_GATEWAY.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            Exception.class
    })
    //@ResponseBody
    public ErrorResponse exception(Exception exception) { // The error must be corrected
        exception.printStackTrace();
        return new ErrorResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


}
