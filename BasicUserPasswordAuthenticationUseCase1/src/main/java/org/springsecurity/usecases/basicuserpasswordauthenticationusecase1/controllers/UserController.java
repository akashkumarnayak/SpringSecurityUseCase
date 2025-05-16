package org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.dtos.UserRequestDto;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.dtos.UserResponseDto;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.exceptions.UserDoesNotExistException;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.models.User;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.models.UserState;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase1.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/create")
    public UserResponseDto signUp(@RequestBody UserRequestDto userRequestDto)
    {
        User user = userService.createUser(from(userRequestDto));
        return from(user);
    }

    @PutMapping("/user/update")
    public UserResponseDto changeUserDetails(@RequestBody UserRequestDto userRequestDto) throws UserDoesNotExistException {
        User updatedUser = userService.updateUser(from(userRequestDto));

        if(updatedUser == null)
        {
            throw new UserDoesNotExistException("User does not exist");
        }

        return from(updatedUser);
    }

    @GetMapping("/user/get-all")
    public List<UserResponseDto> getAllUsers()
    {
        List<User> users = userService.getAllUsers();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        for (User user : users)
        {
            userResponseDtos.add(from(user));
        }

        return userResponseDtos;
    }

    @GetMapping("/user/{email}")
    public UserResponseDto getUserByEmail(@PathVariable String email) throws UserDoesNotExistException {

        User user = userService.getUserByEmail(email);

        if (user == null)
            throw new UserDoesNotExistException("User does not exist");
        return from(user);
    }


    UserResponseDto from(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPhoneNumber(user.getPhoneNumber());
        userResponseDto.setUserType(user.getUserType());
        userResponseDto.setUserState(user.getUserState());
        return userResponseDto;
    }

    User from(UserRequestDto userRequestDto)
    {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setUserState(userRequestDto.getUserState());
        user.setPhoneNumber(String.valueOf(userRequestDto.getPhoneNumber()));
        user.setUserType(userRequestDto.getUserType());
        user.setPassword(userRequestDto.getPassword());
        return user;
    }
}
