package com.tenkovskaya.testcomposeprogect.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tenkovskaya.testcomposeprogect.R
import com.tenkovskaya.testcomposeprogect.data.BetRepository
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

@Composable
fun BackgroundImage() {
    Image(
        painter = painterResource(id = R.drawable.back),
        contentDescription = "Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun BottomNavigationBar(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    BottomNavigation(backgroundColor = Color.Black, contentColor = Color.White) {
        BottomNavigationItem(
            icon = { Icon(painterResource(id = R.drawable.express), contentDescription = "Express") },
            label = { Text("Express") },
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) }
        )
        BottomNavigationItem(
            icon = { Icon(painterResource(id = R.drawable.system), contentDescription = "System") },
            label = { Text("System") },
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) }
        )
        BottomNavigationItem(
            icon = { Icon(painterResource(id = R.drawable.forks), contentDescription = "Forks") },
            label = { Text("Forks") },
            selected = selectedTab == 2,
            onClick = { onTabSelected(2) }
        )
    }
}

@Composable
fun BettingCalculatorScreen(viewModel: MainViewModel) {
    var betAmount by remember { mutableStateOf("") }
    var coefficient by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = betAmount,
            onValueChange = { betAmount = it },
            label = { Text("Bet Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = coefficient,
            onValueChange = { coefficient = it },
            label = { Text("Coefficient") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { result = viewModel.calculateExpressBet(betAmount.toDoubleOrNull() ?: 0.0, coefficient.toDoubleOrNull() ?: 0.0) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: ${result ?: "Enter values"}")
    }
}


@Composable
fun SystemBetCalculatorScreen(viewModel: MainViewModel) {
    var totalBet by remember { mutableStateOf("") }
    var numberOfOutcomes by remember { mutableStateOf("") }
    var coefficients by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = totalBet,
            onValueChange = { totalBet = it },
            label = { Text("Total Bet Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = numberOfOutcomes,
            onValueChange = { numberOfOutcomes = it },
            label = { Text("Number of Outcomes") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = coefficients,
            onValueChange = { coefficients = it },
            label = { Text("Coefficients (comma separated)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val coeffsList = coefficients.split(",").mapNotNull { it.toDoubleOrNull() }
                result = viewModel.calculateSystemBet(totalBet.toDoubleOrNull() ?: 0.0, numberOfOutcomes.toIntOrNull() ?: 0, coeffsList)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: ${result ?: "Enter values"}")
    }
}

@Composable
fun ForkBetCalculatorScreen(viewModel: MainViewModel) {
    var coefficient1 by remember { mutableStateOf("") }
    var coefficient2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = coefficient1,
            onValueChange = { coefficient1 = it },
            label = { Text("Coefficient 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = coefficient2,
            onValueChange = { coefficient2 = it },
            label = { Text("Coefficient 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                result = viewModel.calculateFork(coefficient1.toDoubleOrNull() ?: 0.0, coefficient2.toDoubleOrNull() ?: 0.0)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: ${result ?: "Enter values"}")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val fakeViewModel = MainViewModel(BetRepository())
    MyApp(fakeViewModel)
}