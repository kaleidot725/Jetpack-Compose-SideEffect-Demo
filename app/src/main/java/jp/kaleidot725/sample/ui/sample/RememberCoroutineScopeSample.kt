package jp.kaleidot725.sample.ui.sample

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun RememberCoroutineScopeSample() {
    val count = rememberSaveable { mutableStateOf(0f) }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
        ) {
            Text(
                text = "%.2f".format(count.value),
                style = MaterialTheme.typography.h1,
                maxLines = 1,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .wrapContentSize()
            )

            Button(
                onClick = { increment(scope, count) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "INCREMENT")
            }

            Button(
                onClick = { decrement(scope, count) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "DECREMENT")
            }
        }
    }
}

private fun increment(scope: CoroutineScope, count: MutableState<Float>) {
    scope.launch {
        repeat(10) {
            delay(50)
            count.value = count.value + 0.1f
        }
    }
}

private fun decrement(scope: CoroutineScope, count: MutableState<Float>) {
    scope.launch {
        repeat(10) {
            delay(50)
            count.value = count.value - 0.1f
        }
    }
}