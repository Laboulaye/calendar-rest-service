package com.study.calendarservice.controller;

import com.study.calendarservice.model.User;
import com.study.calendarservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @ApiOperation(value = "Home page")
    public String welcome(){
        return "Welcome to Homepage REST web App";
    }

    @GetMapping("/users")
    @ApiOperation(value = "View a list of available users", response = List.class)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    @ApiOperation(value = "Add user")
    public User addUser(@ApiParam(value = "User object for add", required = true)@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/users/{userId}")
    @ApiOperation(value = "Get user by Id")
    public User getUserById(@ApiParam(value = "User Id from which user object will retrieve", required = true)
                                @PathVariable("userId") long userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/users/{userId}")
    @ApiOperation(value = "Edit user")
    public User editUser(@ApiParam(value = "User Id from which user object will edit", required = true)
                             @PathVariable("userId") long userId,
                         @ApiParam(value = "Edit user object", required = true)@RequestBody User user){
        return userService.editUser(userId, user);
    }

    @DeleteMapping("/users/{userId}")
    @ApiOperation(value = "Delete user")
    public void deleteUser(@ApiParam(value = "User Id from which user object will delete", required = true)
                               @PathVariable("userId") long userId){
        userService.deleteUser(userId);
    }
}
