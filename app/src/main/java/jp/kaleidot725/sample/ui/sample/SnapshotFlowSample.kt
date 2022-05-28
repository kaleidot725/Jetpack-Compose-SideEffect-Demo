package jp.kaleidot725.sample.ui.sample

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collect

@Composable
fun SnapshotFlowSample() {
    val lazyListState = rememberLazyListState()
    val context = LocalContext.current

    LaunchedEffect(lazyListState) {
        val firstVisibleItemIndexFlow = snapshotFlow { lazyListState.firstVisibleItemIndex }
        firstVisibleItemIndexFlow.collect { firstVisibleItemIndex ->
            Toast.makeText(context, "Update $firstVisibleItemIndex", Toast.LENGTH_SHORT).show()
        }
    }

    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        repeat(10) { number ->
            item {
                Card(modifier = Modifier.fillMaxSize().height(100.dp)) {
                    Text(text = "$number")
                }
            }
        }
    }
}