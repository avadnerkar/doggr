package com.doggo.molly.doggr.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.doggo.molly.doggr.ui.theme.typography

@Composable
fun Login(
    onLoginClicked: (email: String, password: String) -> Unit,
    onSignupClicked: (email: String, password: String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Welcome back",
            style = typography.h4,
            color = Color.Black
        )

        val username = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            modifier = Modifier.fillMaxWidth()
                .padding(4.dp),
            label = { Text(text = "Username") },
            leadingIcon = { Icon(Icons.Outlined.Person) },
            value = username.value,
            onValueChange = { username.value = it }
        )

        val password = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            modifier = Modifier.fillMaxWidth()
                .padding(4.dp),
            label = { Text(text = "Password") },
            leadingIcon = { Icon(Icons.Outlined.Lock) },
            value = password.value,
            onValueChange = { password.value = it }
        )

        Button(
            modifier = Modifier.fillMaxWidth()
                .padding(4.dp),
            onClick = { onLoginClicked(username.value.text, password.value.text) }
        ) {
            Text(style = typography.subtitle1, color = Color.White, text = "Login")
        }

        Button(
            modifier = Modifier.fillMaxWidth()
                .padding(4.dp),
            onClick = { onSignupClicked(username.value.text, password.value.text) }
        ) {
            Text(style = typography.subtitle1, color = Color.White, text = "Signup")
        }
    }
}

@Composable
fun AuthedComposable(content: @Composable () -> Unit) {
    val viewModel: AuthViewModel = viewModel()
    val viewState: AuthViewState by viewModel.state.collectAsState()
    if (viewState.isAuthenticated) {
        content()
    } else {
        Login(onLoginClicked = viewModel::login, onSignupClicked = viewModel::signup)
    }
}
