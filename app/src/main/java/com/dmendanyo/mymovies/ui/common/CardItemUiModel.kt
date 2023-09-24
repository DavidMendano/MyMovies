package com.dmendanyo.mymovies.ui.common

import com.dmendanyo.domain.models.Movie

data class CardItemUiModel(
    val id: Int,
    val urlImage: String,
    val title: String,
    val favorite: Boolean = false,
)

fun Movie.toCardItemUiModel(): CardItemUiModel =
    CardItemUiModel(
        id = id,
        urlImage = urlImage,
        title = title,
        favorite = favorite,
    )
