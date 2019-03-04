package com.nair.kotlinreferenceapp.cassandra

import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*



@Table(value = "videos")
data class Video(
        @PrimaryKey @Column(value = "videoid") var videoId: UUID,
        @Column(value = "videoname") var videoName: String,
        @Column(value = "description") var description: String
)

@Repository
open interface VideoRepository : CassandraRepository<Video, UUID> {
}

@Component
class VideoService (var videoRepository: VideoRepository) {
    fun findById(id: UUID): Optional<Video> {
        return videoRepository.findById(id)
    }

    fun findAll(): List<Video> {
        return videoRepository.findAll()
    }
}