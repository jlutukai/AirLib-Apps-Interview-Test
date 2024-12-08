package com.airlibs.app.features.dashboard.models

import com.airlibs.domain.models.data.utils.UiText

data class GetMedicineUIState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean? = null
)
