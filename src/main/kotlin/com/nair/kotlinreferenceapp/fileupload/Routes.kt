package com.nair.kotlinreferenceapp.fileupload

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.micrometer.core.annotation.Timed
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.codec.multipart.FilePart
import org.springframework.http.codec.multipart.Part
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyExtractor
import org.springframework.web.reactive.function.BodyExtractors
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import java.io.File


@Configuration
class FileUploadRoutes(val fileUploadHandler: FileUploadHandler) {

    @Bean
    fun fileUpload() = router {
        "/files/upload".and(RequestPredicates.accept(MediaType.MULTIPART_FORM_DATA)).nest {
            POST("", fileUploadHandler::upload)
        }
    }
}

@Component
class FileUploadHandler {

    @Timed("kotlin_referece_app_file_upload",
            extraTags = arrayOf("application", "kotlin_referece_app"),
            percentiles = doubleArrayOf(0.5, 0.95, 0.99))
    fun upload(req: ServerRequest): Mono<ServerResponse> {
        val objectMapper = jacksonObjectMapper()
        val resp = objectMapper.createObjectNode()
        resp.put("status", "SUCCESSFUL")

        var parts = req.body(BodyExtractors.toMultipartData()).toMono()

        return parts.flatMap { it ->
            val map: Map<String, Part> = it.toSingleValueMap()
            val filePart : FilePart = map["file"]!! as FilePart
            // Note cast to "FilePart" above

            // Save file to disk - in this example, in the "tmp" folder of a *nix system
            val fileName = filePart.filename()
            filePart.transferTo( File("/tmp/$fileName"))

            ServerResponse.ok().body(BodyInserters.fromObject("OK"))
        }

    }
}