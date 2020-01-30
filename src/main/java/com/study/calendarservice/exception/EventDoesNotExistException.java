package com.study.calendarservice.exception;

public class EventDoesNotExistException extends RuntimeException {

    public EventDoesNotExistException(long id) {
        super("Event id not found: " + id);
    }
}
