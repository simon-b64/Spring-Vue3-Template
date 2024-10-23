package at.simonb64.template.exception

import org.springframework.http.HttpStatus

class MappedNotFoundException(
    message: String?
) : MappedBackendException(message, HttpStatus.NOT_FOUND) {

}