package com.example.composeloginscreen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeloginscreen.ui.theme.ComposeLoginScreenTheme
import com.example.composeloginscreen.ui.theme.GreenJC

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLoginScreenTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    NavGraph(navController)
                }
            }
        }
    }
}

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current.applicationContext

    Column(modifier = Modifier
        .fillMaxSize()
        .paint(painterResource(id = R.drawable.jclogin), contentScale = ContentScale.FillBounds)
        .padding(horizontal = 26.dp, vertical = 140.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        OutlinedTextField(
            value = username, onValueChange = { username = it },
            label = { Text("Username") },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedLeadingIconColor = GreenJC,
                unfocusedLeadingIconColor = GreenJC,
                focusedLabelColor = GreenJC,
                unfocusedLabelColor = GreenJC,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = GreenJC,
                unfocusedIndicatorColor = GreenJC,
                unfocusedPlaceholderColor = GreenJC
            ), leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "username")
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        )
        OutlinedTextField(
            value = password, onValueChange = { password = it },
            label = { Text("password") },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedLeadingIconColor = GreenJC,
                unfocusedLeadingIconColor = GreenJC,
                focusedLabelColor = GreenJC,
                unfocusedLabelColor = GreenJC,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = GreenJC,
                unfocusedIndicatorColor = GreenJC,
                unfocusedPlaceholderColor = GreenJC
            ), leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "username")
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = {
            if(authenticate(username, password)){
                onLoginSuccess()
                Toast.makeText(context,"Login successful",Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context,"invalid login",Toast.LENGTH_LONG).show()
            }
        }, colors = ButtonDefaults.buttonColors(GreenJC),
            contentPadding = PaddingValues(start = 60.dp , top = 8.dp , end = 60.dp , bottom = 8.dp),
            modifier = Modifier.padding(top = 18.dp)
            ) {
            Text(text = "Login", fontSize = 22.sp)
        }
    }
}

private fun authenticate(username: String, password: String) : Boolean{
    val validusername = "Admin"
    val validuserpassword = "1234"
    val validusername2 = "user"
    val validuserpassword2 = "4321"
    val validusername3 = "guest"
    val validuserpassword3 = "7548"
    return username == validusername && password == validuserpassword || username == validusername2 && password == validuserpassword2
            || username == validusername3 && password == validuserpassword3
}

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onLoginSuccess = { navController.navigate("home"){
                popUpTo(0)
                }
            })
        }
        composable("home"){
            HomeScreen()
        }
    }
}

@Preview
@Composable
fun ComposeLoginScreenPreview(){
    ComposeLoginScreenTheme {
        LoginScreen(onLoginSuccess = {})
    }
}