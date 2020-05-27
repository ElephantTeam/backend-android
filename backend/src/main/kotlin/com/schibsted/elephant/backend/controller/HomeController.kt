package com.schibsted.elephant.backend.controller

import com.schibsted.elephant.backend.elephant
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @RequestMapping("/")
    fun getHomePage(): String {
        return elephant
    }
}