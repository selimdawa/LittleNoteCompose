package com.flatcode.littlenotecompose.ui.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flatcode.littlenotecompose.ui.components.LoginWarningDialog
import com.flatcode.littlenotecompose.ui.theme.AppIcons
import com.flatcode.littlenotecompose.ui.theme.Black
import com.flatcode.littlenotecompose.ui.theme.Dimen
import com.flatcode.littlenotecompose.ui.theme.Strings
import com.flatcode.littlenotecompose.ui.theme.White
import com.flatcode.littlenotecompose.utils.DATA
import com.flatcode.littlenotecompose.utils.DATA.COLOR_ERROR
import com.flatcode.littlenotecompose.utils.DATA.MC_TRACK
import com.flatcode.littlenotecompose.utils.noRippleClickable
import com.flatcode.littlenotecompose.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onNavigateToHome: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgetPassword: () -> Unit
) {
    val context = LocalContext.current
    var showWarning by remember { mutableStateOf(true) }

    val authStatus by authViewModel.authStatus.collectAsState()

    if (showWarning) {
        LoginWarningDialog(
            onSaveNotes = {
                showWarning = false
                onNavigateToRegister()
            },
            onContinue = { showWarning = false },
            onDismiss = { showWarning = false }
        )
    }

    LaunchedEffect(authStatus) {
        when (authStatus) {
            is AuthViewModel.AuthResult.Success -> {
                Toast.makeText(context, (authStatus as AuthViewModel.AuthResult.Success).message, Toast.LENGTH_SHORT).show()
                onNavigateToHome()
            }
            is AuthViewModel.AuthResult.Error -> {
                Toast.makeText(context, (authStatus as AuthViewModel.AuthResult.Error).message, Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    LoginContent(
        onNavigateToRegister = onNavigateToRegister,
        onNavigateToForgetPassword = onNavigateToForgetPassword,
        onSignIn = { email, password ->
            authViewModel.signIn(email, password)
        }
    )
}

@Composable
fun LoginContent(
    onNavigateToRegister: () -> Unit = {},
    onNavigateToForgetPassword: () -> Unit = {},
    onSignIn: (String, String) -> Unit = { _, _ -> }
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DATA.COLOR_ON_BACKGROUND)
    ) {
        Image(
            painter = painterResource(id = AppIcons.Background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimen.SPACING_20),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = AppIcons.Logo),
                    contentDescription = null,
                    modifier = Modifier.size(Dimen.LOGO_SIZE)
                )

                Spacer(modifier = Modifier.height(Dimen.SPACING_10))

                Text(
                    text = Strings.APP_NAME,
                    color = COLOR_ERROR,
                    fontSize = Dimen.TEXT_SIZE_21,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxWidth()
                    .padding(Dimen.SPACING_15)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.padding(Dimen.SPACING_10),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = AppIcons.Email,
                        contentDescription = null,
                        tint = COLOR_ERROR
                    )
                    Spacer(modifier = Modifier.width(Dimen.SPACING_5))
                    Text(
                        text = Strings.EMAIL,
                        color = COLOR_ERROR,
                        fontSize = Dimen.TEXT_SIZE_14,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            color = DATA.COLOR_ON_BACKGROUND,
                            shape = RoundedCornerShape(Dimen.CARD_CORNER_RADIUS_MEDIUM)
                        )
                        .border(
                            width = 2.dp,
                            color = MC_TRACK,
                            shape = RoundedCornerShape(Dimen.CARD_CORNER_RADIUS_MEDIUM)
                        )
                        .padding(horizontal = Dimen.SPACING_15),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (email.isEmpty()) {
                        Text(
                            text = Strings.EMAIL_EXAMPLE,
                            color = COLOR_ERROR.copy(alpha = 0.5f),
                            fontSize = Dimen.TEXT_SIZE_14,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    BasicTextField(
                        value = email,
                        onValueChange = { email = it },
                        singleLine = true,
                        textStyle = TextStyle(
                            color = COLOR_ERROR,
                            fontSize = Dimen.TEXT_SIZE_14,
                            fontWeight = FontWeight.Normal
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(Dimen.SPACING_20))

                Row(
                    modifier = Modifier.padding(Dimen.SPACING_10),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = AppIcons.Lock,
                        contentDescription = null,
                        tint = COLOR_ERROR
                    )
                    Spacer(modifier = Modifier.width(Dimen.SPACING_5))
                    Text(
                        text = Strings.PASSWORD,
                        color = COLOR_ERROR,
                        fontSize = Dimen.TEXT_SIZE_14,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            color = DATA.COLOR_ON_BACKGROUND,
                            shape = RoundedCornerShape(Dimen.CARD_CORNER_RADIUS_MEDIUM)
                        )
                        .border(
                            width = 2.dp,
                            color = MC_TRACK,
                            shape = RoundedCornerShape(Dimen.CARD_CORNER_RADIUS_MEDIUM)
                        )
                        .padding(horizontal = Dimen.SPACING_15),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (password.isEmpty()) {
                        Text(
                            text = Strings.PASSWORD_HINT,
                            color = COLOR_ERROR.copy(alpha = 0.5f),
                            fontSize = Dimen.TEXT_SIZE_14,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BasicTextField(
                            value = password,
                            onValueChange = { password = it },
                            singleLine = true,
                            textStyle = TextStyle(
                                color = COLOR_ERROR,
                                fontSize = Dimen.TEXT_SIZE_14,
                                fontWeight = FontWeight.Normal
                            ),
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            modifier = Modifier.weight(1f)
                        )

                        Spacer(modifier = Modifier.width(Dimen.SPACING_5))

                        Icon(
                            imageVector = if (passwordVisible) AppIcons.Visibility else AppIcons.VisibilityOff,
                            contentDescription = "Toggle Password Visibility",
                            tint = COLOR_ERROR,
                            modifier = Modifier
                                .size(24.dp)
                                .noRippleClickable { passwordVisible = !passwordVisible }
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Dimen.SPACING_15),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = MC_TRACK,
                                shape = RoundedCornerShape(Dimen.CARD_CORNER_RADIUS_MEDIUM)
                            )
                            .noRippleClickable { onNavigateToRegister() }
                            .padding(Dimen.SPACING_10),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = Strings.NEW_USER_SIGNUP,
                            color = White,
                            fontSize = Dimen.TEXT_SIZE_14,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }

                    Box(
                        modifier = Modifier
                            .background(
                                color = Black,
                                shape = RoundedCornerShape(Dimen.CARD_CORNER_RADIUS_MEDIUM)
                            )
                            .noRippleClickable { onNavigateToForgetPassword() }
                            .padding(Dimen.SPACING_10),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = Strings.FORGET_PASSWORD,
                            color = White,
                            fontSize = Dimen.TEXT_SIZE_14,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Dimen.SPACING_20),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(Dimen.BUTTON_SIZE_LARGE)
                        .background(color = MC_TRACK, shape = RoundedCornerShape(30.dp))
                        .noRippleClickable {
                            if (email.isBlank() || password.isBlank()) {
                                Toast.makeText(context, Strings.EMPTY_REQUIRED, Toast.LENGTH_SHORT).show()
                            } else {
                                onSignIn(email, password)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = AppIcons.ArrowBack,
                        contentDescription = Strings.CD_LOGIN,
                        tint = White,
                        modifier = Modifier
                            .size(30.dp)
                            .graphicsLayer(rotationZ = 180f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun LoginScreenPreview() {
    LoginContent()
}