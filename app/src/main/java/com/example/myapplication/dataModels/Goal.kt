package com.example.myapplication.dataModels

// data model
// It represents the goals and stores it within the database
// Interfaces with ViewModel to manage goal's lifecycle
// Provides structure to handle goal in app
data class Goal (
    val category: String = "",
    val name: String = "",
    val achieved: Boolean = false, // true or false based on if user completed task or not
    val timeCompleted: String? = null
)