package com.example.myapplication.navigation

// Navigation graph to help handle the navigation between screens
sealed class ScreensNav(val route: String) {
    object Welcome : ScreensNav("welcome") // Welcome screen
    object Login : ScreensNav("login") // Login screen
    object Create : ScreensNav("create") // Create Account screen
}
