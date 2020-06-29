package com.domain.oauth2.campaign.client

import com.domain.oauth2.campaign.configuration.feign.FeignAuthorizationInterceptorConfiguration
import com.domain.oauth2.campaign.model.ProductResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(
        value = "\${feign.client.config.product-api.name}",
        url = "\${feign.client.config.product-api.url}",
        configuration = [FeignAuthorizationInterceptorConfiguration::class])
interface ProductApiClient {

    @GetMapping("/campaign-api/products")
    fun getProducts(): List<ProductResponse>
}
