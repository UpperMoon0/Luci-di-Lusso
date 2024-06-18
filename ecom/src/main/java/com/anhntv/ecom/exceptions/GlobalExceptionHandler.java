package com.anhntv.ecom.exceptions;

import com.anhntv.ecom.constants.BusinessExceptionCode;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handleException(LockedException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BusinessExceptionCode.ACCOUNT_LOCKED.getCode())
                                .businessExceptionDescription(BusinessExceptionCode.ACCOUNT_LOCKED.getDescription())
                                .error(e.getMessage())
                                .build()
                );

    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(AccountNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BusinessExceptionCode.ACCOUNT_NOT_FOUND.getCode())
                                .businessExceptionDescription(BusinessExceptionCode.ACCOUNT_NOT_FOUND.getDescription())
                                .error(e.getMessage())
                                .build()
                );

    }

    @ExceptionHandler(InvalidCsrfTokenException.class)
    public ResponseEntity<ExceptionResponse> handleException(InvalidCsrfTokenException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BusinessExceptionCode.INVALID_TOKEN.getCode())
                                .businessExceptionDescription(BusinessExceptionCode.INVALID_TOKEN.getDescription())
                                .error(e.getMessage())
                                .build()
                );

    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ExceptionResponse> handleException(ExpiredJwtException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BusinessExceptionCode.TOKEN_EXPIRED.getCode())
                                .businessExceptionDescription(BusinessExceptionCode.TOKEN_EXPIRED.getDescription())
                                .error(e.getMessage())
                                .build()
                );

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BusinessExceptionCode.BAD_CREDENTIALS.getCode())
                                .businessExceptionDescription(BusinessExceptionCode.BAD_CREDENTIALS.getDescription())
                                .error(e.getMessage())
                                .build()
                );

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException e) {
        Set<String> errors = new HashSet<>();
        e.getBindingResult().getAllErrors()
                            .forEach(error -> {
                                var errorMessage = error.getDefaultMessage();
                                errors.add(errorMessage);
                            });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .validationErrors(errors)
                                .build()
                );

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {

        e.getStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .businessExceptionDescription("Internal server error. Contact to admin")
                                .error(e.getMessage())
                                .build()
                );

    }




}
