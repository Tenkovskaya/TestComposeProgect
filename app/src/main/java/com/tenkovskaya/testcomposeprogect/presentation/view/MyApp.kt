package com.tenkovskaya.testcomposeprogect.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.tenkovskaya.testcomposeprogect.presentation.viewModel.MainViewModel

@Composable
fun MyApp(mainViewModel: MainViewModel) {
    val selectedTab = mainViewModel.selectedTab.collectAsState().value

    Scaffold(
        bottomBar = { BottomNavigationBar(selectedTab, mainViewModel::setSelectedTab) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            BackgroundImage()
            when (selectedTab) {
                0 -> BettingCalculatorScreen(viewModel = mainViewModel)
                1 -> SystemBetCalculatorScreen(viewModel = mainViewModel)
                2 -> ForkBetCalculatorScreen(viewModel = mainViewModel)
            }
        }
    }
}