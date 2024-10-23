package at.simonb64.template.service

import at.simonb64.template.service.model.UserModel
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service


@Service
class SecurityService {

    private fun extractClaim(): Map<String, Any> {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val principal: Any = authentication.principal
        val claims: Map<String, Any> = (principal as Jwt).claims
        return claims
    }

    fun getCurrentUser(): UserModel {
        val claims = extractClaim()
        return UserModel(
            claims["sub"] as String,
            claims["name"] as String,
            claims["email"] as String,
        )
    }

}