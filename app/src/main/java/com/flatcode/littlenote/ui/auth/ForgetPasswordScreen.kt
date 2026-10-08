package com.flatcode.littlenote.ui.auth

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.flatcode.littlenote.ui.theme.AppIcons
import com.flatcode.littlenote.ui.theme.Strings
import com.flatcode.littlenote.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgetPasswordScreen(
    authViewModel: AuthViewModel,
    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }

    val authStatus by authViewModel.authStatus.collectAsState()

    LaunchedEffect(authStatus) {
        when (authStatus) {
            is AuthViewModel.AuthResult.Success -> {
                Toast.makeText(context, (authStatus as AuthViewModel.AuthResult.Success).message, Toast.LENGTH_SHORT).show()
            }
            is AuthViewModel.AuthResult.Error -> {
                Toast.makeText(context, (authStatus as AuthViewModel.AuthResult.Error).message, Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = Strings.CD_RESET_PASSWORD) }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = Strings.EMAIL) },
                leadingIcon = { Icon(imageVector = AppIcons.Email, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (email.isBlank()) {
                        Toast.makeText(context, "Enter email...!", Toast.LENGTH_SHORT).show()
                    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Toast.makeText(context, "Invalid email format...!", Toast.LENGTH_SHORT).show()
                    } else {
                        authViewModel.resetPassword(email)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = Strings.CD_RESET_PASSWORD)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onNavigateToLogin) {
                Text(text = Strings.LOGIN)
            }

            TextButton(onClick = onNavigateToRegister) {
                Text(text = Strings.NEW_USER_SIGNUP)
            }
        }
    }
}