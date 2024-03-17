package com.tenkovskaya.testcomposeprogect.domain.useCases

import com.tenkovskaya.testcomposeprogect.data.BetRepository
import com.tenkovskaya.testcomposeprogect.domain.domainModels.ForkResult

open class CalculateForkUseCase(private val betRepository: BetRepository) {
    open operator fun invoke(coeff1: Double, coeff2: Double): ForkResult {
        val isFork = betRepository.calculateFork(coeff1, coeff2)
        return ForkResult(isFork)
    }
}