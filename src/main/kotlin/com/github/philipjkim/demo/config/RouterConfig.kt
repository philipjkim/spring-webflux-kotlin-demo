package com.github.philipjkim.demo.config

import com.github.philipjkim.demo.handler.BasicHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.function.server.coRouter

@Configuration
@EnableWebFlux
class RouterConfig(private val basicHandler: BasicHandler) {
    @Bean
    fun basicRouter() = coRouter {
        (accept(MediaType.APPLICATION_JSON) and "/api").nest {
            GET("/ping", basicHandler::ping)
        }
    }
}
