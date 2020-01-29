package com.study.calendarservice.service;

import com.study.calendarservice.exception.UserDoesNotExistException;
import com.study.calendarservice.model.User;
import com.study.calendarservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getUserById(long userId){
       return userRepo.findById(userId).orElseThrow(UserDoesNotExistException::new);
    }

    public User addUser(User user){
        return userRepo.save(user);
    }

    public User editUser(long userId, User user){
        userRepo.findById(userId).orElseThrow(UserDoesNotExistException::new);
        return userRepo.save(user);
    }

    public void deleteUser(long userId){
        userRepo.deleteById(userId);
    }
}

