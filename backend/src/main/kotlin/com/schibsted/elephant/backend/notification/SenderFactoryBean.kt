package com.schibsted.elephant.backend.notification

import org.springframework.beans.factory.FactoryBean

class SenderFactoryBean : FactoryBean<Sender> {

    override fun getObject(): Sender = Sender()

    override fun getObjectType() = Sender::class.java

    override fun isSingleton(): Boolean = true
}