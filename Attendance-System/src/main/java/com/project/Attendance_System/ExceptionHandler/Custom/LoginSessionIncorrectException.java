package com.project.Attendance_System.ExceptionHandler.Custom;

public class LoginSessionIncorrectException extends RuntimeException{
    public LoginSessionIncorrectException(String message) {
        super(message);
    }
}
