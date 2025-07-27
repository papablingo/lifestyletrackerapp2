package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.screens.CreateAccountScreen
import com.example.myapplication.screens.CreateAccountScreenPreview
import com.example.myapplication.screens.LoginScreen
import com.example.myapplication.screens.WelcomeScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreensNav.Welcome.route) { // The start destination is the
        // Welcome screen as that is where the user starts the flow of the app
        composable(ScreensNav.Welcome.route) { WelcomeScreen(navController) }
        composable(ScreensNav.Login.route) { LoginScreen(navController) } // navigation to the LoginScreen
        composable(ScreensNav.Create.route) { CreateAccountScreen(navController) } // navigation to the LoginScreen
        // Add more destinations similarly.
    }
}