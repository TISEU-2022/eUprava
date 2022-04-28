package io.uvera.springauthintegrationdemo.demo

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.servlet.CorsDsl
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.client.RestTemplate
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import javax.servlet.http.HttpServletRequest

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration(
    private val jwtAuthEntryPoint: JwtAuthEntryPoint,
    private val restTemplate: RestTemplate,
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http {
            cors {
                configurationSource {
                    corsConfiguration {
                        allowedOrigins = listOf("*")
                        allowedHeaders = listOf("*")
                        allowedMethods = listOf(
                            HttpMethod.GET,
                            HttpMethod.HEAD,
                            HttpMethod.POST,
                            HttpMethod.DELETE,
                            HttpMethod.PUT,
                            HttpMethod.OPTIONS
                        ).map(HttpMethod::toString)
                    }
                }
            }
            csrf { disable() }
            httpBasic { disable() }
            logout { disable() }
            authorizeRequests {
                authorize(anyRequest, authenticated)
            }
            exceptionHandling {
                authenticationEntryPoint = jwtAuthEntryPoint
            }
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
            // idk why but with latest spring some cyclic dependency stuff is going on so we're directly injecting filter
            addFilterBefore<UsernamePasswordAuthenticationFilter>(JwtAuthFilter(restTemplate))
        }
    }
}

inline fun CorsDsl.configurationSource(crossinline block: (HttpServletRequest) -> CorsConfiguration?) {
    this.configurationSource = CorsConfigurationSource {
        block(it)
    }
}

inline fun corsConfiguration(block: CorsConfiguration.() -> Unit) =
    CorsConfiguration().apply {
        block()
    }
