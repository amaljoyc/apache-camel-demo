package com.amaljoyc.demo.camel.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("mongodb")
class MongoDBProperties {
    lateinit var database: String
    lateinit var uri: String
}