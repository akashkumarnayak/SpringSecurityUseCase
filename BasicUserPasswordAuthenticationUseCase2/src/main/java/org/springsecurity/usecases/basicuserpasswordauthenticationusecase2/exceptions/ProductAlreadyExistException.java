package org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.exceptions;

public class ProductAlreadyExistException extends RuntimeException {
    public ProductAlreadyExistException(String message) {
        super(message);
    }
}
