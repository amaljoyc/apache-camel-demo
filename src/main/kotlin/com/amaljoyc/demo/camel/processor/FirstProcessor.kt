package com.amaljoyc.demo.camel.processor

import com.amaljoyc.demo.camel.model.InputData
import com.amaljoyc.demo.camel.model.ProcessData
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

@Component
class FirstProcessor {

    private val logger = KotlinLogging.logger {}

    fun transformInputData(inputData: InputData): ProcessData {
        logger.info { "Consumed InputData with number: ${inputData.number}" }
        return inputData.toProcessData()
    }

    fun InputData.toProcessData(): ProcessData {
        return ProcessData(
                createdDate = OffsetDateTime.now().toString(),
                text = text,
                number = number
        )
    }
}