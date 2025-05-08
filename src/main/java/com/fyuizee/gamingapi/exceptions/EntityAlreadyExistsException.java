package com.fyuizee.gamingapi.exceptions;

import java.util.List;
import java.util.stream.Collectors;

public class EntityAlreadyExistsException extends RuntimeException {
    public <T> EntityAlreadyExistsException(String identifier, Class<T> clazz) {
        super("%s with \"%s\" already exists".formatted(clazz.getSimpleName(), identifier));
    }

    public <T> EntityAlreadyExistsException(List<String> identifiers, Class<T> clazz) {
        super("%s with \"%s\" already exists".formatted(clazz.getSimpleName(), String.join(" or ", identifiers)));
    }
}
