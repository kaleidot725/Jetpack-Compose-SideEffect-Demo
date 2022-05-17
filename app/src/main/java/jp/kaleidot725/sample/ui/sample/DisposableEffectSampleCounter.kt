package jp.kaleidot725.sample.ui.sample

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.delay

@Composable
fun DisposableEffectSampleCounter() {
    val context = LocalContext.current
    var count by remember { mutableStateOf(0) }

    val lifecycleOwner =  LocalLifecycleOwner.current
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.wrapContentSize().align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DisposableEffect(count) {
                // Androidライフサイクルを購読するためObserverを生成して追加する
                Toast.makeText(context, "start effect func", Toast.LENGTH_SHORT).show()
                val observer = LifecycleEventObserver { _, event -> print("$event") }
                lifecycleOwner.lifecycle.addObserver(observer)

                // Androidライフサイクルを購読しているObserverの登録を削除するDisposableEffectResultを生成する
                val disposableEffectResult = onDispose {
                    Toast.makeText(context, "start dispose func", Toast.LENGTH_SHORT).show()
                    lifecycleOwner.lifecycle.removeObserver(observer)
                }

                // 生成したDisposableEffectResultを戻り値として、コンポジションから退場するときにDispose処理が呼び出されるようにする
                disposableEffectResult
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