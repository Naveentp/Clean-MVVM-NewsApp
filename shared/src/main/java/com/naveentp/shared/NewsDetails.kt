package com.naveentp.shared

/**
 * @author Naveen T P
 * @since 01/06/19
 */

data class NewsDetails(
    val articles: List<Article>
) {
    data class Article(
        val author: String?,
        val title: String?,
        val description: String?,
        val url: String?,
        val urlToImage: String?,
        val publishedAt: String?,
        val content: String?
    )
}