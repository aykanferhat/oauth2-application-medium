package com.domain.oauth2.product.controller

import com.domain.oauth2.product.configuration.mvc.AuthenticatedUser
import com.domain.oauth2.product.model.AccountDetails
import com.domain.oauth2.product.model.ProductResponse
import com.domain.oauth2.product.service.ProductService
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products")
class ProductController(private val productService: ProductService) {

    //    @Secured("ROLE_ADMIN", "ROLE_ADMIN")
    @PreAuthorize("#account.hasAuthority('ROLE_READ_PRODUCT') or #account.hasAuthority('ROLE_ADMIN')")
    @GetMapping
    fun getProducts(@AuthenticatedUser account: AccountDetails): List<ProductResponse> {
        println()
        return productService.findAll()
    }
}
