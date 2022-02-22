package jp.kaleidot725.sample.ui.sample

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

object Logger {
    var user: String = "UNKNOWN"

    fun write(message: String) {
        Log.v("TEST", "$message $user")
    }
}

@Composable
fun rememberLogger(userName: String): Logger {
    val logger: Logger = remember { Logger }
    SideEffect {
        logger.user = userName
    }
    return logger
}

private val Users = listOf(
    "ONE",
    "TWO",
    "THREE",
    "FOUR"
)

@Composable
fun SideEffectSample() {
    var user by remember { mutableStateOf(Users.random()) }
    val logger = rememberLogger(userName = user)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Side Effect Sample")
            Text(text = "User $user")
            Button(onClick = {
                logger.write("Change User")
                user = Users.random()
            }) {
                Text(text = "Change User")
            }
            Button(onClick = {
                logger.write("Execute Action")
            }) {
                Text(text = "Execute Action")
            }
        }
    }
}

