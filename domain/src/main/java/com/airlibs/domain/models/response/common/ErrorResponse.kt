package com.airlibs.domain.models.response.common

import com.airlibs.domain.models.dtos.common.ErrorDto

data class ErrorResponse(
    val errors: List<ErrorDto>
)
