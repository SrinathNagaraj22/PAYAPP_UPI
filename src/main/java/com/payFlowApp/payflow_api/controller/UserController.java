package com.payFlowApp.payflow_api.controller;

import com.payFlowApp.payflow_api.entity.User;
import com.payFlowApp.payflow_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @DeleteMapping("{id}")
    public String deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ("User deleted Successfully");
    }

    @GetMapping("/upi/{upiId}")
    public User findByUpiId(@PathVariable String upiId){
        return userService.findByUpiId(upiId).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
