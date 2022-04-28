package io.uvera.springauthintegrationdemo.demo

import org.springframework.http.HttpStatus
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Custom JWT Authentication filter
 *
 * Custom implementation which parses Bearer token from Authorization header
 */
class JwtAuthFilter(
    private val restTemplate: RestTemplate,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        // if no token found, continue the filter chain
        val token = request.extractJwt() ?: return filterChain.doFilter(request, response)
        try {
            // if token is invalid, throw exception
            val authResponse = restTemplate.getForEntity<AuthResponse>("/auth/verify_token/$token")
            if (authResponse.statusCode == HttpStatus.BAD_REQUEST || authResponse.statusCode == HttpStatus.UNAUTHORIZED || authResponse.statusCode == HttpStatus.FORBIDDEN) {
                throw BadCredentialsException("Invalid request to auth server")
            }
            val userDetails: UserDetails = CustomUserDetails(authResponse.body!!.user)
            val secToken = UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.authorities
            )
            SecurityContextHolder.getContext().authentication = secToken
        } catch (ex: Exception) {
            /**
             * Setting attribute so that [JwtAuthEntryPoint]
             * can proceed with error handling
             */
            request.setAttribute("exception", ex)
        }
        filterChain.doFilter(request, response)
    }
}

@Suppress("unused")
class CustomUserDetails(user: MappedUser) : UserDetails {
    val email: String = user.username
    private val password: String? = null
    val active = true
    val authorities: MutableList<GrantedAuthority> =
        user.roles.map { SimpleGrantedAuthority("ROLE_$it") }.toMutableList()
    val firstName: String = user.firstName
    val lastName: String = user.lastName
    val identityNumber: String = user.identityNumber

    override fun getUsername(): String = this.email

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = this.authorities

    override fun getPassword(): String? = this.password

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = active
}

private fun HttpServletRequest.extractJwt(): String? {
    // Return null if authorization header is null
    val authorizationHeader: String = this.getHeader("Authorization") ?: return null
    // We need to check if header is in Bearer {token} form
    return if (authorizationHeader.startsWith("Bearer ")) {
        authorizationHeader.substring(7, authorizationHeader.length)
    } else null
}

class AuthResponse(
    val user: MappedUser,
)

class MappedUser(
    val _id: String,
    val firstName: String,
    val lastName: String,
    val username: String,
    val identityNumber: String,
    val roles: List<String>,
)
