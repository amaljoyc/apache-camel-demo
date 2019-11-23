package com.amaljoyc.demo.camel.route

import com.amaljoyc.demo.camel.config.MongoDBProperties
import com.amaljoyc.demo.camel.exportProcessDataRoute
import com.amaljoyc.demo.camel.saveToFileRoute
import org.apache.camel.LoggingLevel.INFO
import org.apache.camel.builder.RouteBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class SecondRouter : RouteBuilder() {

    @Autowired
    lateinit var mongo: MongoDBProperties

    override fun configure() {
        from("direct:$exportProcessDataRoute")
                .routeId(exportProcessDataRoute)
                .to("mongodb:mongoClient?database=${mongo.database}&collection=process_data&operation=findAll")
                .to("direct:$saveToFileRoute")

        from("direct:$saveToFileRoute")
                .routeId(saveToFileRoute)
                .marshal().csv()
                .to("file:target/output/?fileName=process_data.csv")
                .log(INFO, "Saved ProcessData into a file!")
    }
}