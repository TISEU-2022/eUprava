package io.uvera.springauthintegrationdemo.demo

import org.springframework.http.HttpStatus
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.servlet.http.HttpServletRequest

/**
 * Class representing error returned from this API.
 *
 * This class is used for serializing into json format representing error DTO.
 *
 * @property errors Collection representing multiple errors that occurred during request parsing.
 * @property firstError Property representing first error from [errors].
 * @property timestamp Property representing timestamp when error occurred.
 */
class ApiError(
    val timestamp: Long = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
    val path: String,
    val status: Int,
    val error: String,
    val message: String,
    val errors: Collection<ObjectErrorCompact>,
    val firstError: ObjectErrorCompact? = errors.firstOrNull(),
) {
    /**
     * [Companion] object of [ApiError] holding helper methods.
     */
    companion object {
        /**
         * Convert a [Exception] to instance of [ApiError].
         *
         * @return ApiError instance.
         */
        fun fromException(exception: Exception, request: HttpServletRequest, status: HttpStatus) = ApiError(
            path = request.requestURI,
            status = status.value(),
            error = status.reasonPhrase,
            message = exception.localizedMessage,
            errors = listOf(
                ObjectErrorCompact(
                    defaultMessage = exception.localizedMessage ?: "Unknown error",
                    code = exception::class.simpleName ?: "UnknownException"
                )
            )
        )
    }
}

class ObjectErrorCompact(
    val defaultMessage: String,
    val code: String,
)
