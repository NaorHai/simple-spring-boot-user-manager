package com.haimov.naor.java.practice.javapractice.controller;

import com.haimov.naor.java.practice.javapractice.dao.UserRepository;
import com.haimov.naor.java.practice.javapractice.entity.User;
import com.haimov.naor.java.practice.javapractice.entity.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("Fetching all users...");
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Optional<User> getUserById(@PathVariable String id) {
        System.out.println("Fetching user: " + id);
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
        System.out.println("Storing new user: " + user.toString());
        return userRepository.save(user);
    }

    @PostMapping("/update")
    public @ResponseBody
    User createUser(@RequestBody User obj) {
     Optional<User> userToUpdate = userRepository.findById(obj.getId());
     if (userToUpdate.isPresent()) {
         User u = userToUpdate.get();
         u.setName(obj.getName());
         u.setPhone(obj.getPhone());
         u.setZip(obj.getZip());
         u.setDateOfBirth(obj.getDateOfBirth());
         u.setCity(obj.getCity());
         System.out.println("Updating user: " + u.toString());
         return userRepository.save(u);
     }
        System.out.println("Storing new user: " + obj.toString());
        return userRepository.save(obj);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    String deleteUser(@PathVariable String id) {
        try {
            userRepository.deleteById(id);
            System.out.println("Deleted user: " + id);
            return "success";
        } catch (Exception ex) {
            return "ERROR! " + ex.getMessage();
        }
    }
}
