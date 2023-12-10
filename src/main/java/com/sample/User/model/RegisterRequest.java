package com.sample.User.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String firstname ;
    private String lastname ;
    private String email;
    private LocalDate dob;
    private String address;
    private String phoneNumber;
    private String password;
}


