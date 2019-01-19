package com.haimov.naor.java.practice.javapractice.controller;

import com.haimov.naor.java.practice.javapractice.dao.UserRepository;
import com.haimov.naor.java.practice.javapractice.entity.User;
import com.haimov.naor.java.practice.javapractice.entity.UserRequest;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/demo/users")
public class Controller {
    final private UserRepository userRepository;

    @Autowired
    public Controller(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Optional<User> getUserById(@PathVariable String id) {
        return userRepository.findById(id);
    }

    @PostMapping("/create")
    public @ResponseBody
    User createUser(@RequestBody UserRequest userRequest) {
        User user = new User();
        if (userRequest != null) {
            user.setName(userRequest.getName());
            user.setCity(userRequest.getCity());
            user.setDateOfBirth(userRequest.getDateOfBirth());
            user.setZip(userRequest.getZip());
            user.setPhone(userRequest.getPhone());
        }
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    String deleteUser(@PathVariable String id) {
        try {
            userRepository.deleteById(id);
            return "Success";
        }catch (Exception ex) {
            return "Err! " + ex.getMessage();
        }
    }
}
