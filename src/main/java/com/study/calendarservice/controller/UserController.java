package com.study.calendarservice.controller;

import com.study.calendarservice.model.User;
import com.study.calendarservice.repository.UserRepo;
import com.study.calendarservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String welcome(){
        return "Welcome";
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") long userId){
        return userService.getUserById(userId);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/users/{userId}")
    public User editUser(@PathVariable("userId") long userId, @RequestBody User user){
        return userService.editUser(userId, user);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable("userId") long userId){
        userService.deleteUser(userId);
    }
}
