package com.example.jetpackcomposeinstagram

import android.app.Activity
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(){

Box(
    Modifier
        .fillMaxSize()
        .padding(8.dp)){
    Header(Modifier.align(Alignment.TopEnd))
    Body(Modifier.align(Alignment.Center))
    Footer(Modifier.align(Alignment.BottomCenter))
}

}

@Composable
fun Footer(modifier: Modifier) {
Column(modifier=modifier.fillMaxWidth()) {
    Divider(
        Modifier
            .background((Color(0xFFF9F9F9)))
            .height(1.dp)
            .fillMaxWidth() )
    DpSpacer(24.dp )

    SingUp()
    DpSpacer(24.dp )

}
}

@Composable
fun SingUp() {
     Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
         Text(text = "Don't have a account?", fontSize = 12.sp, fontWeight = FontWeight.Bold,color= Color(0xFFB5B5B5))
         Text(text = "Sign up", fontSize = 12.sp, fontWeight = FontWeight.Bold,             color= Color(0xFF4EA8E9))
     }
}

@Composable
fun Body(modifier: Modifier) {
    var email by rememberSaveable {  mutableStateOf("")  }
    var password by rememberSaveable {  mutableStateOf("")  }
    var isLoginEnable by rememberSaveable { mutableStateOf(false)}
Column(modifier = modifier) {
    ImageLogo(Modifier.align(Alignment.CenterHorizontally))
    DpSpacer(16.dp )
    Email(email){
        email=it
        isLoginEnable= enableLogin(email,password)
    }
    DpSpacer(4.dp )

    Password(password){
            password=it
        isLoginEnable= enableLogin(email,password) }
    DpSpacer(8.dp )
    ForgotPassword(Modifier.align(Alignment.End))
    DpSpacer(8.dp )
    LoginButton(isLoginEnable)
    DpSpacer(16.dp )
    LoginDivider()
    DpSpacer(32.dp )
    SocialLogin()

}
}

@Composable
fun SocialLogin() {
     Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
         ){
         Image(painter = painterResource(id = R.drawable.fb), contentDescription ="Social login Facebook",modifier= Modifier.size(16.dp), )
         Text(
             text = "Continue as Hernan Velazquez",
             fontSize = 14.sp,
             fontWeight = FontWeight.Bold,
             modifier = Modifier.padding(horizontal = 8.dp),
             color= Color(0xFF4EA8E9)
         )
     }
}

@Composable
fun LoginDivider(){
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            Modifier
                .background((Color(0xFFF9F9F9)))
                .height(1.dp)
                .weight(1f) )
        Text(
            text = "OR",
            modifier=Modifier.padding(horizontal = 18.dp), //el espacio que da por los lados
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color= Color(0xFFB5B5B5)

        )
        Divider(
            Modifier
                .background((Color(0xFFF9F9F9)))
                .height(1.dp)
                .weight(1f) )

    }

}

@Composable
fun LoginButton(loginEnable: Boolean) {
    Button(
        onClick = {},
        enabled = loginEnable,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF4EA8E9),
            disabledBackgroundColor = Color(0xFF78C8F9),
            contentColor = Color.White,
            disabledContentColor = Color.White

    )) {
        Text(text = "Log in")

    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {



        Text(text = "Forgot password? ", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4EA8E9),modifier=modifier)


    //Modifier.align(Alignment.TopEnd)
}

@Composable
fun Password(password: String, onTextChange: (String) -> Unit) {
    var passwordVisibility by rememberSaveable { mutableStateOf(false)    }
    TextField(  value = password,
        onValueChange ={onTextChange(it)},
        modifier = Modifier.fillMaxWidth() ,
        maxLines = 1,
        singleLine = true,
        placeholder = { Text(text = "password")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor= Color(0xFFfAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon =
        {
            val imagen=
                if(passwordVisibility){
                    Icons.Filled.VisibilityOff
                }
                else {
                Icons.Filled.Visibility
                }
            IconButton(onClick = { passwordVisibility=!passwordVisibility}) {
                Icon(imageVector = imagen, contentDescription = "Show password")
            }
        },
        visualTransformation = if(passwordVisibility){
            VisualTransformation.None
        }
    else{
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun Email(email:String,onTextChange : (String)-> Unit) {
    TextField(  value = email,
        onValueChange ={onTextChange(it)},
        modifier = Modifier.fillMaxWidth() ,
        maxLines = 1,
        singleLine = true,
        placeholder = { Text(text = "Email")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
             backgroundColor= Color(0xFFfAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )

    )
}


@Composable
fun DpSpacer(value: Dp) {
    Spacer(modifier = Modifier.height(value))
}
@Composable
fun ImageLogo(modifier: Modifier) {
Image(painter = painterResource(id = R.drawable.insta), contentDescription = "insta",modifier=modifier)
}

@Composable
fun Header(modifier:Modifier){
    val acivity= LocalContext.current as Activity
    Icon(imageVector = Icons.Default.Close, contentDescription ="close app",modifier=modifier.clickable {acivity.finish()} )
}

fun enableLogin(email: String,password: String):Boolean{
    return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length>6

}