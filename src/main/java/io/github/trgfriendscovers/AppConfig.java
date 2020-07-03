package io.github.trgfriendscovers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.commons.logging.Log;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class AppConfig {

    /*
     * Use the standard Mongo driver API to create a com.mongodb.client.MongoClient instance.
     */
    private static final Log log = LogFactory.getLog(AppConfig.class);

    // spring.application.local.mongodb obtained from application.properties
    @Value("${spring.application.local.mongodb}")
    private String mongoConnection;

    @Bean
    public MongoClient mongoClient() {
            return MongoClients.create(mongoConnection);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(), "data");
        return mongoTemplate;
    }


}
