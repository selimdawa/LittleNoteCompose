package com.flatcode.littlenotecompose.ui.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flatcode.littlenotecompose.ui.theme.AppIcons
import com.flatcode.littlenotecompose.ui.theme.Black
import com.flatcode.littlenotecompose.ui.theme.Dimen
import com.flatcode.littlenotecompose.ui.theme.Strings
import com.flatcode.littlenotecompose.ui.theme.White
import com.flatcode.littlenotecompose.utils.DATA
import com.flatcode.littlenotecompose.utils.DATA.COLOR_ERROR
import com.flatcode.littlenotecompose.utils.DATA.MC_TRACK
import com.flatcode.littlenotecompose.viewmodel.AuthViewModel
import com.google.firebase.auth.EmailAuthProvider

@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel,
    onNavigateToHome: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val authStatus by authViewModel.authStatus.collectAsState()

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

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = AppIcons.Background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimen.SPACING_15)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(Dimen.SPACING_20))

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
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(Dimen.SPACING_15))

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = AppIcons.Person, contentDescription = null, tint = COLOR_ERROR)
                    Spacer(modifier = Modifier.size(Dimen.SPACING_5))
                    Text(text = Strings.USERNAME, color = COLOR_ERROR, fontSize = Dimen.TEXT_SIZE_14, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(Dimen.SPACING_5))

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    placeholder = { Text(text = Strings.APP_NAME, color = COLOR_ERROR.copy(alpha = 0.5f)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(Dimen.SPACING_10))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = AppIcons.Email, contentDescription = null, tint = COLOR_ERROR)
                    Spacer(modifier = Modifier.size(Dimen.SPACING_5))
                    Text(text = Strings.EMAIL, color = COLOR_ERROR, fontSize = Dimen.TEXT_SIZE_14, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(Dimen.SPACING_5))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text(text = Strings.EMAIL_EXAMPLE, color = COLOR_ERROR.copy(alpha = 0.5f)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(Dimen.SPACING_10))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = AppIcons.Lock, contentDescription = null, tint = COLOR_ERROR)
                    Spacer(modifier = Modifier.size(Dimen.SPACING_5))
                    Text(text = Strings.PASSWORD, color = COLOR_ERROR, fontSize = Dimen.TEXT_SIZE_14, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(Dimen.SPACING_5))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text(text = Strings.PASSWORD_HINT, color = COLOR_ERROR.copy(alpha = 0.5f)) },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(Dimen.SPACING_10))

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    placeholder = { Text(text = Strings.PASSWORD_HINT, color = COLOR_ERROR.copy(alpha = 0.5f)) },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(Dimen.SPACING_15))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Dimen.SPACING_10)
                ) {
                    Button(
                        onClick = onNavigateToLogin,
                        colors = ButtonDefaults.buttonColors(containerColor = MC_TRACK),
                        shape = RoundedCornerShape(Dimen.CARD_CORNER_RADIUS_MEDIUM),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = Strings.LOGIN, color = White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(containerColor = Black),
                        shape = RoundedCornerShape(Dimen.CARD_CORNER_RADIUS_MEDIUM),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = Strings.FORGET_PASSWORD, color = White, fontSize = 12.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(Dimen.SPACING_20))

            IconButton(
                onClick = {
                    if (email.isBlank() || username.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                        Toast.makeText(context, Strings.EMPTY_REQUIRED_ALL, Toast.LENGTH_SHORT).show()
                    } else if (password != confirmPassword) {
                        Toast.makeText(context, DATA.ERR_PASS, Toast.LENGTH_SHORT).show()
                    } else {
                        val credential = EmailAuthProvider.getCredential(email, password)
                        authViewModel.linkCredential(credential, username)
                    }
                },
                modifier = Modifier
                    .size(60.dp)
                    .background(color = MC_TRACK, shape = RoundedCornerShape(30.dp))
            ) {
                Icon(
                    imageVector = AppIcons.ArrowBack,
                    contentDescription = Strings.CD_REGISTER,
                    tint = White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}