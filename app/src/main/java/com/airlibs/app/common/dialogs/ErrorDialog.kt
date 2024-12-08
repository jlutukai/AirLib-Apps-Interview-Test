package com.airlibs.app.common.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airlibs.app.R

@Composable
fun ErrorDialog(
    title: String = stringResource(id = R.string.error),
    message: String,
    onDismiss: () -> Unit,
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    // show dialog
    AlertDialogExample(
        onDismissRequest = {
            openAlertDialog.value = false
            onDismiss()
        },
        onConfirmation = {
            openAlertDialog.value = false
            onDismiss()
        },
        dialogTitle = title,
        dialogText = message,
        icon = Icons.Default.Error,
        iconColor = MaterialTheme.colorScheme.error
    )
}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
    iconColor: Color
) {
    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        icon = {
            Icon(
                icon,
                contentDescription = "Example Icon",
                tint = iconColor,
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
            )
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Ok")
            }
        }
    )
}

