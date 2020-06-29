package com.domain.oauth2.campaign.model

class CampaignResponse(
        val id: Long,
        val name: String,
        val products: List<ProductResponse>
)
