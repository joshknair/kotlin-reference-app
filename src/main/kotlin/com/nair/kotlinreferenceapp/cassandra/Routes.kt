package com.nair.kotlinreferenceapp.cassandra

import com.nair.kotlinreferenceapp.postgresimpl.RelationalRequestHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class CassandraRoute(val videoRequestHandler: VideoRequestHandler) {

    @Bean
    open fun cassandraRoutes() = router {
        "/cassandraimpl/videos".nest {
            GET("", videoRequestHandler::getAll)
            GET("/{id}", videoRequestHandler::getOne)
        }
    }
}