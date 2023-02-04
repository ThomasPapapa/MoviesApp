package com.thomas.moviesapp

import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material.MaterialTheme
import com.thomas.moviesapp.navigation.MainNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContent {
            MaterialTheme {
                MainNavController()
            }
        }
    }
}