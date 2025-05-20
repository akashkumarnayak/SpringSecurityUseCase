package org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.services;

import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.models.User;

import java.util.List;

public interface IUserService {

    public User createUser(User user);
    public User updateUser(User user);
    public List<User> getAllUsers();
    public User getUserByEmail(String email);
}
