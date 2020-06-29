package com.domain.oauth2.campaign

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [OAuth2AutoConfiguration::class])
class CampaignApiApplication

fun main(args: Array<String>) {
    runApplication<CampaignApiApplication>(*args)
}
