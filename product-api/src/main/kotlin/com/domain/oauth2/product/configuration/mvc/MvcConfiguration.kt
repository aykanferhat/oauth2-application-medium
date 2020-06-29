package com.domain.oauth2.product.configuration.mvc

import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class MvcConfiguration(
        private val authenticationHandlerMethodArgumentResolver: AuthenticationHandlerMethodArgumentResolver) : WebMvcConfigurer {

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
        argumentResolvers.add(authenticationHandlerMethodArgumentResolver)
    }
}
