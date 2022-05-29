package jp.kaleidot725.sample.ui.sample

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.*

private val SAMPLES = listOf(
    News("ONE", "ONE ONE ONE", News.Type.BUSINESS),
    News("TWO", "TWO TWO TWO", News.Type.BUSINESS),
    News("THREE", "THREE THREE THREE", News.Type.SPORTS),
    News("FOUR", "FOUR FOUR FOUR", News.Type.HEALTH),
    News("FIVE", "FIVE FIVE FIVE", News.Type.HEALTH),
)

private data class News(val title: String, val content: String, val type: Type) {
    enum class Type {
        BUSINESS, SPORTS, HEALTH
    }
}

@Composable
fun DerivedStateOfSample() {
    val newsList by remember { mutableStateOf(SAMPLES) }
    var filterType by remember { mutableStateOf(News.Type.BUSINESS) }
    var refreshTime by remember { mutableStateOf(Date().time) }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        FilteredNewsList(refreshTime, newsList, filterType)
        FilterMenus(
            onFilterBusiness = { filterType = News.Type.BUSINESS },
            onFilterSports = { filterType = News.Type.SPORTS },
            onFilterHealth = { filterType = News.Type.HEALTH },
            onRefresh = { refreshTime = Date().time }
        )
    }
}

@Composable
private fun BoxScope.FilteredNewsList(refreshTime: Long, newsList: List<News>, type: News.Type) {
    val context = LocalContext.current
    val filteredList by remember(newsList, type) {
        derivedStateOf {
            Toast.makeText(context, "calculation filtered news list", Toast.LENGTH_SHORT).show()
            newsList.filter { it.type == type }
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            Text(text = "REFRESH TIME $refreshTime")
        }

        items(filteredList) { news ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)) {
                Column {
                    Text(text = news.type.toString())
                    Text(text = news.title)
                    Text(text = news.content)
                }
            }
        }
    }
}

@Composable
private fun BoxScope.FilterMenus(
    onFilterBusiness: () -> Unit,
    onFilterSports: () -> Unit,
    onFilterHealth: () -> Unit,
    onRefresh: () -> Unit
) {
    Column(
        modifier = Modifier.align(Alignment.BottomEnd),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = { onFilterBusiness() }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "BUSINESS")
        }

        Button(
            onClick = { onFilterSports() }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "SPORTS")
        }

        Button(
            onClick = { onFilterHealth() }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "HEALTH")
        }

        Button(
            onClick = { onRefresh() }, modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "REFRESH")
        }
    }
}