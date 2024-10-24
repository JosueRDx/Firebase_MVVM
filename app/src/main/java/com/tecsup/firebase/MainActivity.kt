package com.tecsup.firebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.tecsup.firebase.ui.theme.Lab11_FirebaseTheme
import com.tecsup.firebase.view.MainScreenFirebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab11_FirebaseTheme {
                MainScreenFirebase()
            }
        }
    }
}