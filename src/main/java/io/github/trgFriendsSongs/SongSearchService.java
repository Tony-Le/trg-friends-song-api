package io.github.trgFriendsSongs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongSearchService {

    //mongoTemplate initialized from a Sprring annotated Configuration Java class and made available here through the Spring @Bean annotation
    private final MongoTemplate mongoTemplate;
    private static final Logger logger = LoggerFactory.getLogger(SongSearchService.class);

    public SongSearchService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Cacheable("id")
    public Song getById(String id) {
       return mongoTemplate.findById(id, Song.class);
    }

    @Cacheable("all")
    public List<Song> getAll() {
        return mongoTemplate.findAll(Song.class);
    }

    @Cacheable("search")
    public List<Song> findSong(String search) {
        Query query = TextQuery.queryText(new TextCriteria().matchingAny(search.split(" "))).sortByScore().limit(20);
        List<Song> results = mongoTemplate.find(query, Song.class);
        return results;
    }
}
