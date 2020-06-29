package com.domain.oauth2.product.configuration.mvc

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class AuthenticatedUser(val required: Boolean = true)
