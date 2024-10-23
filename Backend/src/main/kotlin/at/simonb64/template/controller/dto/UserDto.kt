package at.simonb64.template.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class UserDto(
    @JsonProperty("id")
    val id: UUID?,
    @JsonProperty("userId")
    val userId: String,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("email")
    val email: String?
)
