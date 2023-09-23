package com.dmendanyo.mymovies.ui.common

import com.dmendanyo.domain.models.Movie

data class CardItemUiModel(
    val id: Int,
    val urlImage: String,
    val title: String,
    val subtitle: String? = null
)

fun Movie.mapToCardItemUiModel(): CardItemUiModel =
    CardItemUiModel(
        id = id,
        urlImage = urlImage,
        title = title,
        subtitle = originalTitle,
    )
