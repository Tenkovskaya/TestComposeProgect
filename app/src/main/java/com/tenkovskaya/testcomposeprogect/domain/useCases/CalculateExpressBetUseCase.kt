package com.tenkovskaya.testcomposeprogect.domain.useCases

import com.tenkovskaya.testcomposeprogect.data.BetRepository
import com.tenkovskaya.testcomposeprogect.domain.domainModels.BetResult

open class CalculateExpressBetUseCase(private val betRepository: BetRepository) {
    open operator fun invoke(betAmount: Double, coefficient: Double): BetResult {
        val winnings = betRepository.calculateExpressBet(betAmount, coefficient)
        return BetResult(coefficient * coefficient * coefficient, winnings)
    }
}