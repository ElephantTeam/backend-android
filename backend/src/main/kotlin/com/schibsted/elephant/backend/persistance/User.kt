package com.schibsted.elephant.backend.persistance

import javax.persistence.Entity
import javax.persistence.Id
import java.io.Serializable

@Entity
data class User(
        val name: String,
        @Id val uuid: String,
        val token: String
): Serializable