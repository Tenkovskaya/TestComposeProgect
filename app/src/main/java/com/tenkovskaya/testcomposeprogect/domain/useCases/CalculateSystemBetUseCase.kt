package com.tenkovskaya.testcomposeprogect.domain.useCases

import com.tenkovskaya.testcomposeprogect.data.BetRepository
import com.tenkovskaya.testcomposeprogect.domain.domainModels.BetResult

open class CalculateSystemBetUseCase(private val betRepository: BetRepository) {
    open operator fun invoke(totalBet: Double, numberOfOutcomes: Int, coefficients: List<Double>): BetResult {
        val winnings = betRepository.calculateSystemBet(totalBet, numberOfOutcomes, coefficients)
        return BetResult(coefficients.fold(1.0) { acc, coeff -> acc * coeff }, winnings)
    }
}