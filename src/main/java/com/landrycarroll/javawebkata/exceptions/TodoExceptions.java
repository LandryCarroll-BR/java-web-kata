package com.landrycarroll.javawebkata.exceptions;

public class TodoExceptions {
    public static class InvalidInputException extends IllegalArgumentException {
        public InvalidInputException(String message) {
            super(message);
        }
    }

    public static class TodoAlreadyExistsException extends RuntimeException {
        public TodoAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class TodoDoesNotExistException extends RuntimeException {
        public TodoDoesNotExistException(String message) {
            super(message);
        }
    }
}
