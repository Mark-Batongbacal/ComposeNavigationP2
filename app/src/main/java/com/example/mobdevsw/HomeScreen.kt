package com.example.mobdevsw

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val HomeBackground = Color(0xFF09090D)
private val HomeSurface = Color(0xFF15151C)
private val HomeField = Color(0xFF111117)
private val HomeBorder = Color(0xFF2A2A33)
private val HomeMuted = Color(0xFF9A9AA5)
private val HomeAccent = Color(0xFF8B7CFF)
private val HomeAccentBlue = Color(0xFF4F7CFF)
private val HomeDanger = Color(0xFFFF6B81)

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
    var errorMessage by remember { mutableStateOf("") }
    var showConfirmationDialog by remember { mutableStateOf(false) }

    val avatarLetter = studentName
        .trim()
        .firstOrNull()
        ?.uppercaseChar()
        ?.toString()
        ?: "S"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HomeBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "STUDENT PORTAL",
                        color = HomeMuted,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.4.sp
                    )
                    Text(
                        text = "Welcome back",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(HomeSurface),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = avatarLetter,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(28.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(HomeAccentBlue, HomeAccent)
                        )
                    )
                    .padding(22.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = "Student Information",
                        color = Color.White.copy(alpha = 0.78f),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = studentName.ifBlank { "Your name" },
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = course.ifBlank { "Your course" },
                        color = Color.White.copy(alpha = 0.82f),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "LOGIN ATTEMPTS",
                                color = Color.White.copy(alpha = 0.70f),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = loginAttempts.toString(),
                                color = Color.White,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Button(
                            onClick = onAddAttempt,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                text = "+ Add Attempt",
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = HomeSurface)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = "Edit information",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Update your details before opening the profile card.",
                        color = HomeMuted,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    StudentTextField(
                        label = "Student ID",
                        value = studentId,
                        onValueChange = onStudentIdChange
                    )

                    StudentTextField(
                        label = "Name",
                        value = studentName,
                        onValueChange = onStudentNameChange
                    )

                    StudentTextField(
                        label = "Course",
                        value = course,
                        onValueChange = onCourseChange
                    )

                    StudentTextField(
                        label = "Year Level",
                        value = yearLevel,
                        onValueChange = onYearLevelChange
                    )

                    if (errorMessage.isNotEmpty()) {
                        Text(
                            text = errorMessage,
                            color = HomeDanger,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = {
                                onClear()
                                errorMessage = ""
                            },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = HomeField,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(18.dp)
                        ) {
                            Text("Clear")
                        }

                        Button(
                            onClick = {
                                errorMessage = when {
                                    studentId.isBlank() -> "Student ID is required."
                                    studentName.isBlank() -> "Name is required."
                                    course.isBlank() -> "Course is required."
                                    else -> ""
                                }

                                if (errorMessage.isEmpty()) {
                                    showConfirmationDialog = true
                                }
                            },
                            modifier = Modifier.weight(1.45f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            ),
                            shape = RoundedCornerShape(18.dp)
                        ) {
                            Text(
                                text = "View Details",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }

    if (showConfirmationDialog) {
        AlertDialog(
            onDismissRequest = {
                showConfirmationDialog = false
            },
            containerColor = HomeSurface,
            titleContentColor = Color.White,
            textContentColor = HomeMuted,
            title = {
                Text(
                    text = "Confirm Student Information",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text("View details for $studentName?")
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showConfirmationDialog = false
                    }
                ) {
                    Text(
                        text = "Cancel",
                        color = HomeMuted
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showConfirmationDialog = false
                        onViewDetails(studentId, studentName, course)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HomeAccent,
                        contentColor = Color.White
                    )
                ) {
                    Text("Continue")
                }
            }
        )
    }
}

@Composable
private fun StudentTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(label) },
        singleLine = true,
        shape = RoundedCornerShape(18.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = HomeField,
            unfocusedContainerColor = HomeField,
            focusedBorderColor = HomeAccent,
            unfocusedBorderColor = HomeBorder,
            focusedLabelColor = HomeAccent,
            unfocusedLabelColor = HomeMuted,
            cursorColor = HomeAccent
        )
    )
}
