package at.simonb64.template.mapper

import at.simonb64.template.controller.dto.UserDto
import at.simonb64.template.persistance.UserEntity

class UserMapper {
    companion object {
        fun UserEntity.toDto(): UserDto {
            return UserDto(
                this.id,
                this.userId,
                this.name,
                this.email
            )
        }
    }
}
