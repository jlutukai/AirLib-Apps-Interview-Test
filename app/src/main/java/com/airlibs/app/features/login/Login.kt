package com.airlibs.app.features.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airlibs.app.R
import com.airlibs.app.activity.ui.theme.Purple40
import com.airlibs.app.activity.ui.theme.Typography
import com.airlibs.app.common.dialogs.ErrorDialog
import com.airlibs.app.common.utils.asString
import com.airlibs.app.designSystem.AirLibPasswordTextField
import com.airlibs.app.designSystem.AirLibTextField
import com.airlibs.app.features.login.models.LoginFormState


@Composable
fun LoginPage(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val formState by viewModel.loginFormState.collectAsStateWithLifecycle()
    val events = viewModel.events.collectAsStateWithLifecycle(LoginEvents.IdleState)
    val uiState by viewModel.loginUiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.userId) {
        uiState.userId?.let {
            onLoginSuccess()
        }
    }

    when (val event = events.value) {
        is LoginEvents.Error -> {
            ErrorDialog(
                title = event.errorTitle.asString(context),
                message = event.error.asString(context),
                onDismiss = { viewModel.onEvent(LoginEvents.IdleState) }
            )
        }

        else -> {}
    }

    LoginPageContent(
        onEvent = viewModel::onEvent,
        formState = formState,

        )
}

@Composable
fun LoginPageContent(onEvent: (LoginEvents) -> Unit, formState: LoginFormState) {
    val scrollState = rememberScrollState()

    Scaffold { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .background(Purple40)
                .padding(padding)
                .verticalScroll(scrollState)
                .imePadding()
        ) {
            Box(Modifier.weight(1f)) {

            }
            Column(
                Modifier
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 20.dp, start = 10.dp, end = 10.dp)

            ) {
                Text(
                    "Hi, Welcome", style = Typography.titleMedium.copy(
                        color = colorResource(R.color.grey_bg),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(Modifier.size(20.dp))
                AirLibTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = formState.userName,
                    leading = {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = Icons.Default.Mail,
                            contentDescription = stringResource(R.string.email),
                            tint = colorResource(R.color.grey_bg)
                        )
                    },
                    onChange = formState.userName::onValueChanged,
                    keyBoardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                )
                AirLibPasswordTextField(
                    value = formState.password,
                    onChange = formState.password::onValueChanged,
                    leading = {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = Icons.Default.Lock,
                            contentDescription = stringResource(R.string.email),
                            tint = colorResource(R.color.grey_bg)
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    "Forgot Password?", style = Typography.titleMedium.copy(
                        color = Purple40,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(Modifier.weight(1f))

                Row(
                    Modifier
                        .background(color = Purple40, shape = RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 8.dp)
                        .clickable {
                            onEvent(LoginEvents.OnLogIn)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        stringResource(R.string.sign_in), style = Typography.titleMedium.copy(
                            color = Color.White,
                            fontSize = 24.sp
                        )
                    )
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = stringResource(R.string.sign_in),
                        tint = colorResource(R.color.white)
                    )
                }
                Spacer(Modifier.size(15.dp))
            }

        }
    }
}
