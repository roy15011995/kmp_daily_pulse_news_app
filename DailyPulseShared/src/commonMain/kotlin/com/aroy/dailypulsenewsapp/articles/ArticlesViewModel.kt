package com.aroy.dailypulsenewsapp.articles

import com.aroy.dailypulsenewsapp.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Created by amitroy on Date : 20/04/25
 */
class ArticlesViewModel: BaseViewModel() {
    private val _articlesState: MutableStateFlow<ArticlesState> = MutableStateFlow(ArticlesState(loading = true))
    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    init {
        getArticles()
    }

    fun ArticlesViewModel.getArticlesStateFlow(): Flow<ArticlesState> {
        return articlesState
    }


    private fun getArticles() {
        scope.launch {
            delay(1500)
            _articlesState.emit(ArticlesState(loading = true))
            val fetchedArticles = fetchArticles()
            _articlesState.emit(ArticlesState(articles = fetchedArticles, loading = false))
        }
    }

    private suspend fun fetchArticles() = mockArticles


    private val mockArticles = listOf(
        Article(
            title = "Stock Market Hits Record High",
            desc = "The S&P 500 index reaches an all-time high, driven by strong earnings reports from tech companies.",
            date = "2025-04-20",
            imageUrl = "https://media.istockphoto.com/id/1384646810/photo/golden-bull-and-bear-on-stock-data-chart-background-investing-stock-exchange-financial.jpg?s=1024x1024&w=is&k=20&c=NAZJNY3GUwBZFeyCG7mtbHSUrS3-P_mJuPiR8w1mCoQ="
        ),
        Article(
            title = "Global Markets Show Volatility",
            desc = "Asian markets experience fluctuations as investors react to geopolitical tensions.",
            date = "2025-04-19",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/9/91/Stocks-market.jpg"
        ),
        Article(
            title = "Indian Stock Market Resilient",
            desc = "Despite global economic challenges, Indian equity markets show signs of stability and growth.",
            date = "2025-04-18",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/9/91/Stocks-market.jpg"
        )
    )
}