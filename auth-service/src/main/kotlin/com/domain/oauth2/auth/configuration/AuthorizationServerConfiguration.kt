package com.domain.oauth2.auth.configuration

import com.domain.oauth2.auth.manager.CustomAuthenticationManager
import com.domain.oauth2.auth.service.CustomUserDetailsService
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfiguration(
        private val passwordEncoder: PasswordEncoder,
        private val tokenStore: JwtTokenStore,
        private val jwtAccessTokenConverter: JwtAccessTokenConverter,
        private val customAuthenticationManager: CustomAuthenticationManager,
        private val customUserDetailsService: CustomUserDetailsService) : AuthorizationServerConfigurerAdapter() {

    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security
                .checkTokenAccess("hasAuthority('ROLE_CHECK_TOKEN')")
                .tokenKeyAccess("permitAll()")
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients
                .inMemory()

                .withClient("clientX").secret(passwordEncoder.encode("clientSecretX"))
                .authorities("ROLE_CLIENT")
                .scopes("read", "write")
                .autoApprove(true) // Client'ın kullanıcıya izin istediği yetkileri sormak istersek false olmalı.
                .authorizedGrantTypes("authorization_code")
                .redirectUris("http://localhost:8080/token")
                .accessTokenValiditySeconds(24 * 365 * 60 * 60)

                .and()

                .withClient("clientY").secret(passwordEncoder.encode("clientSecretY"))
                .authorities("ROLE_CLIENT")
                .scopes("openid")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(24 * 365 * 60 * 60)

                .and()

                .withClient("productApi").secret(passwordEncoder.encode("productApiSecret"))
                .authorities("ROLE_CHECK_TOKEN", "ROLE_CLIENT")
                .scopes("openid")
                .authorizedGrantTypes("client_credentials")
                .accessTokenValiditySeconds(24 * 365 * 60 * 60)

                .and()

                .withClient("campaignApi").secret(passwordEncoder.encode("campaignApiSecret"))
                .authorities("ROLE_CHECK_TOKEN", "ROLE_CLIENT")
                .scopes("openid")
                .authorizedGrantTypes("client_credentials")
                .accessTokenValiditySeconds(24 * 365 * 60 * 60)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.tokenStore(tokenStore)
                .tokenEnhancer(jwtAccessTokenConverter)
                .authenticationManager(customAuthenticationManager)
                .userDetailsService(customUserDetailsService)
    }
}
