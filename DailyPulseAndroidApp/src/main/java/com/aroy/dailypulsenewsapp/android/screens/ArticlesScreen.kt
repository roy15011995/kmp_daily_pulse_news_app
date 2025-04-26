package com.aroy.dailypulsenewsapp.android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import com.aroy.dailypulsenewsapp.articles.Article
import com.aroy.dailypulsenewsapp.articles.ArticlesViewModel

/**
 * Created by amitroy on Date : 20/04/25
 */

@Composable
fun ArticlesScreen(
    articlesViewModel: ArticlesViewModel
) {
    val articlesState = articlesViewModel.articlesState.collectAsState()

    Column {
        AppBar()
        if (articlesState.value.loading) {
            Loader()
        }
        if(articlesState.value.error !=null) {
            ErrorMessage(articlesState.value.error ?: "Something went wrong")
        }
        if (articlesState.value.articles.isNotEmpty()) {
            ArticleListView(articlesState.value.articles)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() {
    TopAppBar(title = { Text(text = "Articles") })
}

@Composable
private fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
        )
    }
}

@Composable
private fun ArticleListView(articles: List<Article>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(articles) { article ->
            ArticleItemView(article)
        }
    }
}

@Composable
private fun ArticleItemView(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier.size(width = 400.dp, height = 200.dp),
            model = article.imageUrl,
            loading = {
                CircularProgressIndicator(
                    color = Color.Red,
                    modifier = Modifier.size(48.dp)
                )
            },
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.desc)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}