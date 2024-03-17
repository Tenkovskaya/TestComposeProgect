package com.tenkovskaya.testcomposeprogect.data

class BetRepository {

    fun calculateExpressBet(betAmount: Double, coefficient: Double): Double {
        val totalCoeff = coefficient * coefficient * coefficient
        return betAmount * totalCoeff
    }

    fun calculateSystemBet(totalBet: Double, numberOfOutcomes: Int, coefficients: List<Double>): Double {
        val betPerOutcome = totalBet / numberOfOutcomes
        val winningCombinations = coefficients.combinations(numberOfOutcomes)
        var totalWin = 0.0
        for (combination in winningCombinations) {
            totalWin += combination.fold(betPerOutcome) { acc, coeff -> acc * coeff }
        }
        return totalWin
    }

    fun calculateFork(coeff1: Double, coeff2: Double): Boolean {
        return (1 / coeff1 + 1 / coeff2) < 1
    }


    private fun <T> List<T>.combinations(length: Int): List<List<T>> {
        if (length <= 0) return listOf(emptyList())
        if (length >= size) return listOf(this)
        val result = mutableListOf<List<T>>()

        val subList = subList(1, size)
        for (element in this) {
            val subCombos = subList.combinations(length - 1)
            subCombos.forEach { combo ->
                result.add(listOf(element) + combo)
            }
        }
        return result.distinct()
    }
}
