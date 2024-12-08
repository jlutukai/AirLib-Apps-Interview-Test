package com.airlibs.app.features.login.models

import com.airlibs.app.R
import com.airlibs.app.common.utils.InputWrapper

data class LoginFormState(
    val userName: InputWrapper = InputWrapper(
        label = R.string.enter_your_email
    ),
    val password: InputWrapper = InputWrapper(
        label = R.string.enter_your_password
    )
)
