package com.herokuapp.TRGFriendsSongs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongSearchService {

    //mongoTemplate initialized from a Spring annotated Configuration Java class and made available here through the Spring @Bean annotation
    private final MongoTemplate mongoTemplate;
    private static final int DEFAULT_PAGE_SIZE = 15;
    private  static final int MAX_PAGE_SIZE = 100;

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

    private static Query searchQuery(String search) {
        Query query = TextQuery.queryText(new TextCriteria().matchingAny(search.split(" "))).sortByScore().addCriteria(Criteria.where("searchable").is(1));
        return query;
    }

    @Cacheable("search")
    public List<Song> findSong(String search, int pageNumber, int pageSize) {
        Query query = this.searchQuery(search);
        pageSize = Math.min(pageSize, MAX_PAGE_SIZE);
        List<Song> results = mongoTemplate.find(query.with(PageRequest.of(pageNumber, pageSize)), Song.class);
        return results;
    }

    @Cacheable("search")
    public List<Song> findSong(String search, int pageSize) {
        Query query = this.searchQuery(search);
        pageSize = Math.min(pageSize, MAX_PAGE_SIZE);
        List<Song> results = mongoTemplate.find(query.with(PageRequest.of(0, pageSize)), Song.class);
        return results;
    }

    @Cacheable("search")
    public List<Song> findSong(String search) {
        Query query = this.searchQuery(search);
        List<Song> results = mongoTemplate.find(query.with(PageRequest.of(0, DEFAULT_PAGE_SIZE)), Song.class);
        return results;
    }
}
