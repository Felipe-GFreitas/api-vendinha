package com.api_vendinha.api.infrastructure.repository

import com.api_vendinha.api.domain.entities.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long>
