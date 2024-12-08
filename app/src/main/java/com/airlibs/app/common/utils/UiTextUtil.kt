package com.airlibs.app.common.utils

import android.content.Context
import com.airlibs.domain.models.data.utils.UiText
fun UiText.asString(context: Context): String {
    return when (this) {
        is UiText.DynamicString -> value
        is UiText.StringResource -> context.getString(id, args)
    }
}