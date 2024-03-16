package com.tenkovskaya.testcomposeprogect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
    }
}

@Preview
@Composable
fun AppScreen() {
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.Black,
                contentColor = Color.White
            ) {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.express),
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    label = { Text("Express", color = Color.White) },
                    selected = selectedTab == 0,
                    onClick = { setSelectedTab(0) }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.system),
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    label = { Text("Systems", color = Color.White) },
                    selected = selectedTab == 1,
                    onClick = { setSelectedTab(1) }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.forks),
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    label = { Text("Forks", color = Color.White) },
                    selected = selectedTab == 2,
                    onClick = { setSelectedTab(2) }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            when (selectedTab) {
                0 -> BettingCalculatorScreen()
                1 -> SystemBetCalculatorScreen()
                2 -> ForkBetCalculatorScreen()
            }
        }
    }
}
@Preview
@Composable
fun BettingCalculatorScreen() {
    var betAmount by remember { mutableStateOf("") }
    var coefficient by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = betAmount,
            onValueChange = { betAmount = it },
            label = { Text("Bid") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = coefficient,
            onValueChange = { coefficient = it },
            label = { Text("Сoefficient") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                result = calculateExpressBet(betAmount, coefficient)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
        ) {
            Text("Calculate", color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: ${result ?: ""}", color = Color.White)

    }

    InfoDialog.Show(
        showDialog = showDialog,
        onClose = { showDialog = false },
        title = "Information",
        message = "Learn about calculating Express Bets."
    )

    Column {
        TopAppBar(
            title = { Text("Express Bets", color = Color.White) },
            backgroundColor = Color.Black,
            actions = {
                IconButton(onClick = { showDialog = true }) {
                    Icon(Icons.Filled.Info, contentDescription = "Information", tint = Color.White)
                }
            }
        )
}
}

fun calculateExpressBet(betAmount: String, coefficient: String): String {
    return try {
        val amount = betAmount.toDouble()
        val coeff = coefficient.toDouble()
        val winAmount = BetCalculator.calculateExpressBet(amount, coeff)
        "Coefficient: ${coeff * coeff * coeff}, Winning: ${"%.2f".format(winAmount)} руб."
    } catch (e: Exception) {
        "Input error"
    }
}

@Preview
@Composable
fun SystemBetCalculatorScreen() {
    var totalBet by remember { mutableStateOf("") }
    var numberOfOutcomes by remember { mutableStateOf("") }
    var coefficients by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = totalBet,
            onValueChange = { totalBet = it },
            label = { Text("Total bet amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = numberOfOutcomes,
            onValueChange = { numberOfOutcomes = it },
            label = { Text("Number of outcomes") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = coefficients,
            onValueChange = { coefficients = it },
            label = { Text("Сoefficient") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                result = calculateSystemBet(totalBet, numberOfOutcomes, coefficients)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
        ) {
            Text("Calculate", color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: ${result ?: ""}", color = Color.White)
    }

    InfoDialog.Show(
        showDialog = showDialog,
        onClose = { showDialog = false },
        title = "Information",
        message = "Learn about calculating System Bet."
    )

    Column {
        TopAppBar(
            title = { Text("System Bet", color = Color.White) },
            backgroundColor = Color.Black,
            actions = {
                IconButton(onClick = { showDialog = true }) {
                    Icon(Icons.Filled.Info, contentDescription = "Information", tint = Color.White)
                }
            }
        )
    }
}

fun calculateSystemBet(totalBet: String, numberOfOutcomes: String, coefficients: String): String {
    return try {
        val betAmount = totalBet.toDouble()
        val outcomes = numberOfOutcomes.toInt()
        val coeffList = coefficients.split(",").map { it.trim().toDouble() }
        val totalWin = BetCalculator.calculateSystemBet(betAmount, outcomes, coeffList)
        "General Winning: ${"%.2f".format(totalWin)}"
    } catch (e: Exception) {
        "Input error"
    }
}

@Preview
@Composable
fun ForkBetCalculatorScreen() {
    var coefficient1 by remember { mutableStateOf("") }
    var coefficient2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var totalBet by remember { mutableStateOf("") }
    var numberOfOutcomes by remember { mutableStateOf("") }
    var coefficients by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = coefficient1,
            onValueChange = { coefficient1 = it },
            label = { Text("Сoefficient 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = coefficient2,
            onValueChange = { coefficient2 = it },
            label = { Text("Сoefficient 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                result = calculateSystemBet(totalBet, numberOfOutcomes, coefficients)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
        ) {
            Text("Calculate", color = Color.White) // Устанавливаем цвет текста на белый для контраста
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: ${result ?: ""}", color = Color.White)
    }

    InfoDialog.Show(
        showDialog = showDialog,
        onClose = { showDialog = false },
        title = "Information",
        message = "Learn about calculating Fork Bet."
    )

    Column {
        TopAppBar(
            title = { Text("Fork Bet", color = Color.White) },
            backgroundColor = Color.Black,
            actions = {
                IconButton(onClick = { showDialog = true }) {
                    Icon(Icons.Filled.Info, contentDescription = "Information", tint = Color.White)
                }
            }
        )
    }
}

fun calculateFork(coeff1: String, coeff2: String): String {
    return try {
        val k1 = coeff1.toDouble()
        val k2 = coeff2.toDouble()
        val isFork = BetCalculator.calculateFork(k1, k2)
        if (isFork) {
            "There is a fork. Value: ${"%.4f".format(1 / k1 + 1 / k2)}"
        } else {
            "No fork. Significance: ${"%.4f".format(1 / k1 + 1 / k2)}"
        }
    } catch (e: Exception) {
        "Input error"
    }
}



