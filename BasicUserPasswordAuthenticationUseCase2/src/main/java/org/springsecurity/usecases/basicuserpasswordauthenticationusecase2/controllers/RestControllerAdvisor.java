package org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.dtos.ErrorResponse;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.exceptions.ProductAlreadyExistException;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.exceptions.ProductDoesNotExistException;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.exceptions.UserDoesNotExistException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestControllerAdvice
public class RestControllerAdvisor {

    @ExceptionHandler({ProductAlreadyExistException.class, ProductDoesNotExistException.class, UserDoesNotExistException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
