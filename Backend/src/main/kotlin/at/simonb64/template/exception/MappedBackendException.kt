package at.simonb64.template.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
open class MappedBackendException(
    message: String?,
    val status: HttpStatus,
) : RuntimeException(message) {

}