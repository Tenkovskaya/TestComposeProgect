package com.tenkovskaya.testcomposeprogect.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.tenkovskaya.testcomposeprogect.presentation.viewModel.MainViewModel

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