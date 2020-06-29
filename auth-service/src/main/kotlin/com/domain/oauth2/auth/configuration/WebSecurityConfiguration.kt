package com.domain.oauth2.auth.configuration

import com.domain.oauth2.auth.manager.CustomAuthenticationManager
import com.domain.oauth2.auth.service.CustomUserDetailsService
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@Order(2)
class WebSecurityConfiguration(private val customAuthenticationManager: CustomAuthenticationManager,
                               private val customUserDetailsService: CustomUserDetailsService) : WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable().formLogin()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/token", "/jwk.json").permitAll()
                .anyRequest().authenticated()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
                .parentAuthenticationManager(customAuthenticationManager)
                .userDetailsService(customUserDetailsService)
//              supported Multi AuthenticationProvider
//                .authenticationProvider(CustomXProvider)
//                .authenticationProvider(CustomYProvider)
    }
}
