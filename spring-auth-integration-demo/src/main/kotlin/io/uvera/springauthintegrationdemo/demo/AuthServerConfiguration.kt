package io.uvera.springauthintegrationdemo.demo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpResponse
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.DefaultResponseErrorHandler
import org.springframework.web.client.RestTemplate
import javax.validation.constraints.NotBlank

@Configuration
class AuthServerConfiguration(
    private val restTemplateBuilder: RestTemplateBuilder,
    private val authProps: AuthServerProperties,
) {

    @Bean
    fun restTemplate(): RestTemplate = restTemplateBuilder.rootUri("${authProps.protocol}://${authProps.hostName}:${authProps.port}")
        .requestFactory { HttpComponentsClientHttpRequestFactory() }
        .errorHandler(object : DefaultResponseErrorHandler() {
            override fun hasError(response: ClientHttpResponse): Boolean {
                return response.statusCode.series() == HttpStatus.Series.SERVER_ERROR
            }
        })
        .messageConverters(
            MappingJackson2HttpMessageConverter().also {
                it.objectMapper = ObjectMapper().apply {
                    registerModule(kotlinModule())
                }
            }
        )
        .build()
}

@ConfigurationProperties("auth.server")
@ConstructorBinding
class AuthServerProperties(
    @field:NotBlank val hostName: String,
    @field:NotBlank val port: String,
    @field:NotBlank val protocol: String,
)
