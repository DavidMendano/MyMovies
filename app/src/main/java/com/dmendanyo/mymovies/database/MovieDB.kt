package com.dmendanyo.mymovies.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dmendanyo.domain.models.Movie

@Entity
data class MovieDB(
    @PrimaryKey val id: Int,
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

fun List<Movie>.fromDomainModel(): List<MovieDB> =
    this.map { it.fromDomainModel() }

fun Movie.fromDomainModel(): MovieDB =
    MovieDB(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        urlImage = urlImage,
        backdropPath = backdropPath ?: "",
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        popularity = popularity,
        voteAverage = voteAverage,
        favorite = false,
    )

fun List<MovieDB?>.toDomainModel(): List<Movie> =
    this.mapNotNull { it?.toDomainModel() }

fun MovieDB?.toDomainModel(): Movie? =
    this?.let {
        Movie(
            id = id,
            title = title,
            overview = overview,
            releaseDate = releaseDate,
            urlImage = urlImage,
            backdropPath = backdropPath ?: "",
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            popularity = popularity,
            voteAverage = voteAverage,
            favorite = favorite,
        )
    }
