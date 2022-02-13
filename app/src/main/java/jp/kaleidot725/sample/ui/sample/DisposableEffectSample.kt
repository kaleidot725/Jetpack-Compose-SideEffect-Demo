package jp.kaleidot725.sample.ui.sample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun DisposableEffectSample() {
    var enable by remember { mutableStateOf(true) }
    var lifecycleState by remember { mutableStateOf("") }
    val lifecycleOwner = LocalLifecycleOwner.current

    if (enable) {
        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event -> lifecycleState = event.name }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
                lifecycleState = "NO OBSERVER"
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
        ) {
            Text(
                text = lifecycleState,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = { enable = !enable },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = if (enable) "DISABLE" else "ENABLE")
            }
        }
    }
}