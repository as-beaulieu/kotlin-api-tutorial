package com.kotlinspring.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GreetingsService {

    @Value("\${message}") //dynamically populate value by reading yaml file, and following the given path
    lateinit var message: String

    fun retrieveGreeting(name: String) = "Hello $name, $message"
}