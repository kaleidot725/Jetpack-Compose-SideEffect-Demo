package jp.kaleidot725.sample.ui.sample

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collect

@Composable
fun SnapshotFlowSample() {
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow {
            Log.v("TEST", "Enter")
            listState.firstVisibleItemIndex
        }.collect {
            Log.v("TEST", "Collect FirstVisibleItemIndex $it")
        }
    }

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        repeat(10) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(100.dp)
                ) {
                    Text(text = it.toString())
                }
            }
        }
    }
}