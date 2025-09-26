package com.project.Attendance_System.ExceptionHandler.Custom;

public class VariableNotFound extends RuntimeException{
    public VariableNotFound(String message) {
        super(message);
    }
}
