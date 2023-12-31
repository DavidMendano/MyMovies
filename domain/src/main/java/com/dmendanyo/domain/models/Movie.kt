package com.dmendanyo.domain.models

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val urlImage: String,
    val backdropPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val popularity: Double,
    val voteAverage: Double,
    val favorite: Boolean,
)
