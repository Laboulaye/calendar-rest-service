package com.study.calendarservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class MainHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EventDoesNotExistException .class)
    protected void handleEventDoesNotExistException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(UserDoesNotExistException .class)
    protected void handleUserDoesNotExistException(HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    protected void handleTypeMismatchException(HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Incorrect request. Id should be type long");
    }

}