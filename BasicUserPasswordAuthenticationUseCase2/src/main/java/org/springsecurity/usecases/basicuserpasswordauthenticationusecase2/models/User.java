package org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User extends BaseModel {

    private String name;

    @Column(unique = true)
    private String email;
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Enumerated(EnumType.STRING)
    private UserState userState;

}
