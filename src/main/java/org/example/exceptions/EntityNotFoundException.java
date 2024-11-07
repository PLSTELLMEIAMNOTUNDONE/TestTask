package org.example.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends HttpException {

    public <T> EntityNotFoundException(Class<T> clazz, Object... args) {
        super(HttpStatus.NOT_FOUND,
                "Entity "
                + clazz.getSimpleName()
                + " with parameters "
                + Arrays.toString(args)
                + " was not found");
    }
}
