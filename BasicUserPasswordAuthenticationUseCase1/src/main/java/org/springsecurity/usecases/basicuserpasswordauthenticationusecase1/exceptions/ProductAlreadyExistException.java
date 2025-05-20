package org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.exceptions;

public class ProductAlreadyExistException extends RuntimeException {
    public ProductAlreadyExistException(String message) {
        super(message);
    }
}
