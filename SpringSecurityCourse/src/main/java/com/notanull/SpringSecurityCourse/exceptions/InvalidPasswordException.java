package com.notanull.SpringSecurityCourse.exceptions;

public class InvalidPasswordException extends RuntimeException{

    public InvalidPasswordException(String message) {
        super(message);
    }
}
