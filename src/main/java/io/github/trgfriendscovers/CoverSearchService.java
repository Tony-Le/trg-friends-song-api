package io.github.trgfriendscovers;

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
public class CoverSearchService {

    //mongoTemplate initialized from a Sprring annotated Configuration Java class and made available here through the Spring @Bean annotation
    private final MongoTemplate mongoTemplate;
    private static final Logger logger = LoggerFactory.getLogger(CoverSearchService.class);

    public CoverSearchService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Cacheable("id")
    public Cover getById(String id) {
       return mongoTemplate.findById(id, Cover.class);
    }

    @Cacheable("all")
    public List<Cover> getAll() {
        return mongoTemplate.findAll(Cover.class);
    }

    @Cacheable("search")
    public List<Cover> findCover(String search) {
        Query query = TextQuery.query(new TextCriteria().matching(search));
        List<Cover> results = mongoTemplate.find(query, Cover.class);
        return results.subList(0,Math.min(results.size(), 20));
    }
}
