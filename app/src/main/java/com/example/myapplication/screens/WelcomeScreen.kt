package com.example.myapplication.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.navigation.ScreensNav
import com.example.myapplication.ui.theme.MyApplicationTheme

// welcome screen. First screen users see

@Composable
// navigation host controls navigation
fun WelcomeScreen(navController: NavHostController) {
    // Column UI
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            fontSize = 48.sp,
            lineHeight = 50.sp,
            modifier = Modifier
                .padding(top = 35.dp)
        )

        Button (
            // navigation to the login screen
            onClick = {navController.navigate(ScreensNav.Login.route)},
            modifier = Modifier
                .offset(y = 150.dp)
        ) {
            Text(text = stringResource(id = R.string.LoginText))
        }

        Button (
            // navigation to the create account screen
            onClick = {navController.navigate(ScreensNav.Create.route) },
            modifier = Modifier
                .offset(y = 200.dp)
        ) {
            Text(text = stringResource(id = R.string.createAccountText))
        }
    }
}

@Preview
@Composable
fun WelcomePreview() {
    val navController = rememberNavController()
    MyApplicationTheme {
        WelcomeScreen(navController)
    }
}