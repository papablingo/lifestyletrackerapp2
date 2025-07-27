package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

// Email and password input fields: Allows users to input their emails and passwords
// Login button: This starts the login process
// Show error messages
// Navigates to register screen: Button to navigate to the sign up screen
// Use firebase authentication to authenticate the user and ensure their details exist
// Handle login success/failure
// If login is successful, navigate to the calendar screen
// Displays errors if login failed
// Progress tracker to show the progress of loading (Could be nice to add)


// This needs text fields for the email and password so that users can input their emails and passwords
// Also needs the button that allows a user to either confirm and login or go to create account and create a new account
// Also needs error messages like if a user tries to login without inputting neccary user data (e.g. if they leave a text field blank)
// or if login fails
// Also needs to call ViewModel functions such as login


@Composable
fun LoginScreen(navController: NavHostController) {

    // rememberSaveables for the class
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

    // box
    Box(
        modifier = Modifier.fillMaxSize() // fills whole screen
    ) {
        // Title to the login page
        Text(
            text = stringResource(id = R.string.LoginText),
            fontSize = 48.sp, // size of font
            modifier = Modifier
                .align(Alignment.TopCenter) // Aligns to the to the top center of the page
                .padding(top = 35.dp) // Where the title is located
        )
        // Text to tell the user what to do
        Text(
            // "Enter Your email:"
            text = stringResource(id = R.string.EnterEmailText), // Change this to a string resource
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 250.dp)
        )
        // The textfield that takes in a user input
        EmailTextField(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-60).dp), // For now, I might change to a column later on but for now this works
            textValue = email.value, // The username
            onValChanged = {
                email.value = it // Changes based on what the user inputs
            }
        )
        // Text to tell the user what to do
        Text(
            //"Enter Your Password:"
            text = stringResource(id = R.string.PasswordText), // Change to string resource
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 400.dp)
        )
        // Text field for password
        PasswordTextField(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 45.dp),
            textValue = password.value,
            onValChanged = {
                password.value = it
            }
        )
        // login button, currently working on logic
        Button (
            onClick = {
                Log.i("LOGIN:","email: " + email.value + "\npassword: " + password.value)
                navController.navigate(ScreensNav.Welcome.route)
                      },
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 150.dp)
        )
        {
            Text(text = stringResource(id = R.string.LoginText)) // Change with string resource
        }
    }
}

// Username text field
@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    textValue: String = "",
    onValChanged: (String) -> Unit = {}) {
    OutlinedTextField(
        value = textValue,
        modifier = modifier,
        label = {
            Text(text = stringResource(id = R.string.EmailText)) // make a string resource
        },
        onValueChange = onValChanged
    )
}

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    textValue: String = "",
    onValChanged: (String) -> Unit = {}) {
    OutlinedTextField(
        value = textValue,
        modifier = modifier,
        label = {
            Text(text = stringResource(id = R.string.EnterpasswordEmail)) // Make a string resource
        },
        onValueChange = onValChanged, // Changes as a user is typing
        visualTransformation = PasswordVisualTransformation() // This is an existing function that changes the password as a user types to "*"

    )
}

// Screen preview
@Preview
@Composable
fun LoginPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        LoginScreen(navController)
    }
}
