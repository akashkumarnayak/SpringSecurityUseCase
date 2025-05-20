package org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.exceptions;

public class ProductDoesNotExistException extends Exception {
    public ProductDoesNotExistException(String message) {
        super(message);
    }
}
