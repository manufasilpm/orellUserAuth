package com.sample.User.exception;

public class EmailAlreadyExistsException extends Throwable {
    public EmailAlreadyExistsException(String email) {
        super("Email already exists: " + email);
    }
}
