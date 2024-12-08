package com.airlibs.domain.models.data.utils

import androidx.annotation.StringRes


sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class StringResource(
        @StringRes val id: Int,
        val args: Array<Any> = emptyArray()
    ) : UiText()
}
