package com.amaljoyc.demo.camel.route

import com.amaljoyc.demo.camel.config.MongoDBProperties
import com.amaljoyc.demo.camel.config.RabbitMQProperties
import com.amaljoyc.demo.camel.model.InputData
import com.amaljoyc.demo.camel.processor.FirstProcessor
import com.amaljoyc.demo.camel.saveProcessDataRoute
import org.apache.camel.LoggingLevel.INFO
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.model.dataformat.JsonLibrary
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FirstRouter : RouteBuilder() {

    @Autowired
    lateinit var mq: RabbitMQProperties

    @Autowired
    lateinit var mongo: MongoDBProperties

    @Autowired
    lateinit var processor: FirstProcessor

    override fun configure() {
        from("rabbitmq://${mq.host}/${mq.route.exchange}"
                + "?routingKey=${mq.route.routingKey}"
                + "&queue=${mq.route.queue}"
                + "&deadLetterQueue=${mq.route.queue}.dlq"
                + "&deadLetterRoutingKey=${mq.route.queue}")
                .routeId("consumeSampleData")
                .unmarshal().json(JsonLibrary.Jackson, InputData::class.java)
                .bean(processor, "transformInputData")
                .to("direct:$saveProcessDataRoute")

        from("direct:$saveProcessDataRoute")
                .routeId(saveProcessDataRoute)
                .to("mongodb:mongoClient?database=${mongo.database}&collection=process_data&operation=insert")
                .log(INFO, "Saved ProcessData with id(s): \${in.header.CamelMongoOid}")
    }
}