package jp.kaleidot725.sample

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import jp.kaleidot725.sample.ui.theme.SampleTheme
import kotlinx.coroutines.delay

@Composable
fun Toggle() {
    val context = LocalContext.current
    var state by remember { mutableStateOf(false) }

    if (state) {
        LaunchedEffect(Unit) {
            Toast.makeText(context, "start $state", Toast.LENGTH_SHORT).show()

            delay(5000)

            Toast.makeText(context, "end $state", Toast.LENGTH_SHORT).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
        ) {
            Text(
                text = state.toString(),
                style = MaterialTheme.typography.h1,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = { state = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "TRUE")
            }
            Button(
                onClick = { state = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "FALSE")
            }
        }
    }
}