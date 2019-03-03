package com.nair.kotlinreferenceapp

import com.nair.kotlinreferenceapp.relationalimpl.RelationalRequestHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router


@Configuration
class Route(val relationalRequestHandler: RelationalRequestHandler) {

    @Bean
    open fun MovieRoutes() = router {
        "/relationalimpl/movies/".nest {
            GET("{id}", relationalRequestHandler::getOne)
        }
    }
}