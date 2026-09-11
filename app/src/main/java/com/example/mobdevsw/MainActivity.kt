package com.example.mobdevsw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mobdevsw.ui.theme.MOBDEVSWTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MOBDEVSWTheme{
                AppNavigation()
            }
        }
    }
}