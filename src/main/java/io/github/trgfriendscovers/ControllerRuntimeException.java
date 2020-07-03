package io.github.trgfriendscovers;

public class ControllerRuntimeException extends RuntimeException {
    public ControllerRuntimeException() {
        super("Something went wrong. ");
    }
}