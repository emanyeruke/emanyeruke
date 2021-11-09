package com.cap10mycap10.zuulgateway.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionController extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        return super.handleAsyncRequestTimeoutException(ex, headers, status, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError =
                new ApiError(LocalDateTime.now().toString(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";

        ApiError apiError =
                new ApiError(LocalDateTime.now().toString(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    /*@ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        ApiError apiError =
                new ApiError(LocalDateTime.now().toString(),HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }*/

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error =
                ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ApiError apiError =
                new ApiError(LocalDateTime.now().toString(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> handleError(HttpServletRequest request, EntityNotFoundException exception) {


        String error =
                exception.getMessage();

        ApiError apiError =
                new ApiError(LocalDateTime.now().toString(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), error);
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(value = BusinessValidationException.class)
    public ResponseEntity<Object> handleError(HttpServletRequest request, BusinessValidationException exception) {
        String error =
                exception.getMessage();

        ApiError apiError =
                new ApiError(LocalDateTime.now().toString(), HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN, exception.getLocalizedMessage(), error);
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    /*@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleError(HttpServletRequest request, DataIntegrityViolationException exception) {
        String error =
                exception.getRootCause().getMessage();

        ApiError apiError =
                new ApiError(LocalDateTime.now().toString(), HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN, exception.getLocalizedMessage(), error);
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleError(HttpServletRequest request, DataAccessException exception) {
        String error =
                exception.getMessage();

        ApiError apiError =
                new ApiError(LocalDateTime.now().toString(), HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN, exception.getLocalizedMessage(), error);
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }*/


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleError(HttpServletRequest request, Exception exception) {

        String error =
                exception.getMessage();

        ApiError apiError =
                new ApiError(LocalDateTime.now().toString(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), error);
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

}
