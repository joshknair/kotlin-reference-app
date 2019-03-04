package com.nair.kotlinreferenceapp.cassandra

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.SchemaAction
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories


@Configuration
@EnableCassandraRepositories
class CassandraConfig : AbstractCassandraConfiguration() {
    @Value("\${cassandra.contactpoints}")
    private val contactPoints: String = ""
    @Value("\${cassandra.port}")
    private val port: Int = 0
    @Value("\${cassandra.keyspace}")
    private val keySpace: String = ""
    @Value("\${cassandra.basePackages}")
    private val basePackages: String = ""

    override fun getKeyspaceName(): String {
        return keySpace
    }

    override fun getContactPoints(): String {
        return contactPoints
    }

    override fun getPort(): Int {
        return port
    }

    override fun getSchemaAction(): SchemaAction {
        return SchemaAction.NONE
    }

    override fun getEntityBasePackages(): Array<String> {
        return arrayOf<String>(basePackages)
    }
}