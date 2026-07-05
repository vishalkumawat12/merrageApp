package com.example.merrageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.merrageapp.ui.navigation.SoulmateNavGraph
import com.example.merrageapp.ui.theme.MerrageAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MerrageAppTheme {
                SoulmateNavGraph()
            }
        }
    }
}
