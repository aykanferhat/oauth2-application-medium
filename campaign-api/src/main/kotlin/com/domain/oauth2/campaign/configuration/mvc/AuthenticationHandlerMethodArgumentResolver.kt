package com.domain.oauth2.campaign.configuration.mvc

import com.domain.oauth2.campaign.model.AccountDetails
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class AuthenticationHandlerMethodArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(AuthenticatedUser::class.java) && parameter.parameterType == AccountDetails::class.java
    }

    override fun resolveArgument(parameter: MethodParameter,
                                 mavContainer: ModelAndViewContainer?,
                                 webRequest: NativeWebRequest,
                                 binderFactory: WebDataBinderFactory?): Any? {
        return SecurityContextHolder.getContext().getAccountDetails()
    }
}

fun SecurityContext.getAccountDetails(): AccountDetails {
    return (this.authentication as OAuth2Authentication).userAuthentication.principal as AccountDetails
}
