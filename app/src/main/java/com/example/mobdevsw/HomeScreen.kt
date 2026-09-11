package com.example.mobdevsw

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onViewDetails: () -> Unit
) {
    var loginAttempts by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Student Information")

        Text("Name: Mark Jennel M. Batongbacal")
        Text("Course: BS Computer Engineering")
        Text("Year: 4")

        Text(
            text = "Login Attempts: $loginAttempts",
            modifier = Modifier.padding(15.dp)
        )

        Button(
            onClick = {
                loginAttempts++
            },
            modifier = Modifier.padding(15.dp)
        ) {
            Text("Add Attempt")
        }

        Button(
            onClick = onViewDetails,
            modifier = Modifier.padding(15.dp)
        ) {
            Text("View Details")
        }
    }
}