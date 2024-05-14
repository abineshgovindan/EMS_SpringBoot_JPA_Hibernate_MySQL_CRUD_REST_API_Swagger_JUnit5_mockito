package com.SpringBootHibernateMySQLCRUDRESTAPI.App.Expection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class APIExpection extends RuntimeException{
    public APIExpection(String message) {
        super(message);

    }

}
