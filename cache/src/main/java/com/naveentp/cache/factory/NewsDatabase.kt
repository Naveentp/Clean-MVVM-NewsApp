package com.naveentp.cache.factory

import androidx.room.Database
import androidx.room.RoomDatabase
import com.naveentp.cache.dao.ArticlesDao
import com.naveentp.cache.model.ArticleEntity

/**
 * @author Naveen T P
 * @since 02/06/19
 */
@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao

}