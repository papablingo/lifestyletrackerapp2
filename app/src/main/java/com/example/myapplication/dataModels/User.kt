package com.example.myapplication.dataModels

// Data model
// Maps data for database (making it easier to use database)
// Will be used to map data fetched from the Firebase Realtime Database
// Handles data he same across the entire app
// Used to transfer user-related data across activities, view models and fragments
data class User (
    val email: String = "",
    val password: String = "",
)
