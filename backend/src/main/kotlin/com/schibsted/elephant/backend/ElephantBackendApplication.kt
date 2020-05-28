package com.schibsted.elephant.backend

import com.schibsted.elephant.backend.persistance.User
import com.schibsted.elephant.backend.persistance.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(
        "com.schibsted.elephant.backend.controller",
        "com.schibsted.elephant.backend.service",
        "com.schibsted.elephant.backend.notification"
)
class ElephantBackendApplication

private val log: Logger = LoggerFactory.getLogger(ElephantBackendApplication::class.java)

fun main(args: Array<String>) {
    runApplication<ElephantBackendApplication>(*args)
    println(elephant)
}

@Bean
fun demo(userRepository: UserRepository): CommandLineRunner {
    return CommandLineRunner {


        userRepository.save(User("Iwo", "111", "aaa"))

        // fetch all customers
        // fetch all customers
        log.info("Customers found with findAll():")
        log.info("-------------------------------")
        for (customer in userRepository.findAll()) {
            log.info(customer.toString())
            println(customer)
        }
        log.info("")
    }
}

const val elephant = " _______  __       _______ .______    __    __       ___      .__   __. .___________.\n" +
        "|   ____||  |     |   ____||   _  \\  |  |  |  |     /   \\     |  \\ |  | |           |\n" +
        "|  |__   |  |     |  |__   |  |_)  | |  |__|  |    /  ^  \\    |   \\|  | `---|  |----`\n" +
        "|   __|  |  |     |   __|  |   ___/  |   __   |   /  /_\\  \\   |  . `  |     |  |     \n" +
        "|  |____ |  `----.|  |____ |  |      |  |  |  |  /  _____  \\  |  |\\   |     |  |     \n" +
        "|_______||_______||_______|| _|      |__|  |__| /__/     \\__\\ |__| \\__|     |__|     \n" +
        "                                                                                     "
