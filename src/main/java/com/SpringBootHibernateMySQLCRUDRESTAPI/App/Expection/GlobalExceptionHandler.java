package com.SpringBootHibernateMySQLCRUDRESTAPI.App.Expection;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //Handler specific Exception
    @ExceptionHandler(ResourceNotFoundExpection.class)
    public ResponseEntity<?> handleResourceNotFoundException(
            ResourceNotFoundExpection resourceNotFoundExpection,
            WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                resourceNotFoundExpection.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


    //Handler specific Exception
    @ExceptionHandler(APIExpection.class)
    public ResponseEntity<?> handleAPIExpection(
             APIExpection exception,
            WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus()
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> handleInvalidArugument(MethodArgumentNotValidException exception,
                                               WebRequest request){
        Map<String, String> errorMap = new HashMap<>();
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
                    errorMap.put(error.getField(), error.getDefaultMessage());
                });

        return errorMap;


    }





    //Global Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception,
                                                              WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
