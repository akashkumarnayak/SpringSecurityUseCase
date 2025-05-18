package org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.dtos;

import lombok.Data;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.models.UserState;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.models.UserType;

@Data
public class UserRequestDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private UserType userType;
    private UserState userState;
}
