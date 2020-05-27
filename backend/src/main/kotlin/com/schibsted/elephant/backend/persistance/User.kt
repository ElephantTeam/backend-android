package com.schibsted.elephant.backend.persistance

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class User(
        private val name: String,
        @Id private val uuid: String,
        private val token: String
)