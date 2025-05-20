package org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.services;

import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.models.User;

import java.util.List;

public interface IUserService {

    public User createUser(User user);
    public User updateUser(User user);
    public List<User> getAllUsers();
    public User getUserByEmail(String email);
    public String authenticate(String userName, String password);
}
