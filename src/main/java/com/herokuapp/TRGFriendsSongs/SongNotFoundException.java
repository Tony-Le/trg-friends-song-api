package com.herokuapp.TRGFriendsSongs;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(String id) {
        super("Could not find song " + id);
    }
}
