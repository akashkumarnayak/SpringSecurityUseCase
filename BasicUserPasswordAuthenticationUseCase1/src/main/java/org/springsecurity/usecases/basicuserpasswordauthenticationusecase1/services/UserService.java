package org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.exceptions.UserDoesNotExistException;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.models.User;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.models.UserState;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.repos.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUserState(UserState.ACTIVE);
        return userRepository.save(user);
    }

    public User updateUser(User user){

        Optional<User> userFetched = userRepository.findUsersByEmail(user.getEmail());

        if (userFetched.isPresent()) {
            userFetched.get().setName(user.getName());
            userFetched.get().setEmail(user.getEmail());
            userFetched.get().setPhoneNumber(user.getPhoneNumber());
            userFetched.get().setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userFetched.get().setUserState(user.getUserState());
            userFetched.get().setUserType(user.getUserType());
            userRepository.save(userFetched.get());

        }

        return userFetched.get();

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {

        Optional<User> user = userRepository.findUsersByEmail(email);

        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

}
