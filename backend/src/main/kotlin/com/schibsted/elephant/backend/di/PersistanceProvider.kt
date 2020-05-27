package com.schibsted.elephant.backend.di

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource

class UserDao(val jdbcTemplate: JdbcTemplate) {
    fun insertUser() {
        val asd = jdbcTemplate
        println("insertingUser")
    }
}

@Configuration
class PersistanceProvider {

    @Bean
    fun provideUserDao(jdbcTemplate: JdbcTemplate): UserDao = UserDao(jdbcTemplate)

    @Bean
    fun provideJdbcTemplate(dataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }

    @Bean
    fun provideNamedParameterJdbcTemplate(dataSource: DataSource): NamedParameterJdbcTemplate {
        return NamedParameterJdbcTemplate(dataSource)
    }

    @Bean
    fun mysqlDataSource(
 /*       @Value("\${db.name}") dbName: String,
        @Value("\${db.username}") username: String,
        @Value("\${db.password}") password: String,
        @Value("\${db.host}") host: String,
        @Value("\${db.port}") port: String*/
    ): DataSource {
        /*  val MYSQL_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver"
          val MYSQL_URL = "jdbc:mysql://$host:$port/$dbName"
  
          val dataSource = DriverManagerDataSource()
          dataSource.setDriverClassName(MYSQL_DRIVER_CLASS_NAME)
          dataSource.url = MYSQL_URL
          dataSource.username = username
          dataSource.password = password*/

        val MYSQL_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver"
        val MYSQL_URL = "jdbc:mysql://localhost:3306/ELEPHANTDB"

        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName(MYSQL_DRIVER_CLASS_NAME)
        dataSource.url = MYSQL_URL
        dataSource.username = "usern"
        dataSource.password = "pasw"
        return dataSource
    }
}
