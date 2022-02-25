package jp.kaleidot725.sample.ui.sample

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.sample.ui.sample.News.Companion.SAMPLES
import java.util.*

enum class FilterType {
    PINNED,
    UNPINNED,
    NONE,
}

private data class News(
    val title: String,
    val content: String,
    val pinned: Boolean
) {
    companion object {
        val SAMPLES = listOf(
            News("ONE", "ONE ONE ONE", true),
            News("TWO", "TWO TWO TWO", true),
            News("THREE", "THREE THREE THREE", false),
            News("FOUR", "FOUR FOUR FOUR", false),
            News("FIVE", "FIVE FIVE FIVE", false),
        )
    }
}

@Composable
private fun NewsCard(news: News, onChangeStatus: () -> Unit, modifier: Modifier = Modifier) {
    Card(modifier) {
        Column {
            Text(text = news.title)
            Text(text = news.content)
            Button(onClick = { onChangeStatus() }, modifier = Modifier.fillMaxWidth()) {
                Text(text = if (news.pinned) "UNPIN" else "PIN")
            }
        }
    }
}

@Preview
@Composable
private fun NewsCard_Preview() {
    NewsCard(news = SAMPLES.first(), onChangeStatus = {}, modifier = Modifier.fillMaxSize())
}

@Composable
fun DerivedStateOfSample() {
    var filterType by remember { mutableStateOf(FilterType.NONE) }
    var refreshTime by remember { mutableStateOf(Date().time) }

    Box(modifier = Modifier.fillMaxSize()) {
        NewsList(refreshTime, filterType)

        Column(modifier = Modifier.align(Alignment.BottomEnd)) {
            FloatingActionButton(
                onClick = {
                    filterType = when (filterType) {
                        FilterType.PINNED -> FilterType.UNPINNED
                        FilterType.UNPINNED -> FilterType.NONE
                        FilterType.NONE -> FilterType.PINNED
                    }
                }
            ) {
                Text(text = "Change")
            }

            FloatingActionButton(
                onClick = { refreshTime = Date().time }
            ) {
                Text(text = "Refresh")
            }
        }
    }
}

@Composable
private fun NewsList(refreshTime: Long, filterType: FilterType) {
    Log.v("TEST", "enter pinned news list")

    var newsList by remember { mutableStateOf(SAMPLES) }

//    Log.v("TEST", "calc pinned news list")
//    val filteredList = when (filterType) {
//        FilterType.PINNED -> newsList.filter { it.pinned }
//        FilterType.UNPINNED -> newsList.filter { !it.pinned }
//        FilterType.NONE -> newsList
//    }

    val filteredList by remember(filterType) {
        derivedStateOf {
            Log.v("TEST", "calc pinned news list")
            when (filterType) {
                FilterType.PINNED -> newsList.filter { it.pinned }
                FilterType.UNPINNED -> newsList.filter { !it.pinned }
                FilterType.NONE -> newsList
            }
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            Surface(color = Color.LightGray, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "${filterType.name} $refreshTime",
                    style = MaterialTheme.typography.h6
                )
            }
        }

        items(filteredList) { news ->
            NewsCard(
                news = news,
                onChangeStatus = {
                    newsList = buildList {
                        addAll(newsList)
                        val index = newsList.indexOf(news)
                        val newNews = news.copy(pinned = !news.pinned)
                        remove(news)
                        add(index, newNews)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(horizontal = 4.dp)
            )
        }
    }
}
