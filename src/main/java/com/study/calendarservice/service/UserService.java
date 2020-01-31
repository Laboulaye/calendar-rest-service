package com.study.calendarservice.service;

import com.study.calendarservice.exception.UserDoesNotExistException;
import com.study.calendarservice.model.User;
import com.study.calendarservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public User addUser(User user){
        return userRepo.save(user);
    }

    public User getUserById(long userId){
        isUserExist(userId);
        return userRepo.getOne(userId);
    }

    public User editUser(long userId, User user){
        isUserExist(userId);
        user.setId(userId);
        return userRepo.save(user);
    }

    public void deleteUser(long userId){
        userRepo.deleteById(userId);
    }


    private void isUserExist(long userId){
        userRepo.findById(userId).orElseThrow(()->new UserDoesNotExistException(userId));
    }
}

