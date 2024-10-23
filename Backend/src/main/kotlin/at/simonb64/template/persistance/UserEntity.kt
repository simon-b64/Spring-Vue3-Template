package at.simonb64.template.persistance

import jakarta.persistence.*
import java.util.*

@Entity(name = "usertable")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(nullable = false, unique = true)
    var userId: String,
    @Column
    var name: String?,
    @Column
    var email: String?
) {
}
