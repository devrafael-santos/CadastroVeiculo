package com.raffasdev.cadastroVeiculos.infrastructure.web.rest.exception;

import com.raffasdev.cadastroVeiculos.domain.exception.CPFAlreadyExistsException;
import com.raffasdev.cadastroVeiculos.domain.exception.CPFNotFoundException;
import com.raffasdev.cadastroVeiculos.domain.exception.PlacaAlreadyExistsException;
import com.raffasdev.cadastroVeiculos.domain.exception.PlacaNotFoundException;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response.problem.ProblemDetails;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response.problem.ValidationProblemDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {


        ProblemDetails exceptionDetails = ProblemDetails.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .title("Message Not Readable Exception: Invalid fields syntax")
                .details(exception.getCause().getMessage())
                .build();

        return this.createResponseEntity(exceptionDetails, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldError> fieldErrors = exception.getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(" | "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(" | "));

        return new ResponseEntity<>(
                ValidationProblemDetails.builder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception: Invalid Fields")
                        .details("Check the field(s) error")
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CPFAlreadyExistsException.class)
    public ResponseEntity<Object> handleCPFAlreadyExistsException(CPFAlreadyExistsException exception, WebRequest request) {
        ProblemDetails exceptionDetails = ProblemDetails.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Already Exists Exception: CPF already exists")
                .details(exception.getMessage())
                .build();

        return this.createResponseEntity(exceptionDetails, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(CPFNotFoundException.class)
    public ResponseEntity<Object> handleCPFNotFoundException(CPFNotFoundException exception, WebRequest request) {
        ProblemDetails exceptionDetails = ProblemDetails.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Not Found Exception: CPF not found")
                .details(exception.getMessage())
                .build();

        return this.createResponseEntity(exceptionDetails, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(PlacaAlreadyExistsException.class)
    public ResponseEntity<Object> handlePlacaAlreadyExistsException(PlacaAlreadyExistsException exception, WebRequest request) {
        ProblemDetails exceptionDetails = ProblemDetails.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Already Exists Exception: Placa already exists")
                .details(exception.getMessage())
                .build();

        return this.createResponseEntity(exceptionDetails, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(PlacaNotFoundException.class)
    public ResponseEntity<Object> handlePlacaNotFoundException(PlacaNotFoundException exception, WebRequest request) {
        ProblemDetails exceptionDetails = ProblemDetails.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Not Found Exception: Placa not found")
                .details(exception.getMessage())
                .build();

        return this.createResponseEntity(exceptionDetails, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
