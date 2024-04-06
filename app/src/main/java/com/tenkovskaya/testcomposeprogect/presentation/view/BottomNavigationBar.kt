package com.tenkovskaya.testcomposeprogect.presentation.view


import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.tenkovskaya.testcomposeprogect.R

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