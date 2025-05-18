package org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.exceptions;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException(String userDoesNotExist) {
        super(userDoesNotExist);
    }
}
