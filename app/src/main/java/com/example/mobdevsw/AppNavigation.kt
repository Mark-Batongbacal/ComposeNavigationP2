package com.example.mobdevsw

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    var studentId by remember { mutableStateOf("1001") }
    var studentName by remember { mutableStateOf("Mark Jennel Batongbacal") }
    var course by remember { mutableStateOf("BS Computer Engineering") }
    var yearLevel by remember { mutableStateOf("4") }

    var loginAttempts by remember { mutableStateOf(0) }

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {

            HomeScreen(
                studentId = studentId,
                studentName = studentName,
                course = course,
                yearLevel = yearLevel,
                loginAttempts = loginAttempts,

                onStudentIdChange = {
                    studentId = it
                },

                onStudentNameChange = {
                    studentName = it
                },

                onCourseChange = {
                    course = it
                },

                onYearLevelChange = {
                    yearLevel = it
                },

                onAddAttempt = {
                    loginAttempts++
                },

                onClear = {
                    studentId = ""
                    studentName = ""
                    course = ""
                    yearLevel = ""
                    loginAttempts = 0
                },

                onViewDetails = { id, name, studentCourse ->

                    val encodedId = Uri.encode(id)
                    val encodedName = Uri.encode(name)
                    val encodedCourse = Uri.encode(studentCourse)

                    navController.navigate(
                        "details/$encodedId/$encodedName/$encodedCourse"
                    )
                }
            )
        }

        composable(
            route = "details/{studentId}/{studentName}/{course}"
        ) { backStackEntry ->

            val studentId =
                backStackEntry.arguments?.getString("studentId") ?: ""

            val studentName =
                backStackEntry.arguments?.getString("studentName") ?: ""

            val course =
                backStackEntry.arguments?.getString("course") ?: ""

            DetailsScreen(
                studentId = studentId,
                studentName = studentName,
                course = course,

                onBack = {
                    navController.popBackStack()
                },

                onEdit = {
                    navController.popBackStack(
                        route = "home",
                        inclusive = false
                    )
                }
            )
        }
    }
}