package com.nair.kotlinreferenceapp.postgresimpl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nair.kotlinreferenceapp.common.Error
import com.nair.kotlinreferenceapp.common.ErrorResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class RelationalRequestHandler(val bookService: BookService) {
    val objectMapper = jacksonObjectMapper()

    fun health(req: ServerRequest): Mono<ServerResponse> {
        val resp = objectMapper.createObjectNode()
        resp.put("status", "UP")
        return ServerResponse.ok().body(BodyInserters.fromObject(resp))
    }

    fun getOne(req: ServerRequest): Mono<ServerResponse> {
        val body = req.bodyToMono(String::class.java)
        val errors = validate(req)
        if (errors.size > 0) {
            val errorResponse = ErrorResponse("Errors found", errors)
            return ServerResponse.ok().body(BodyInserters.fromObject(errorResponse))
        }else {
            val id = req.pathVariable("id")
            val movie = bookService.findById(id);
            return ServerResponse.ok().body(BodyInserters.fromObject(movie))
        }
    }


    fun getAll(req: ServerRequest): Mono<ServerResponse> {
        val body = req.bodyToMono(String::class.java)
        val errors = validate(req)
        if (errors.size > 0) {
            val errorResponse = ErrorResponse("Errors found", errors)
            return ServerResponse.ok().body(BodyInserters.fromObject(errorResponse))
        }else {
            val movies = bookService.findAll();
            return ServerResponse.ok().body(BodyInserters.fromObject(movies))
        }
    }


    fun validate(req: ServerRequest) : List<Error> {
        val errors = emptyList<Error>()

        return errors
    }


}