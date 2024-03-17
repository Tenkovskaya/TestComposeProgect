package com.tenkovskaya.testcomposeprogect.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tenkovskaya.testcomposeprogect.data.BetRepository
import com.tenkovskaya.testcomposeprogect.domain.useCases.CalculateExpressBetUseCase
import com.tenkovskaya.testcomposeprogect.domain.useCases.CalculateForkUseCase
import com.tenkovskaya.testcomposeprogect.domain.useCases.CalculateSystemBetUseCase
import com.tenkovskaya.testcomposeprogect.presentation.viewModel.MainViewModel
import com.tenkovskaya.testcomposeprogect.presentation.viewModel.MainViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(BetRepository()))
            MyApp(viewModel)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    val fakeViewModel = MainViewModel(
//        calculateExpressBetUseCase = CalculateExpressBetUseCase(),
//        calculateSystemBetUseCase = CalculateSystemBetUseCase(),
//        calculateForkUseCase = CalculateForkUseCase()
//    )
//    MyApp(fakeViewModel)
//}

