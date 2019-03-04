package com.nair.kotlinreferenceapp.cassandra

import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.util.*



@Table(value = "videos")
data class Video(
        @PrimaryKey @Column(value = "videoid") var videoId: UUID,
        @Column(value = "videoname") var videoName: String,
        @Column(value = "description") var description: String
)

@Repository
open interface VideoRepository : ReactiveCassandraRepository<Video, UUID> {
}

@Component
class VideoService (var videoRepository: VideoRepository) {
    fun findById(id: UUID): Mono<Video> {
        return videoRepository.findById(id)
    }

    fun findAll(): Flux<Video> {
        return videoRepository.findAll()
    }
}