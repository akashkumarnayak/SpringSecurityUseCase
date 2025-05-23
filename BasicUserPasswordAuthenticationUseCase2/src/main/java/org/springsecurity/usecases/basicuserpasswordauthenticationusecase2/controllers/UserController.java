package org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.controllers;

import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.dtos.UserRequestDto;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.dtos.UserResponseDto;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.dtos.UserSignInDto;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.exceptions.UserDoesNotExistException;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.models.User;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.services.IUserService;
import org.springsecurity.usecases.basicuserpasswordauthenticationusecase2.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private IUserService userService;

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

    @PostMapping("/user/signin")
    public ResponseEntity<?> userSignIn(@RequestBody UserSignInDto userSignInDto)
    {
        String jwt = userService.authenticate(userSignInDto.getUserName(), userSignInDto.getPassword());
        return ResponseEntity.ok(jwt);
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
