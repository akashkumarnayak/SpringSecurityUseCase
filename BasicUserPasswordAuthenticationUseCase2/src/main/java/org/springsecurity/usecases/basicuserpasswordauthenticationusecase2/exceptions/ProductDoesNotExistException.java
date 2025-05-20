package org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.exceptions;

public class ProductDoesNotExistException extends Exception {
    public ProductDoesNotExistException(String message) {
        super(message);
    }
}
