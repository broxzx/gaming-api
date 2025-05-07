package com.fyuizee.gamingapi.exceptions;

public class DataNotFoundException extends RuntimeException {

    public <T> DataNotFoundException(Object identifier, Class<T> clazz) {
        super("%s with id %s not found".formatted(clazz.getSimpleName(), identifier));
    }

}
