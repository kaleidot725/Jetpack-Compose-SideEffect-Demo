package jp.kaleidot725.sample.ui.sample

import android.content.Context
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun RememberCoroutineScopeSample() {
    val count = remember { mutableStateOf(0f) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

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
                onClick = { increment(context, scope, count) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "INCREMENT")
            }

            Button(
                onClick = { decrement(context, scope, count) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "DECREMENT")
            }
        }
    }
}

private fun increment(context: Context, scope: CoroutineScope, count: MutableState<Float>) {
    scope.launch {
        Toast.makeText(context, "START INCREMENT", Toast.LENGTH_SHORT).show()
        repeat(10) {
            delay(300)
            count.value = count.value + 0.1f
        }
        Toast.makeText(context, "END INCREMENT", Toast.LENGTH_SHORT).show()
    }
}

private fun decrement(context: Context, scope: CoroutineScope, count: MutableState<Float>) {
    scope.launch {
        Toast.makeText(context, "START DECREMENT", Toast.LENGTH_SHORT).show()
        repeat(10) {
            delay(300)
            count.value = count.value - 0.1f
        }
        Toast.makeText(context, "END DECREMENT", Toast.LENGTH_SHORT).show()
    }
}