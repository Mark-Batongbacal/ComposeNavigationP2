package com.example.mobdevsw

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailsScreen(
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Student Details")

        Text(
            text = "Student ID: 1001",
            modifier = Modifier.padding(15.dp)
        )

        Text("Name: Juan Dela Cruz")
        Text("Course: BS Computer Engineering")
        Text("Year: 4")

        Button(
            onClick = onBack,
            modifier = Modifier.padding(15.dp)
        ) {
            Text("Back")
        }
    }
}