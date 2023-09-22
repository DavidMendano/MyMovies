package com.dmendanyo.mymovies.ui.common

import com.dmendanyo.domain.models.MovieApiModel

data class CardItemUiModel(
    val id: Int,
    val urlImage: String,
    val title: String,
    val subtitle: String? = null
)

fun MovieApiModel.mapToCardItemUiModel(): CardItemUiModel =
    CardItemUiModel(
        id = id,
        urlImage = urlImage,
        title = title,
        subtitle = originalTitle,
    )
