package com.naveentp.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Naveen T P
 * @since 02/06/19
 */
@Entity(tableName = "Articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)