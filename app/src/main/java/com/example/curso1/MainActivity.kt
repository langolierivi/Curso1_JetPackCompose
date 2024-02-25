package com.example.curso1

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.regex.Pattern

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Principal()
        }
    }
}

fun checkEmail(email: String): Boolean {
    var pat = Pattern.compile(Patterns.EMAIL_ADDRESS.toString())
    var m = pat.matcher(email)
    return m.matches()
}

fun checkUserPass(email: String, password: String):Boolean {
    if (email == "pepe@gmail.com" && password == "Pepe_25") {
        Log.i("Curso1", "Usuario aceptado")
        return true
    } else {
        Log.i("Curso1", "Usuario denegado")
        return false
    }
}

@Composable
@Preview(showSystemUi = true)
fun Principal() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var enableButtons by remember { mutableStateOf(false) }

    val context=LocalContext.current

    enableButtons = checkEmail(email)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo(modifier = Modifier.size(300.dp))
        FieldUser(
            email, { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        )
        Spacer(modifier = Modifier.size(20.dp))
        FieldPass(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, end = 10.dp), password, { password = it }
        )
        Spacer(Modifier.size(20.dp))
        ButtonsPlatform(modifier = Modifier, enableButtons, {
            Log.i("curso1","Boton entrar")
            if (checkUserPass(email,password)){
                Toast.makeText(context,"Has accedido!!",Toast.LENGTH_LONG).show()
            }

        },
            {
            Log.i("Curso1","Boton registrar")
        })


    }
}

@Composable
fun Logo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.mipmap.zgzmaker),
        modifier = modifier,
        contentDescription = "Logo"
    )
}

@Composable
fun FieldUser(
    email: String, onChangeEmail: (String) -> Unit,
    modifier: Modifier
) {
    TextField(
        value = email,
        modifier = modifier,
        onValueChange = onChangeEmail,
        placeholder = { Text(stringResource(id = R.string.email)) })
}

@Composable
fun FieldPass(modifier: Modifier, password: String, onChangePassword: (String) -> Unit) {
    TextField(
        value = password,
        modifier = modifier,
        onValueChange = onChangePassword,
        placeholder = { Text(stringResource(id = R.string.password)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation()
    )
}


@Composable
fun ButtonsPlatform(
    modifier: Modifier,
    enable: Boolean,
    onEnterButton: () -> Unit,
    onRegButtons: () -> Unit
) {
    Row {
        Button(
            onClick = onEnterButton,
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF05BF13)),
            enabled = enable
        ) {
            Text(stringResource(id = R.string.b_enter), fontSize = 20.sp)
        }
        Spacer(modifier = modifier.size(20.dp))
        Button(
            onClick = onRegButtons,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF05BF13)),
            enabled = enable
        ) {
            Text(stringResource(id = R.string.b_registrar), fontSize = 20.sp)

        }
    }
}

