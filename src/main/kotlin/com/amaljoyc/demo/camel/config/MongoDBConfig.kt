package com.amaljoyc.demo.camel.config

import com.mongodb.Mongo
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MongoDBConfig {

    @Autowired
    lateinit var properties: MongoDBProperties

    @Bean
    fun mongoClient(): Mongo {
        return MongoClient(MongoClientURI(properties.uri))
    }
}