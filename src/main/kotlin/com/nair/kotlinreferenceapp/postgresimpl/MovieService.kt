package com.nair.kotlinreferenceapp.postgresimpl

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "movies")
data class Movie(
        @Id @Column(name = "id") var id: String,
        @Column(name = "title_name") var title: String,
        @Column(name = "update_timestamp") var updateTimestamp: Instant
)

interface BooksRepository : JpaRepository<Movie, String> {

}

@Service
class BookService(val booksRepository: BooksRepository) {
    fun save(movie: Movie) {
        booksRepository.save(movie)
    }

    fun findById(id: String): Optional<Movie> {
        return booksRepository.findById(id)
    }

    fun findAll(): List<Movie> {
        return booksRepository.findAll() as List<Movie>
    }


}