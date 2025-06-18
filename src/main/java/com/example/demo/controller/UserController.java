package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

//    @Autowired
//    UserServiceImpl userServiceImpl;


    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/users")
    public void createUser(@RequestBody User user){
        userServiceImpl.createUser(user);
//        return user;
    }

    @GetMapping("/users")
    public Iterable<User> getUsers(){
        return userServiceImpl.getAllUsers();
//        return null;
    }

    @GetMapping("/users/me")
    public User getCurrentUser(){
        return userServiceImpl.getCurrentUser();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id){
        return userServiceImpl.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id){
        userServiceImpl.deleteUserById(id);
    }
}
