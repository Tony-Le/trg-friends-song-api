package io.github.trgfriendscovers;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class CoverSearchServiceTest {

    private final CoverSearchService service;

    @Autowired
    CoverSearchServiceTest(CoverSearchService service) {
        this.service = service;
    }

    @Test
    public void allUniqueID() throws Exception {
        List<Cover> coversList = service.findCover("toxic");
        Set<String> coverIds = new HashSet<String>();
        coversList.iterator().forEachRemaining(a -> coverIds.add(a.getId()));

        Assertions.assertEquals(coversList.size(),coverIds.size());
    }
}
