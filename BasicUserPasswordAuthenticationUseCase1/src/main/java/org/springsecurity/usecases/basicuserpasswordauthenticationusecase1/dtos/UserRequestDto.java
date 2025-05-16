package org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.dtos;

import lombok.Data;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.models.UserState;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.models.UserType;

@Data
public class UserRequestDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private UserType userType;
    private UserState userState;
}
