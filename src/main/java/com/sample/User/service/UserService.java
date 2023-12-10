package com.sample.User.service;

import com.sample.User.dto.UserResponse;
import com.sample.User.model.User;
import com.sample.User.repository.UserRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRegistrationRepository userRegistrationRepository;


    public UserDetails loadUserByUserName(String username) {
        return userRegistrationRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

    public UserResponse getUserById(String email) {
        User user = userRegistrationRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToUserResponse(user);
    }

    public UserResponse mapToUserResponse(User user) {
        if (user == null) {
            return null;
        }
        return UserResponse.builder()
                .firstname(user.getFirstName())
                .lastname(user.getLastName())
                .email(user.getEmail())
                .dob(user.getDob())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }


    public UserResponse updateUserDetails(String email, UserResponse updatedUserDetails) {

        Optional<User> optionalUser = userRegistrationRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(updatedUserDetails.getFirstname());
            user.setLastName(updatedUserDetails.getLastname());
            user.setDob(updatedUserDetails.getDob());
            user.setAddress(updatedUserDetails.getAddress());
            user.setPhoneNumber(updatedUserDetails.getPhoneNumber());
            User updatedUser = userRegistrationRepository.save(user);
            return mapToUserResponse(updatedUser);
        } else {

            throw new RuntimeException("User not found for email: " + email);
        }


    }


}
