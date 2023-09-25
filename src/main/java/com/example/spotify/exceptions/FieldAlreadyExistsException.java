package com.example.spotify.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
@Getter
@AllArgsConstructor
public class FieldAlreadyExistsException extends RuntimeException{
    private ExceptionMessage errorMessage;

    public FieldAlreadyExistsException(String fieldName, String value) {
        errorMessage = new ExceptionMessage(String.format("%s '%s' already exists", fieldName, value));
    }
}
