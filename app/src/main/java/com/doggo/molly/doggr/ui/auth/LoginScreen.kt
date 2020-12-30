package com.doggo.molly.doggr.ui.auth

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.viewModel

@Composable
fun Login() {
    val viewModel: LoginViewModel = viewModel()
    Text(text = "Login screen")
}

@Preview
@Composable
fun LoginPreview() {
    Login()
}
