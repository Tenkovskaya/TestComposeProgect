package com.tenkovskaya.testcomposeprogect.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tenkovskaya.testcomposeprogect.data.BetRepository

class MainViewModelFactory(private val betRepository: BetRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(betRepository) as T
        }
        throw IllegalArgumentException("Unable to construct viewModel")
    }
}

