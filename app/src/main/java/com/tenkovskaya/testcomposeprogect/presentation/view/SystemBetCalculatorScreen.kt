package com.tenkovskaya.testcomposeprogect.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
    var resultText by remember { mutableStateOf("Enter values to calculate") }

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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = numberOfOutcomes,
            onValueChange = { numberOfOutcomes = it },
            label = { Text("Number of Outcomes") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = coefficients,
            onValueChange = { coefficients = it },
            label = { Text("Coefficients (comma separated)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val coeffsList = coefficients.split(",").mapNotNull { it.toDoubleOrNull() }
                if (totalBet.toDoubleOrNull() != null && numberOfOutcomes.toIntOrNull() != null && coeffsList.isNotEmpty()) {
                    val result = viewModel.calculateSystemBet(totalBet.toDoubleOrNull() ?: 0.0, numberOfOutcomes.toIntOrNull() ?: 0, coeffsList)
                    resultText = "Total Winning: ${"%.2f".format(result.potentialWinnings)} руб."
                } else {
                    resultText = "Please enter valid numbers"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(resultText)
    }
}
