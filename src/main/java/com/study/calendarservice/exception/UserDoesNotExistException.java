package com.study.calendarservice.exception;

public class UserDoesNotExistException extends RuntimeException {

    public UserDoesNotExistException(long userId) {
        super("User id not found: " + userId);
    }
}
