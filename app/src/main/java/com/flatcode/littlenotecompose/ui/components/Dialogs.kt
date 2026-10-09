package com.flatcode.littlenotecompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.flatcode.littlenotecompose.ui.theme.Dimen
import com.flatcode.littlenotecompose.ui.theme.Strings
import com.flatcode.littlenotecompose.utils.DATA

@Composable
fun CloseAppDialogContent(
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    Card(
        modifier = Modifier.width(Dimen.DIALOG_WIDTH),
        shape = RoundedCornerShape(Dimen.CARD_CORNER_RADIUS_MEDIUM),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(DATA.MC_BG)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = Strings.DO_YOU_WANT_TO_EXIT,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimen.SPACING_15),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = Dimen.TEXT_SIZE_18,
                    fontWeight = FontWeight.Bold
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimen.DIVIDER_HEIGHT)
                        .background(Color.White)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimen.SPACING_10),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = Dimen.SPACING_5)
                            .clickable { onDismiss() },
                        shape = RoundedCornerShape(Dimen.SPACING_8),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Text(
                            text = Strings.NO,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = Dimen.SPACING_8),
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontSize = Dimen.TEXT_SIZE_18,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = Dimen.SPACING_5)
                            .clickable { onConfirm() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = Strings.YES,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = Dimen.SPACING_8),
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = Dimen.TEXT_SIZE_18,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CloseAppDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        CloseAppDialogContent(
            onConfirm = onConfirm,
            onDismiss = onDismiss
        )
    }
}

@Composable
fun AboutAccountDialogContent(
    username: String?,
    email: String?
) {
    Card(
        modifier = Modifier.width(Dimen.DIALOG_WIDTH),
        shape = RoundedCornerShape(Dimen.CARD_CORNER_RADIUS_LARGE),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(DATA.MC_BG)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = Strings.USERNAME,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimen.SPACING_15),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = Dimen.TEXT_SIZE_18,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = username ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimen.SPACING_15)
                        .padding(bottom = Dimen.SPACING_15),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = Dimen.TEXT_SIZE_16,
                    fontWeight = FontWeight.Normal
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimen.SPACING_10, vertical = Dimen.SPACING_5)
                        .height(Dimen.DIVIDER_HEIGHT)
                        .background(Color.White)
                )

                Text(
                    text = Strings.EMAIL,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimen.SPACING_15),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = Dimen.TEXT_SIZE_18,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = email ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimen.SPACING_15)
                        .padding(bottom = Dimen.SPACING_15),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = Dimen.TEXT_SIZE_16,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@Composable
fun AboutAccountDialog(
    username: String?,
    email: String?,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        AboutAccountDialogContent(
            username = username,
            email = email
        )
    }
}

@Composable
fun AnonymousLogoutWarningDialogContent(
    onSyncNotes: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    Card(
        modifier = Modifier.width(Dimen.DIALOG_WIDTH),
        shape = RoundedCornerShape(Dimen.CARD_CORNER_RADIUS_MEDIUM),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(DATA.MC_BG)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = Strings.ALERT_DELETE_TITLE,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimen.SPACING_15),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = Dimen.TEXT_SIZE_18,
                    fontWeight = FontWeight.Bold
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimen.DIVIDER_HEIGHT)
                        .background(Color.White)
                )

                Text(
                    text = Strings.ALERT_DELETE_MESSAGE,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimen.SPACING_15),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = Dimen.TEXT_SIZE_16
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimen.DIVIDER_HEIGHT)
                        .background(Color.White)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimen.SPACING_10),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = Dimen.SPACING_5)
                            .clickable { onSyncNotes() },
                        shape = RoundedCornerShape(Dimen.SPACING_8),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Text(
                            text = Strings.ALERT_DELETE_POSITIVE,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = Dimen.SPACING_8),
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontSize = Dimen.TEXT_SIZE_16,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = Dimen.SPACING_5)
                            .clickable { onLogout() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = Strings.ALERT_DELETE_NEGATIVE,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = Dimen.SPACING_8),
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = Dimen.TEXT_SIZE_16,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AnonymousLogoutWarningDialog(
    onSyncNotes: () -> Unit,
    onLogout: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        AnonymousLogoutWarningDialogContent(
            onSyncNotes = onSyncNotes,
            onLogout = onLogout
        )
    }
}

@Composable
fun LoginWarningDialogContent(
    onSaveNotes: () -> Unit = {},
    onContinue: () -> Unit = {}
) {
    Card(
        modifier = Modifier.width(Dimen.DIALOG_WIDTH),
        shape = RoundedCornerShape(Dimen.CARD_CORNER_RADIUS_MEDIUM),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(DATA.MC_BG)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = Strings.ALERT_DELETE_TITLE,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimen.SPACING_15),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = Dimen.TEXT_SIZE_18,
                    fontWeight = FontWeight.Bold
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimen.DIVIDER_HEIGHT)
                        .background(Color.White)
                )

                Text(
                    text = Strings.ALERT_LOGIN_MESSAGE,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimen.SPACING_15),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = Dimen.TEXT_SIZE_16
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimen.DIVIDER_HEIGHT)
                        .background(Color.White)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimen.SPACING_10),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = Dimen.SPACING_5)
                            .clickable { onSaveNotes() },
                        shape = RoundedCornerShape(Dimen.SPACING_8),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Text(
                            text = Strings.ALERT_LOGIN_POSITIVE,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = Dimen.SPACING_8),
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontSize = Dimen.TEXT_SIZE_16,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = Dimen.SPACING_5)
                            .clickable { onContinue() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = Strings.ALERT_LOGIN_NEGATIVE,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = Dimen.SPACING_8),
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = Dimen.TEXT_SIZE_16,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoginWarningDialog(
    onSaveNotes: () -> Unit,
    onContinue: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        LoginWarningDialogContent(
            onSaveNotes = onSaveNotes,
            onContinue = onContinue
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CloseAppDialogPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        CloseAppDialogContent()
    }
}

@Preview(showBackground = true)
@Composable
fun AboutAccountDialogPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        AboutAccountDialogContent(
            username = "John Doe",
            email = Strings.EMAIL_EXAMPLE
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnonymousLogoutWarningDialogPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        AnonymousLogoutWarningDialogContent()
    }
}

@Preview(showBackground = true)
@Composable
fun LoginWarningDialogPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        LoginWarningDialogContent()
    }
}