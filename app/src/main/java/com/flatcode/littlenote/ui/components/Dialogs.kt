package com.flatcode.littlenote.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flatcode.littlenote.ui.theme.Strings

@Composable
fun CloseAppDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = Strings.DO_YOU_WANT_TO_EXIT, fontWeight = FontWeight.Bold) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = Strings.YES)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = Strings.NO)
            }
        }
    )
}

@Composable
fun AboutAccountDialog(
    username: String?,
    email: String?,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = Strings.CD_INFO, fontWeight = FontWeight.Bold) },
        text = {
            Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Text(text = "${Strings.USERNAME}: ${username ?: Strings.USERNAME}", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "${Strings.EMAIL}: ${email ?: Strings.EMAIL}", fontSize = 16.sp)
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = Strings.YES)
            }
        }
    )
}

@Composable
fun AnonymousLogoutWarningDialog(
    onSyncNotes: () -> Unit,
    onLogout: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = Strings.ALERT_DELETE_TITLE, fontWeight = FontWeight.Bold) },
        text = { Text(text = Strings.ALERT_DELETE_MESSAGE) },
        confirmButton = {
            TextButton(onClick = onSyncNotes) {
                Text(text = Strings.ALERT_DELETE_POSITIVE)
            }
        },
        dismissButton = {
            TextButton(onClick = onLogout) {
                Text(text = Strings.ALERT_DELETE_NEGATIVE)
            }
        }
    )
}

@Composable
fun LoginWarningDialog(
    onSaveNotes: () -> Unit,
    onContinue: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = Strings.ALERT_DELETE_TITLE, fontWeight = FontWeight.Bold) },
        text = { Text(text = Strings.ALERT_LOGIN_MESSAGE) },
        confirmButton = {
            TextButton(onClick = onSaveNotes) {
                Text(text = Strings.ALERT_LOGIN_POSITIVE)
            }
        },
        dismissButton = {
            TextButton(onClick = onContinue) {
                Text(text = Strings.ALERT_LOGIN_NEGATIVE)
            }
        }
    )
}