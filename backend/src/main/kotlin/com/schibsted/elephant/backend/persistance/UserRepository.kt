package com.schibsted.elephant.backend.persistance

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String>