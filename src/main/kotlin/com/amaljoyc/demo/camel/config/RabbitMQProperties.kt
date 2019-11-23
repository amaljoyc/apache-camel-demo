package com.amaljoyc.demo.camel.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("rabbitmq")
class RabbitMQProperties {
    lateinit var host: String
    val route: RouteConfig = RouteConfig()

    class RouteConfig {
        lateinit var exchange: String
        lateinit var queue: String
        lateinit var routingKey: String
    }
}

