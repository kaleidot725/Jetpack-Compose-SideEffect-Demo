package jp.kaleidot725.sample.ui.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun RememberUpdateStateSample() {
    var unconfirmedValue by remember { mutableStateOf(Random.nextInt()) }
    var confirmedValue by remember { mutableStateOf(0) }
    var isCompleted by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
                .padding(8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Status")
                    Text(text = if (isCompleted) "Completed" else "Waiting")
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "UnconfirmedValue")
                    Text(text = unconfirmedValue.toString())
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "ConfirmedValue")
                    Text(text = confirmedValue.toString())
                }

                Button(
                    onClick = { unconfirmedValue = Random.nextInt() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Regenerate")
                }

                val currentValue = unconfirmedValue
                val onExecute = {
                    confirmedValue = currentValue
                    isCompleted = true
                }
                DelayExecutorUsingUpdatedState(onExecute = onExecute)
            }
        }
    }
}

@Composable
fun DelayExecutor(onExecute: () -> Unit) {
    // LaunchedEffect は再コンポーズしないので、古い onExecute を持ったままになる
    LaunchedEffect(true) {
        delay(10000)
        onExecute()
    }
}

@Composable
fun DelayExecutorUsingUpdatedState(onExecute: () -> Unit) {
    // 再コンポーズ時に onExecute を最新の値に更新して、LaunchedEffect で使える参照を保持する
    val currentOnExecute by rememberUpdatedState(onExecute)

    // LaunchedEffect は最コンポーズしないが、onExecute ではなく currentOnExecute の参照を持っているので、最新の onExecute を参照できる
    LaunchedEffect(true) {
        delay(10000)
        currentOnExecute()
    }
}