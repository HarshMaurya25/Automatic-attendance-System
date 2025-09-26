package com.project.Attendance_System.ExceptionHandler.Custom;

public class EmailAlreadyExist extends RuntimeException{
    public EmailAlreadyExist(String message){
        super(message);
    }
}
