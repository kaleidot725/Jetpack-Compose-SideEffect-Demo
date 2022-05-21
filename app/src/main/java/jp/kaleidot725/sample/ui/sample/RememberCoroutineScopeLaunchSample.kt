package jp.kaleidot725.sample.ui.sample

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RememberCoroutineScopeLaunchSample() {
    var enable by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier.wrapContentSize(), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = { enable = !enable }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (enable) "DISABLE COUNTER" else "ENABLED COUNTER")
        }

        if (enable) Counter()
    }
}

@Composable
private fun Counter() {
    var count by remember { mutableStateOf(0f) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "%.2f".format(count),
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
        Button(
            onClick = {
                scope.launch {
                    Toast.makeText(context, "START INCREMENT", Toast.LENGTH_SHORT).show()
                    repeat(10) {
                        delay(300)
                        count += 0.1f
                    }
                    Toast.makeText(context, "END INCREMENT", Toast.LENGTH_SHORT).show()
                }
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "INCREMENT")
        }
    }
}