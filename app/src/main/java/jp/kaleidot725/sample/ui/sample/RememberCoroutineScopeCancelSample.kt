package jp.kaleidot725.sample.ui.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RememberCoroutineScopeCancelSample() {
    var enable by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier.wrapContentSize(), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = { enable = !enable }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (enable) "DISABLE COUNTER" else "ENABLED COUNTER")
        }

        if (enable) RememberCoroutineScopeLaunchSample()
    }
}