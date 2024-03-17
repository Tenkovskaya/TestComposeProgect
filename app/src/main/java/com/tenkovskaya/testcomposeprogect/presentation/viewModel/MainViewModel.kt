package com.tenkovskaya.testcomposeprogect.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.tenkovskaya.testcomposeprogect.domain.domainModels.BetResult
import com.tenkovskaya.testcomposeprogect.domain.domainModels.ForkResult
import com.tenkovskaya.testcomposeprogect.domain.useCases.CalculateExpressBetUseCase
import com.tenkovskaya.testcomposeprogect.domain.useCases.CalculateForkUseCase
import com.tenkovskaya.testcomposeprogect.domain.useCases.CalculateSystemBetUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    private val calculateExpressBetUseCase: CalculateExpressBetUseCase,
    private val calculateSystemBetUseCase: CalculateSystemBetUseCase,
    private val calculateForkUseCase: CalculateForkUseCase
) : ViewModel() {
    private val _selectedTab = MutableStateFlow(0)
    val selectedTab = _selectedTab.asStateFlow()

    fun setSelectedTab(index: Int) {
        _selectedTab.value = index
    }

    fun calculateExpressBet(betAmount: Double, coefficient: Double): BetResult {
        return calculateExpressBetUseCase(betAmount, coefficient)
    }

    fun calculateSystemBet(totalBet: Double, numberOfOutcomes: Int, coefficients: List<Double>): BetResult {
        return calculateSystemBetUseCase(totalBet, numberOfOutcomes, coefficients)
    }

    fun calculateFork(coeff1: Double, coeff2: Double): ForkResult {
        return calculateForkUseCase(coeff1, coeff2)
    }
}


