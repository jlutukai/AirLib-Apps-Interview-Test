package com.airlibs.data.mappers.auth

import com.airlibs.data.models.entities.UserDetailsEntity
import com.airlibs.domain.models.data.UserDetails

fun UserDetailsEntity.toDomain(): UserDetails = UserDetails(
    email = email
)
