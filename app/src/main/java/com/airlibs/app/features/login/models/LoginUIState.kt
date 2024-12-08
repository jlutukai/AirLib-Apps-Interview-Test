package com.airlibs.app.features.login.models

import androidx.compose.runtime.Stable
import com.airlibs.domain.models.data.utils.UiText


data class LoginUIState(
    val userId: Long? = null,
    val error: UiText? = null
)
