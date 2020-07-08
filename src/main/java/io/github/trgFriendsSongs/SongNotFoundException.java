package io.github.trgFriendsSongs;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(String id) {
        super("Could not find song " + id);
    }
}
