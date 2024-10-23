package at.simonb64.template.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtDecoders
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    @Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    val oidcIssuerUri: String?
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests {
            it.anyRequest().authenticated()
        }

        http.oauth2ResourceServer {
            it.jwt { }
        }

        return http.build()
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {
        if(oidcIssuerUri == null || oidcIssuerUri!!.isEmpty()) {
            return JwtDecoder { _: String? -> null }
        }
        return JwtDecoders.fromOidcIssuerLocation(oidcIssuerUri)
    }

}