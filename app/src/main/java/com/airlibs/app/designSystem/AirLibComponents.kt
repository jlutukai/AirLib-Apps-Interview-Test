package com.airlibs.app.designSystem

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.airlibs.app.R
import com.airlibs.app.common.utils.InputWrapper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AirLibTextField(modifier: Modifier = Modifier,
                    value: InputWrapper,
                    onChange: (String) -> Unit,
                    keyBoardOptions: KeyboardOptions = KeyboardOptions.Default,
                    keyboardActions: KeyboardActions = KeyboardActions.Default,
                    visualTransformation: VisualTransformation = VisualTransformation.None,
                    trailingIcon: @Composable (() -> Unit)? = null,
                    leading: @Composable (() -> Unit)? = null,
                    readOnly: Boolean = false,
                    singleLine: Boolean = true,
                    isRequired: Boolean = false,
                    minLines: Int = 1,
                    enabled: Boolean = true,) {
    val interactionSource = remember { MutableInteractionSource() }


    BasicTextField(
        value = value.inputValue.value,
        onValueChange = onChange,
        modifier = modifier
            .padding(0.dp)
            .heightIn(min = 40.dp),
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        keyboardActions = keyboardActions,
        keyboardOptions = keyBoardOptions,
        enabled = enabled,
        minLines = minLines,
        readOnly = readOnly,
        singleLine = singleLine,
    ) { innerTextField ->
        TextFieldDefaults.DecorationBox(
            value = value.inputValue.value,
            visualTransformation = visualTransformation,
            innerTextField = innerTextField,
            singleLine = singleLine,
            enabled = enabled,
            leadingIcon = leading,
            interactionSource = interactionSource,
            label = {
                Text(
                    modifier = Modifier.padding(0.dp),
                    text = if (isRequired) {
                        val annotatedString = buildAnnotatedString {
                            append(stringResource(value.label))

                            withStyle(style = SpanStyle(Color.Red)) {
                                append(" * ")
                            }
                        }
                        annotatedString.text
                    } else {
                        stringResource(value.label)
                    }
                )
            },
            isError = value.hasError,
            contentPadding = PaddingValues(
                horizontal = 8.dp,
                vertical = 8.dp
            ),
            supportingText = {
                if (value.hasError && value.validationMessage != null) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource( value.validationMessage!!),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            trailingIcon = trailingIcon,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(id = R.color.white),
                disabledContainerColor = colorResource(id = R.color.white),
                errorContainerColor = colorResource(id = R.color.white),
                focusedContainerColor = colorResource(id = R.color.white),
            ),
            container = {
                TextFieldDefaults.Container(
                    enabled = enabled,
                    isError = value.hasError,
                    interactionSource = interactionSource,
                    modifier = Modifier.heightIn(min = 40.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = colorResource(id = R.color.white),
                        disabledContainerColor = colorResource(id = R.color.white),
                        errorContainerColor = colorResource(id = R.color.white),
                        focusedContainerColor = colorResource(id = R.color.white),
                    ),
                    shape = TextFieldDefaults.shape,
                    focusedIndicatorLineThickness = TextFieldDefaults.FocusedIndicatorThickness,
                    unfocusedIndicatorLineThickness = TextFieldDefaults.UnfocusedIndicatorThickness,
                )
            }

        )
    }
}

@Composable
fun AirLibPasswordTextField(
    modifier: Modifier = Modifier,
    value: InputWrapper,
    leading: @Composable (() -> Unit)? = null,
    onChange: (String) -> Unit
) {
    val focusManager: FocusManager = LocalFocusManager.current
    val showPassword = remember { mutableStateOf(false) }
    AirLibTextField(
        modifier = modifier,
        value = value,
        leading = leading,
        onChange = onChange,
        keyBoardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        visualTransformation = if (showPassword.value)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        trailingIcon = { PassWordTrailingIcon(showPassword) },
    )
}

@Composable
fun PassWordTrailingIcon(showPassword: MutableState<Boolean>) {
    val (icon, iconColor) = if (showPassword.value) {
        Pair(
            Icons.Outlined.Visibility,
            colorResource(id = R.color.grey_bg)
        )
    } else {
        Pair(Icons.Filled.VisibilityOff, colorResource(id = R.color.black))
    }

    IconButton(
        onClick = { showPassword.value = !showPassword.value }) {
        Icon(
            icon,
            contentDescription = "Visibility",
            tint = iconColor
        )
    }

}