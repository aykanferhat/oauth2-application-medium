package com.domain.oauth2.campaign.service

import com.domain.oauth2.campaign.client.ProductApiClient
import com.domain.oauth2.campaign.model.CampaignResponse
import org.springframework.stereotype.Service

@Service
class CampaignService(private val productApiClient: ProductApiClient) {

    fun findCampaignById(campaignId: Long): CampaignResponse {
        val products = productApiClient.getProducts()
        return CampaignResponse(
                id = 100,
                name = "Süper İkili",
                products = products
        )
    }
}
