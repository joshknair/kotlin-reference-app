package com.nair.kotlinreferenceapp.cassandra


import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nair.kotlinreferenceapp.common.Error
import com.nair.kotlinreferenceapp.common.ErrorResponse
import com.nair.kotlinreferenceapp.postgresimpl.BookService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.util.*

@Component
class VideoRequestHandler(val service: VideoService) {
    val objectMapper = jacksonObjectMapper()


    fun getOne(req: ServerRequest): Mono<ServerResponse> {
        val body = req.bodyToMono(String::class.java)
        val errors = validate(req)
        if (errors.size > 0) {
            val errorResponse = ErrorResponse("Errors found", errors)
            return ServerResponse.ok().body(BodyInserters.fromObject(errorResponse))
        } else {
            val id = req.pathVariable("id")
            val video = service.findById(UUID.fromString(id));
            return ServerResponse.ok().body(BodyInserters.fromObject(video))
        }
    }


    fun getAll(req: ServerRequest): Mono<ServerResponse> {
        val body = req.bodyToMono(String::class.java)
        val errors = validate(req)
        if (errors.size > 0) {
            val errorResponse = ErrorResponse("Errors found", errors)
            return ServerResponse.ok().body(BodyInserters.fromObject(errorResponse))
        } else {
            val videos = service.findAll();
            return ServerResponse.ok().body(BodyInserters.fromObject(videos))
        }
    }


    fun validate(req: ServerRequest): List<Error> {
        val errors = emptyList<Error>()

        return errors
    }


}