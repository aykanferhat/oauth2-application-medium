package com.domain.oauth2.product.service

import com.domain.oauth2.product.model.ProductResponse
import org.springframework.stereotype.Service

@Service
class ProductService {

    // find from Repository
    fun findAll(): List<ProductResponse> = listOf(
            ProductResponse(
                    id = 1,
                    title = "Iphone 7",
                    description = "Description"
            ),
            ProductResponse(
                    id = 2,
                    title = "Samsung Galaxy",
                    description = "Description"
            )
    )
}
