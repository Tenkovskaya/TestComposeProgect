package com.tenkovskaya.testcomposeprogect

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertIsDisplayed
import com.tenkovskaya.testcomposeprogect.domain.domainModels.BetResult
import com.tenkovskaya.testcomposeprogect.presentation.view.BettingCalculatorScreen
import com.tenkovskaya.testcomposeprogect.presentation.viewModel.MainViewModel
import org.junit.Rule
import org.junit.Test

//class BettingCalculatorScreenTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @Test
//    fun whenInputIsValid_DisplayCorrectResult() {
//        // Arrange
//        val fakeViewModel = mock<MainViewModel>()
//        val betAmount = "100"
//        val coefficient = "2.0"
//        val expectedResult = "Coefficient: 8.00, Winning: 800.00 руб."
//
//        // Имитация ожидаемого поведения ViewModel
//        whenever(fakeViewModel.calculateExpressBet(betAmount.toDouble(), coefficient.toDouble()))
//            .thenReturn(BetResult(8.0, 800.0))
//
//        composeTestRule.setContent {
//            BettingCalculatorScreen(fakeViewModel)
//        }
//
//        // Act
//        composeTestRule.onNodeWithText("Bet Amount").performTextInput(betAmount)
//        composeTestRule.onNodeWithText("Coefficient").performTextInput(coefficient)
//        composeTestRule.onNodeWithText("Calculate").performClick()
//
//        // Assert
//        composeTestRule.onNodeWithText(expectedResult).assertIsDisplayed()
//    }
//}
