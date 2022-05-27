package jp.kaleidot725.sample.ui.sample

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun SideEffectSample() {
    var refreshTime by remember { mutableStateOf(Date().time) }
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        RefreshTimeText(timeText = refreshTime.toString())
        Button(
            onClick = { refreshTime = Date().time },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "REFRESH")
        }
    }
}

@Composable
fun RefreshTimeText(timeText: String) {
    val context = LocalContext.current

    SideEffect {
        Toast.makeText(context, "RefreshTime $timeText", Toast.LENGTH_SHORT).show()
    }

    Column {
        Text(text = "REFRESH TIME", style = MaterialTheme.typography.h5)
        Text(text = timeText, style = MaterialTheme.typography.h5)
    }
}

