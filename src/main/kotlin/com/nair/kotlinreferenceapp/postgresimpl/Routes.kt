package com.nair.kotlinreferenceapp.postgresimpl

import com.nair.kotlinreferenceapp.postgresimpl.RelationalRequestHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router


@Configuration
class RelationalRoute(val relationalRequestHandler: RelationalRequestHandler) {

    @Bean
    open fun relationalRoutes() = router {
        "/postgresimpl/movies".nest {
            GET("", relationalRequestHandler::getAll)
            GET("/{id}", relationalRequestHandler::getOne)
        }
    }
}