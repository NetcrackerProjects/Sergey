package com.netcracker.study.general.Exceptions;

public class NoSuchUserException extends Exception {
    public NoSuchUserException(String errorMessage) {
        super(errorMessage);
    }
}
