package at.simonb64.template.persistance.repository

import at.simonb64.template.persistance.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<UserEntity, UUID> {
    fun findByUserId(userId: String): UserEntity?
}
