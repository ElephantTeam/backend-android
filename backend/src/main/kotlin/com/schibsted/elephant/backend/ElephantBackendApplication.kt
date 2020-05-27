package com.schibsted.elephant.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.schibsted.elephant.backend.controller")
class ElephantBackendApplication

fun main(args: Array<String>) {
	runApplication<ElephantBackendApplication>(*args)
	println(elephant)
}

const val elephant = " _______  __       _______ .______    __    __       ___      .__   __. .___________.\n" +
	"|   ____||  |     |   ____||   _  \\  |  |  |  |     /   \\     |  \\ |  | |           |\n" +
	"|  |__   |  |     |  |__   |  |_)  | |  |__|  |    /  ^  \\    |   \\|  | `---|  |----`\n" +
	"|   __|  |  |     |   __|  |   ___/  |   __   |   /  /_\\  \\   |  . `  |     |  |     \n" +
	"|  |____ |  `----.|  |____ |  |      |  |  |  |  /  _____  \\  |  |\\   |     |  |     \n" +
	"|_______||_______||_______|| _|      |__|  |__| /__/     \\__\\ |__| \\__|     |__|     \n" +
	"                                                                                     "
