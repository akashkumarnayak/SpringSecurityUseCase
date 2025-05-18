package org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.dtos;

import lombok.Data;

@Data
public class UserSignInDto {

    private String userName;
    private String password;

}
