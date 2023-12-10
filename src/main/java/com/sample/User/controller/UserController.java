package com.sample.User.controller;


import com.sample.User.dto.UserResponse;
import com.sample.User.model.User;
import com.sample.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/userDetails")
//    public void userDetails(){
//        System.out.println("user details");
//    }

    @GetMapping("/user-details")
    public ResponseEntity<UserResponse > getUserDetails(@RequestParam String  email) {
        // Extract user ID from UserDetails
        UserResponse  user = userService.getUserById(email);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUserDetails(@RequestParam String email, @RequestBody UserResponse updatedUserDetails) {
        UserResponse updatedUser = userService.updateUserDetails(email, updatedUserDetails);
        return ResponseEntity.ok(updatedUser);
    }

}
