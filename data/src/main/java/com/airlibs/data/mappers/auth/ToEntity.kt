package com.airlibs.data.mappers.auth

import com.airlibs.data.models.entities.UserDetailsEntity
import com.airlibs.domain.models.data.UserDetails


fun UserDetails.toEntity(): UserDetailsEntity = UserDetailsEntity(
    email = email
)