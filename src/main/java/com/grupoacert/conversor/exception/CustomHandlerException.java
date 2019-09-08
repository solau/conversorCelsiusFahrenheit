package com.grupoacert.conversor.exception;

import com.grupoacert.conversor.exception.dto.ErroResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class CustomHandlerException {

    @ExceptionHandler(EscalaDesconhecidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErroResponse> handle(EscalaDesconhecidaException ex) {
        ErroResponse error = new ErroResponse("converterPara",ex.getMessage());
        return Arrays.asList(error);
    }

    @ExceptionHandler(ConversorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErroResponse> handle(ConversorException ex) {
        ErroResponse error = new ErroResponse(ex.getMessage());
        return Arrays.asList(error);
    }

}
