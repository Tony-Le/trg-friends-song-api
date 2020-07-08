package com.herokuapp.TRGFriendsSongs;

public class ControllerRuntimeException extends RuntimeException {
    public ControllerRuntimeException() {
        super("Something went wrong. ");
    }
}