package com.doggo.molly.doggr.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doggo.molly.doggr.data.model.Dog
import com.doggo.molly.doggr.data.repository.DogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val dogRepository: DogRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState>
        get() = _state

    init {
        viewModelScope.launch {
            dogRepository.observeDogs().collect {
                _state.value = HomeViewState(dogs = it)
            }
        }
    }
}

data class HomeViewState(val dogs: List<Dog> = emptyList())
