package com.domain.oauth2.campaign.controller

import com.domain.oauth2.campaign.model.CampaignResponse
import com.domain.oauth2.campaign.service.CampaignService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("campaigns")
class CampaignController(private val campaignService: CampaignService) {

    @GetMapping("/{campaignId}")
    fun getCampaign(@PathVariable campaignId: Long): CampaignResponse {
        return campaignService.findCampaignById(campaignId)
    }
}
