package com.tenkovskaya.testcomposeprogect.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tenkovskaya.testcomposeprogect.data.BetRepository
import com.tenkovskaya.testcomposeprogect.domain.useCases.CalculateExpressBetUseCase
import com.tenkovskaya.testcomposeprogect.domain.useCases.CalculateForkUseCase
import com.tenkovskaya.testcomposeprogect.domain.useCases.CalculateSystemBetUseCase

class MainViewModelFactory(private val betRepository: BetRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                CalculateExpressBetUseCase(betRepository),
                CalculateSystemBetUseCase(betRepository),
                CalculateForkUseCase(betRepository)
            ) as T
        }
        throw IllegalArgumentException("Unable to construct viewModel")
    }
}
