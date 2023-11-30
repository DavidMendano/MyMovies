package com.dmendanyo.mymovies.common

import com.dmendanyo.domain.models.Movie
import com.dmendanyo.mymovies.ui.common.CardItemUiModel

val sampleCardItemUIModel = CardItemUiModel(
    id = 1,
    urlImage = "",
    title = "Sample Movie",
    favorite = false,
)

val sampleMovie = Movie(
    id = 1,
    title = "Sample Movie",
    overview = "",
    releaseDate = "",
    urlImage = "",
    backdropPath = "",
    originalLanguage = "",
    originalTitle = "",
    popularity = 0.0,
    voteAverage = 0.0,
    favorite = false,
)