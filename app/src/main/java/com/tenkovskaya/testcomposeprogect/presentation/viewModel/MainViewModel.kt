package com.tenkovskaya.testcomposeprogect.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.tenkovskaya.testcomposeprogect.data.BetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(private val betRepository: BetRepository) : ViewModel() {
    private val _selectedTab = MutableStateFlow(0)
    val selectedTab = _selectedTab.asStateFlow()

    fun setSelectedTab(index: Int) {
        _selectedTab.value = index
    }

    fun calculateExpressBet(betAmount: Double, coefficient: Double): String {
        return try {
            val winAmount = betRepository.calculateExpressBet(betAmount, coefficient)
            "Coefficient: ${"%.2f".format(coefficient)}, Winning: ${"%.2f".format(winAmount)} руб."
        } catch (e: Exception) {
            "Input error"
        }
    }

    fun calculateSystemBet(totalBet: Double, numberOfOutcomes: Int, coefficients: List<Double>): String {
        return try {
            val totalWin = betRepository.calculateSystemBet(totalBet, numberOfOutcomes, coefficients)
            "General Winning: ${"%.2f".format(totalWin)}"
        } catch (e: Exception) {
            "Input error"
        }
    }

    fun calculateFork(coeff1: Double, coeff2: Double): String {
        return if (betRepository.calculateFork(coeff1, coeff2)) {
            "Fork found: Profitable!"
        } else {
            "No fork: Not profitable."
        }
    }
}

