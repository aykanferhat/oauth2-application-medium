package com.domain.oauth2.product.controller.campaignapi

import com.domain.oauth2.product.model.ProductResponse
import com.domain.oauth2.product.service.ProductService
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/campaign-api/products")
class ProductCampaignApiController(private val productService: ProductService) {

    @Secured("ROLE_CLIENT")
    @GetMapping
    fun getProducts(): List<ProductResponse> {
        return productService.findAll()
    }
}
