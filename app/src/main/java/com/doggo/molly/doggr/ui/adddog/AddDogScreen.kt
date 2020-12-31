package com.doggo.molly.doggr.ui.adddog

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.doggo.molly.doggr.ui.theme.typography

@Composable
fun AddDog() {
    val viewModel: AddDogViewModel = viewModel()
    Column(modifier = Modifier.fillMaxWidth()) {
        val name = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            modifier = Modifier.fillMaxWidth()
                .padding(4.dp),
            label = { Text(text = "Name") },
            leadingIcon = { Icon(Icons.Outlined.Person) },
            value = name.value,
            onValueChange = { name.value = it }
        )

        val breed = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            modifier = Modifier.fillMaxWidth()
                .padding(4.dp),
            label = { Text(text = "Breed") },
            leadingIcon = { Icon(Icons.Outlined.Lock) },
            value = breed.value,
            onValueChange = { breed.value = it }
        )

        Button(
            modifier = Modifier.fillMaxWidth()
                .padding(4.dp),
            onClick = { viewModel.addDog(name.value.text, breed.value.text) }
        ) {
            Text(style = typography.subtitle1, color = Color.White, text = "Add")
        }
    }
}
