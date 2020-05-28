package com.schibsted.elephant.backend.notification

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SenderFactoryBeanConfiguration {

    private fun senderFactory(): SenderFactoryBean = SenderFactoryBean()

    @Bean
    @Throws(Exception::class)
    fun sender(): Sender {
        return senderFactory().getObject()
    }
}