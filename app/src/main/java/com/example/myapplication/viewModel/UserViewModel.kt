package com.example.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication.dataModels.User
import com.example.myapplication.repository.UserAuthenticator
import com.google.firebase.auth.FirebaseUser

class UserViewModel(private val repository: UserAuthenticator) : ViewModel() {

    // Uses repository to check for the current user
    fun  currentUser(): FirebaseUser? = repository.currentUser()

    // Uses repository to logout a user. This will be used in the settings screen
    fun logout() { repository.logOut()}

    // Uses repository to login a user
   // fun login(email: String, password: String, callback: (Boolean, String?) -> Unit) {
    //    repository.login(email, password) {success, error -> callback(success, error)}
 //   }

    // Uses repository to create a new user account
    fun createAccount(email: String, password: String, userData: User, callback: (Boolean, String?) -> Unit) {
        Log.i("createAccount","Got to createAccount")
        repository.createAccount(email, password, userData) {success, error -> callback(success, error)}
    }

    // testing to check that the ViewModel works
    init {
        Log.d("viewModel", "ViewModel running")
    }
}