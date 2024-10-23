package at.simonb64.template.service

import at.simonb64.template.controller.dto.UserDto
import at.simonb64.template.mapper.UserMapper.Companion.toDto
import at.simonb64.template.persistance.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun findAll(): List<UserDto> {
        return userRepository.findAll().map { it.toDto() }
    }

}