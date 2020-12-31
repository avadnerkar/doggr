package com.doggo.molly.doggr.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.doggo.molly.doggr.data.model.Dog
import com.doggo.molly.doggr.ui.adddog.AddDog
import com.doggo.molly.doggr.ui.auth.AuthViewModel
import com.doggo.molly.doggr.ui.auth.AuthedComposable

@Composable
fun Home() {
    AuthedComposable {
        val viewModel: HomeViewModel = viewModel()
        val viewState by viewModel.state.collectAsState()
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                item {
                    AddDog()
                }

                items(viewState.dogs) { dog ->
                    DogItem(dog = dog)
                }
            }
            LogoutButton()
        }
    }
}

@Composable
fun DogItem(dog: Dog) {
    Card(modifier = Modifier.padding(4.dp), backgroundColor = MaterialTheme.colors.secondary) {
        Row(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
            Text(text = dog.name.orEmpty(), modifier = Modifier.weight(1f))
            Text(text = dog.breed.orEmpty(), modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun LogoutButton() {
    val viewModel: AuthViewModel = viewModel()
    Button(onClick = { viewModel.logout() }, modifier = Modifier.fillMaxWidth().padding(4.dp)) {
        Text(text = "Logout")
    }
}
