package com.domain.oauth2.product.configuration.security

import com.domain.oauth2.product.configuration.security.CustomUserAuthenticationConverter
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@Configuration
@EnableResourceServer
class ResourceServerConfiguration : ResourceServerConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
    }

    @Bean // disable OAuth2AutoConfiguration
    @ConfigurationProperties(prefix = "security.oauth2.resource")
    fun resourceServerProperties(): ResourceServerProperties {
        return ResourceServerProperties()
    }

    @Bean // disable OAuth2AutoConfiguration
    fun jwtTokenServices(jwtTokenStore: TokenStore): DefaultTokenServices {
        val services = DefaultTokenServices()
        services.setTokenStore(jwtTokenStore)
        return services
    }

    @Bean// disable OAuth2AutoConfiguration
    fun jwtTokenStore(jwtAccessTokenConverter: JwtAccessTokenConverter): TokenStore {
        return JwtTokenStore(jwtAccessTokenConverter)
    }

    @Bean // disable OAuth2AutoConfiguration
    fun jwtAccessTokenConverter(resourceServerProperties: ResourceServerProperties): JwtAccessTokenConverter {
        val converter = JwtAccessTokenConverter()

        val defaultAccessTokenConverter = DefaultAccessTokenConverter()
        defaultAccessTokenConverter.setUserTokenConverter(CustomUserAuthenticationConverter())
        converter.accessTokenConverter = defaultAccessTokenConverter

        val keyValue = resourceServerProperties.jwt.keyValue
        converter.setVerifierKey(keyValue)

        return converter
    }
}
