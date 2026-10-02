package com.example.biblioteca.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    ProblemDetail notFound(ResourceNotFoundException ex) {
        return problem(HttpStatus.NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(BusinessRuleException.class)
    ProblemDetail business(BusinessRuleException ex){
        return problem(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    ProblemDetail unexpected(Exception ex){
        log.error("Error inesperado.",ex);
        return problem(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrio un error interno");
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
        ProblemDetail body = problem(HttpStatus.BAD_REQUEST, "Hay datos invalidos en la solicitud.");
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> errors.putIfAbsent(e.getField(), e.getDefaultMessage()));
        body.setProperty("errors", errors);
        return handleExceptionInternal(ex,body,headers,status,request);
    }

    private ProblemDetail problem(HttpStatus status, String detail){
        ProblemDetail body = ProblemDetail.forStatusAndDetail(status,detail);

        body.setTitle(status.getReasonPhrase());
        body.setType(URI.create("urn:biblioteca.error:"+status.value()));
        return body;
    }
}

