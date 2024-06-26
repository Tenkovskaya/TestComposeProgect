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
fun ForkBetCalculatorScreen(viewModel: MainViewModel) {
    var coefficient1 by remember { mutableStateOf("") }
    var coefficient2 by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("Enter values to calculate") }

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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = coefficient2,
            onValueChange = { coefficient2 = it },
            label = { Text("Coefficient 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val coeff1Double = coefficient1.toDoubleOrNull()
                val coeff2Double = coefficient2.toDoubleOrNull()
                if (coeff1Double != null && coeff2Double != null) {
                    val forkResult = viewModel.calculateFork(coeff1Double, coeff2Double)
                    resultText = if (forkResult.isFork) "Fork found: Profitable!" else "No fork: Not profitable."
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
