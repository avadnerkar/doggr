package com.doggo.molly.doggr.ui.adddog

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doggo.molly.doggr.data.model.Dog
import com.doggo.molly.doggr.data.repository.DogRepository
import kotlinx.coroutines.launch

class AddDogViewModel @ViewModelInject constructor(
    private val dogRepository: DogRepository
): ViewModel() {

    fun addDog(name: String, breed: String) {
        viewModelScope.launch {
            dogRepository.addDog(Dog(name = name, breed = breed))
        }
    }
}
