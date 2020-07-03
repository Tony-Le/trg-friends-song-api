package io.github.trgfriendscovers;

public class CoverNotFoundException extends RuntimeException {
    public CoverNotFoundException(String id) {
        super("Could not find cover " + id);
    }
}
