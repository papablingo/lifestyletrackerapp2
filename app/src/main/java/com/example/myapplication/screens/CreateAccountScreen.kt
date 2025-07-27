package com.example.myapplication.screens

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.dataModels.User
import com.example.myapplication.navigation.ScreensNav
import com.example.myapplication.repository.UserAuthenticator
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewModel.UserViewModel


// Email and password user input fields for user to input information
// Confirm password field
// Submit button: Starts CreateAccount process
// Input validation to ensure the email is in the correct format and passwords match
// Display error messages
// When the user clicks submit button a new user is created in the database through firebase authetication
// Upon success user is brought to calendar screen


@Composable
fun CreateAccountScreen(navController: NavHostController)
{
    val repository: UserAuthenticator = UserAuthenticator()
    val userViewModel: UserViewModel = viewModel()

    // rememberSaveables
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val error = rememberSaveable { mutableStateOf<String?>(null) }

    // The preview cant handle repository and ViewModel data directly so it was key to create a
    // function that contains the necessary information without having issues with the preview not being able to handle it
    // This runs the CreateAccountScreenContent and adds everything into it
    CreateAccountScreenContent(
        // gets the value of the email
        email = email.value,
        // gets the value of the password
        password = password.value,
        emailChange = {email.value = it}, // This is for when the email changes
        passwordChange = {password.value = it}, // This is for when the password changes
        createAccount = { val user = User (email = email.value, password = password.value)
            userViewModel.createAccount(email.value, password.value, user) { success, errorMessage ->
                if (success) {
                    error.value = null // error will = null because the account ahs been created successfully
                    ScreensNav.Login.route
                } else {
                    error.value = errorMessage // error message since it failed
                }
            }
        }
    )
}

// CreateAccountScreenContent
@Composable
fun CreateAccountScreenContent(
    email: String,
    password: String,
    emailChange: (String) -> Unit,
    passwordChange: (String) -> Unit,
    createAccount: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize() // fills max screen size
    ) {
        // Title to the create account page
        Text(
            text = stringResource(id = R.string.createAccountText),
            fontSize = 48.sp, // size of font
            modifier = Modifier
                .align(Alignment.TopCenter) // Aligns to the to the top center of the page
                .padding(top = 35.dp) // Where the title is located
        )
        // Text to tell the user what to do
        Text(
            text = stringResource(id = R.string.EmailText), // Change this to a string resource
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 250.dp)
        )
        // The textfield that takes in a user input
        EmailTextFieldAccount(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-60).dp), // For now, I might change to a column later on but for now this works
            textValue = email, // The email
            // Changes as the user types into the text field
            onValChanged = emailChange // Changes based on what the user inputs
        )

        // Text to tell the user what to do
        Text(
            text = stringResource(id = R.string.PasswordText), // Change to string resource
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 400.dp)
        )

        // Text field for password
        PasswordTextFieldAccount(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 65.dp),
            textValue = password, // The email
            // Changes as the types and inputs into the textfield
            onValChanged = passwordChange
        )

        // login button, currently working on logic
        Button(
            onClick = {
                // this was done to check that the button was being clicked
                createAccount() // runs the CreateAccount ViewModel function
                android.util.Log.d("CreateAccount", "Success! Button clicked")

            },
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 150.dp)
        )
        {
            // "Create Account"
            Text (text = stringResource(id = R.string.createAccountButton)) // Change with string resource
        }
    }
}

// Username text field
@Composable
fun EmailTextFieldAccount(
    modifier: Modifier = Modifier,
    textValue: String,
    onValChanged: (String) -> Unit = {}) {
    OutlinedTextField(
        value = textValue,
        modifier = modifier,
        label = {
            // "Enter Email: "
            Text(text = stringResource(id = R.string.EnterEmailText)) // make a string resource
        },
        onValueChange = onValChanged
    )
}

@Composable
fun PasswordTextFieldAccount(
    modifier: Modifier = Modifier,
    textValue: String,
    onValChanged: (String) -> Unit = {}) {
    OutlinedTextField(
        value = textValue,
        modifier = modifier,
        label = {
            // "Enter password: "
            Text(text = stringResource(id = R.string.EnterpasswordEmail)) // Make a string resource
        },
        onValueChange = onValChanged, // Changes as a user is typing
        visualTransformation = PasswordVisualTransformation() // This is an existing function that changes the password as a user types to "*"
    )
}

@Preview
@Composable
fun CreateAccountScreenPreview() {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    MyApplicationTheme {
        CreateAccountScreenContent(
            email = email.value,
            password = password.value,
            emailChange = {email.value = it},
            passwordChange = {password.value = it},
            createAccount = {}
        )
    }
}