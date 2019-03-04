package com.nair.kotlinreferenceapp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(basePackages = arrayOf("com.nair.kotlinreferenceapp.postgresimpl"))
@EnableReactiveCassandraRepositories(basePackages = arrayOf("com.nair.kotlinreferenceapp.cassandra"))
@EnableCassandraRepositories(basePackages = arrayOf("com.nair.kotlinreferenceapp.cassandra"))
interface Configuration { }

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	SpringApplication.run(Application::class.java, *args)
}
