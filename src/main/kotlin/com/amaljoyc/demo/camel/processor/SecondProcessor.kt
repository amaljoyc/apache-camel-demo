package com.amaljoyc.demo.camel.processor

import com.amaljoyc.demo.camel.exportProcessDataRoute
import mu.KotlinLogging
import org.apache.camel.CamelContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class SecondProcessor {

    private val logger = KotlinLogging.logger {}

    @Autowired
    lateinit var camelContext: CamelContext

    @Scheduled(cron = "0 * * * * *") // runs every min
    fun exportToFile() {
        logger.info { "Exporting ProcessData!" }
        val producerTemplate = camelContext.createProducerTemplate()
        val filterByThisValue = 100 // eg. to show find by a particular column
        producerTemplate.sendBodyAndHeaders(
                "direct:$exportProcessDataRoute",
                mapOf("number" to filterByThisValue), // filter by number
                emptyMap() // add headers if needed
        )
    }

}
