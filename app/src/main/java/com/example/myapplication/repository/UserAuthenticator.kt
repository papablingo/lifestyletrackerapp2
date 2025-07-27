package com.example.myapplication.repository

import android.util.Log
import com.example.myapplication.dataModels.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class UserAuthenticator {
    private val authentication: FirebaseAuth =
        FirebaseAuth.getInstance() // connects ti Firebase authentication

    // Get current user for user that is logged in
    fun currentUser(): FirebaseUser? {
        return authentication.currentUser // gets the current user
    }

    // Logout current user
    fun logOut() {
        authentication.signOut() // signs the current user out
    }

    fun createAccount(
        email: String,
        password: String,
        userData: User,
        onComplete: (Boolean, String?) -> Unit
    ) {
        Log.d("CreateAccount", "🟡 Entered createAccount()")
        authentication.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                Log.d(
                    "CreateAccount",
                    "User created with email: ${authResult.user?.email}, UID: ${authResult.user?.uid}"
                )
                // Oh success it has created an account in the database

                val uid = authResult.user?.uid
                if (uid == null) {
                    onComplete(true, "User UID is null after account creation.")
                    return@addOnSuccessListener
                }

                val databaseRef = FirebaseDatabase.getInstance().reference
                databaseRef.child("users").child(uid).setValue(userData)
                    .addOnSuccessListener {
                        onComplete(true, null)
                    }

                    .addOnFailureListener { exception ->
                        Log.e("UserRepository", "Error creating user", exception)
                        onComplete(false, exception.message) // exception message thrown if failed
                    }
            }

            .addOnFailureListener { exception ->
                onComplete(false, exception.message)
            }
    }

    // Login for an existing user
    fun login(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        authentication.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // listener used to check for a success
                onComplete(true, null)
            }
            .addOnFailureListener { exception ->
                onComplete(false, exception.message) // exception message thrown
                // add own unique exceptions
            }
    }

    // Delete the current user that is logged in and if not let user know that no one is logged in
    fun deleteAccount(onComplete: (Boolean, String?) -> Unit) {
        val currentUser = authentication.currentUser // first we get the current user
        // if the user is logged in then the user is deleted
        if (currentUser != null) {
            currentUser.delete()
                // User is deleted
                .addOnSuccessListener {
                    onComplete(true, null) // deletes the user
                }
                .addOnFailureListener { exception ->
                    onComplete(false, exception.message) // provides a exception message
                }
        } else {
            onComplete(
                false,
                "No user is logged in"
            ) // lets the system know no user is logged in
        }
    }
}