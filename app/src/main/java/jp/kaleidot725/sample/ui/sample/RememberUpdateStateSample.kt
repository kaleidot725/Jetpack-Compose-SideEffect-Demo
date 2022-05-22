package jp.kaleidot725.sample.ui.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun RememberUpdateStateSample() {
    var refreshTime by remember { mutableStateOf(Date().time) }
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        TimeTextUsingRemember(timeText = refreshTime.toString())
        TimeTextUsingRememberUpdateState(timeText = refreshTime.toString())
        Button(
            onClick = { refreshTime = Date().time },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "REFRESH")
        }
    }
}

@Composable
fun TimeTextUsingRemember(timeText: String) {
    val value by remember { mutableStateOf(timeText) }
    Column {
        Text(text = "REMEMBER", style = MaterialTheme.typography.h5)
        Text(text = value, style = MaterialTheme.typography.h5)
    }
}

@Composable
fun TimeTextUsingRememberUpdateState(timeText: String) {
    val getValue by rememberUpdatedState { timeText }
    Column {
        Text(text = "REMEMBER UPDATE STATE", style = MaterialTheme.typography.h5)
        Text(text = getValue(), style = MaterialTheme.typography.h5)
    }
}
