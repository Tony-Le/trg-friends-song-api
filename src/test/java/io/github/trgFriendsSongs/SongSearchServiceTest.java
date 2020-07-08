package io.github.trgFriendsSongs;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class SongSearchServiceTest {

    private final SongSearchService service;

    @Autowired
    SongSearchServiceTest(SongSearchService service) {
        this.service = service;
    }

    @Test
    public void allUniqueID() throws Exception {
        List<Song> songsList = service.findSong("toxic");
        Set<String> songIds = new HashSet<String>();
        songsList.iterator().forEachRemaining(a -> songIds.add(a.getId()));

        Assertions.assertEquals(songsList.size(),songIds.size());
    }
}
