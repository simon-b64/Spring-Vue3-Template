package at.simonb64.template.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@Profile("openapi")
class OpenapiSecurityConfig {

    @Bean
    fun openApiSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests {
            it.requestMatchers("/v3/api-docs", "/swagger-ui.html").permitAll()
        }

        return http.build()
    }
}