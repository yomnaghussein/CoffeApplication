package com.example.coffeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.coffeapplication.ui.compose_screens.CoffeeApplication
import com.example.coffeapplication.ui.theme.CoffeApplicationTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeApplicationTheme {
                CoffeeApplication()
            }
        }
    }
}


