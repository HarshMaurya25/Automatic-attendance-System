    package com.project.Attendance_System.ExceptionHandler;

    import com.project.Attendance_System.ExceptionHandler.Custom.EmailAlreadyExist;
    import com.project.Attendance_System.ExceptionHandler.Custom.LoginSessionIncorrectException;
    import com.project.Attendance_System.ExceptionHandler.Custom.VariableNotFound;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.MethodArgumentNotValidException;
    import org.springframework.web.bind.annotation.ControllerAdvice;
    import org.springframework.web.bind.annotation.ExceptionHandler;

    import java.util.HashMap;
    import java.util.Map;

    @Slf4j
    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String , String>> handleValidationException(
                MethodArgumentNotValidException exception
        ){
            Map<String , String> errors = new HashMap<>();

            exception.getBindingResult().getFieldErrors().forEach(
                    error -> errors.put(error.getField(), error.getDefaultMessage())
            );

            return ResponseEntity.badRequest().body(errors);
        }

        @ExceptionHandler(EmailAlreadyExist.class)
        public ResponseEntity<Map<String , String>> emailAlreadyExist(
                EmailAlreadyExist emailAlreadyExist
        ){
            log.warn("Email Already Exists : {}" , emailAlreadyExist.getMessage());
            Map<String , String> error = new HashMap<>();
            error.put("Email" , "Email Already Exists");
            return ResponseEntity.badRequest().body(error);
        }

        @ExceptionHandler(LoginSessionIncorrectException.class)
        public ResponseEntity<Map<String , String>> loginSessionIncorrect(
                LoginSessionIncorrectException exception
        ){
            log.warn("Email Already Exists : {}" , exception.getMessage());
            Map<String , String> error = new HashMap<>();
            error.put("LoginSession" , "Incorrect Login Exception");
            return ResponseEntity.badRequest().body(error);
        }

        @ExceptionHandler(VariableNotFound.class)
        public ResponseEntity<Map<String , String>> variableNotFound(
                VariableNotFound exception
        ){
            log.warn("{} is not found", exception.getMessage());
            Map<String , String > error = new HashMap<>();
            error.put(exception.getMessage() , "Does Not Found");

            return ResponseEntity.badRequest().body(error);
        }

    }
