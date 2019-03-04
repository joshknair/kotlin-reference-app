package com.nair.kotlinreferenceapp.common

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

@Configuration
class HealthRoute(val handler: ApplicationHealthHandler) {

    @Bean
    open fun healthRoutes() = router {
        "/health".nest {
            GET("", handler::health)
        }
    }
}

@Component
class ApplicationHealthHandler {
    val objectMapper = jacksonObjectMapper()
    fun health(req: ServerRequest): Mono<ServerResponse> {
        val resp = objectMapper.createObjectNode()
        resp.put("status", "UP")
        return ServerResponse.ok().body(BodyInserters.fromObject(resp))
    }
}