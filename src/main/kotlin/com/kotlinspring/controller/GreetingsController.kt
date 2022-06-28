package com.kotlinspring.controller

import com.kotlinspring.service.GreetingsService
import mu.KLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/greetings")
class GreetingsController(val greetingsService: GreetingsService) {

    @GetMapping("/{name}")
    fun retrieveGreeting(@PathVariable("name") name: String) : String =
        greetingsService.retrieveGreeting(name).apply {
            logger.info("Name is $name")
        }

    companion object: KLogging()
}