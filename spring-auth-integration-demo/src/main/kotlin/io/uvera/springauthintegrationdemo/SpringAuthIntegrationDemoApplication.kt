package io.uvera.springauthintegrationdemo

import io.uvera.springauthintegrationdemo.demo.ConfigurationPropertiesMarkerInterface
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackageClasses = [ConfigurationPropertiesMarkerInterface::class])
@SpringBootApplication
class SpringAuthIntegrationDemoApplication

fun main(args: Array<String>) {
    runApplication<SpringAuthIntegrationDemoApplication>(*args)
}
