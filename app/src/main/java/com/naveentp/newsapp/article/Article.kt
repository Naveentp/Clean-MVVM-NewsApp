package com.naveentp.newsapp.article

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val imageUrl: String?,
    val title: String?,
    val description: String?
) : Parcelable