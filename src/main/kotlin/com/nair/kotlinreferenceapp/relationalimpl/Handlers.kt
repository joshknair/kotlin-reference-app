package com.nair.kotlinreferenceapp.relationalimpl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class RelationalRequestHandler() {
    val objectMapper = jacksonObjectMapper()

    fun getOne(req: ServerRequest): Mono<ServerResponse> {
        val body = req.bodyToMono(String::class.java)
        return body.flatMap {
            val resp = objectMapper.createObjectNode()
            resp.put("message", "Welcome to Kotlin")
            ServerResponse.ok().body(BodyInserters.fromObject(resp))
        }
    }

    /*
    fun getAll(req: ServerRequest): Flux<ServerResponse> {
        val body = req.bodyToMono(String::class.java)
        return body.flatMap {
            val resp = objectMapper.createObjectNode()
            resp.put("message", "Welcome to Kotlin")
            ServerResponse.ok().body(Flux.just(resp))
        }
    }
    */

}