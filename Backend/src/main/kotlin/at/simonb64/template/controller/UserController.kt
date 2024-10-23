package at.simonb64.template.controller

import at.simonb64.template.controller.dto.UserDto
import at.simonb64.template.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/user")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun findAllUsers(): List<UserDto> {
        return userService.findAll()
    }

}
