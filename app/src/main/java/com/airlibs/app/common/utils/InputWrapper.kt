package com.airlibs.app.common.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf

@Stable
data class InputWrapper(
    val inputValue: MutableState<String> = mutableStateOf(""),
    var hasError: Boolean = false,
    @StringRes var validationMessage: Int? = null,
    @StringRes val label: Int,

    ) {
    fun onValueChanged(input: String) {
        validationMessage = null
        inputValue.value = input
    }

}
