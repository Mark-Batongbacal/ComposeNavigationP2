package com.example.mobdevsw

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
    studentId: String,
    studentName: String,
    course: String,
    yearLevel: String,
    loginAttempts: Int,

    onStudentIdChange: (String) -> Unit,
    onStudentNameChange: (String) -> Unit,
    onCourseChange: (String) -> Unit,
    onYearLevelChange: (String) -> Unit,

    onAddAttempt: () -> Unit,
    onClear: () -> Unit,

    onViewDetails: (
        studentId: String,
        studentName: String,
        course: String
    ) -> Unit
) {

    var errorMessage by remember {
        mutableStateOf("")
    }

    var showConfirmationDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Student Information",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Student ID",
            modifier = Modifier.align(Alignment.Start)
        )

        TextField(
            value = studentId,
            onValueChange = onStudentIdChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text(
            text = "Name",
            modifier = Modifier.align(Alignment.Start)
        )

        TextField(
            value = studentName,
            onValueChange = onStudentNameChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text(
            text = "Course",
            modifier = Modifier.align(Alignment.Start)
        )

        TextField(
            value = course,
            onValueChange = onCourseChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text(
            text = "Year Level",
            modifier = Modifier.align(Alignment.Start)
        )

        TextField(
            value = yearLevel,
            onValueChange = onYearLevelChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text(
            text = "Login Attempts: $loginAttempts",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 12.dp)
        )

        Button(
            onClick = onAddAttempt,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Attempt")
        }

        Button(
            onClick = {
                onClear()
                errorMessage = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clear")
        }

        Button(
            onClick = {

                errorMessage = when {
                    studentId.isBlank() ->
                        "Student ID is required."

                    studentName.isBlank() ->
                        "Name is required."

                    course.isBlank() ->
                        "Course is required."

                    else -> ""
                }

                // Only show confirmation if validation passed
                if (errorMessage.isEmpty()) {
                    showConfirmationDialog = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Details")
        }

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
    }

    if (showConfirmationDialog) {

        AlertDialog(
            onDismissRequest = {
                showConfirmationDialog = false
            },

            title = {
                Text("Confirm Student Information")
            },

            text = {
                Text(
                    "View details for $studentName?"
                )
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        showConfirmationDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            },

            confirmButton = {
                Button(
                    onClick = {
                        showConfirmationDialog = false

                        onViewDetails(
                            studentId,
                            studentName,
                            course
                        )
                    }
                ) {
                    Text("Continue")
                }
            }
        )
    }
}