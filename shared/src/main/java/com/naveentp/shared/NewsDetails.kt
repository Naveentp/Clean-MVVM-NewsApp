package com.naveentp.shared

/**
 * @author Naveen T P
 * @since 01/06/19
 */

data class NewsDetails(
    val status: String,
    val articles: List<Article>,
    val totalResults: Int
) {
    data class Article(
        val author: String,
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String,
        val publishedAt: String,
        val source: Source,
        val content: String
    ) {
        data class Source(
            val id: String,
            val name: String
        )
    }
}