package org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.exceptions;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException(String userDoesNotExist) {
        super(userDoesNotExist);
    }
}
