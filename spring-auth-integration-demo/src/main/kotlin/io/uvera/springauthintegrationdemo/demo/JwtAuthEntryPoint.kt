package io.uvera.springauthintegrationdemo.demo

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException,
    ) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = MediaType.APPLICATION_JSON_VALUE

        val ex = request.getAttribute("exception") as? Exception ?: authException

        val body: ByteArray = ObjectMapper().writeValueAsBytes(
            ApiError.fromException(ex, request, HttpStatus.UNAUTHORIZED)
        )

        response.outputStream.write(body)
    }
}
