package jp.kaleidot725.sample

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LaunchedEffectSampleCounter() {
    val context = LocalContext.current
    var count by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.wrapContentSize().align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LaunchedEffect(count) {
                Toast.makeText(context, "start $count", Toast.LENGTH_SHORT).show()

                delay(5000)

                Toast.makeText(context, "end $count", Toast.LENGTH_SHORT).show()
            }
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.h1,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Button(onClick = { count += 1 }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "INCREMENT")
            }
            Button(onClick = { count -= 1 }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "DECREMENT")
            }
            Button(onClick = { count = 0 }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "ZERO CLEAR")
            }
        }
    }
}