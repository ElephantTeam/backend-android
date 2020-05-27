package com.schibsted.elephant.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ElephantBackendApplication

fun main(args: Array<String>) {
	runApplication<ElephantBackendApplication>(*args)
}
