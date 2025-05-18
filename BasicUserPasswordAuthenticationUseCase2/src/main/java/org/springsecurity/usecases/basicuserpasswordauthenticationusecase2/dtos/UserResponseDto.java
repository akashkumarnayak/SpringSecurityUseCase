package org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.dtos;

import lombok.Data;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.models.UserState;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.models.UserType;

@Data
public class UserResponseDto {
    private String name;
    private String email;
    private String phoneNumber;
    private UserType userType;
    private UserState userState;
}
