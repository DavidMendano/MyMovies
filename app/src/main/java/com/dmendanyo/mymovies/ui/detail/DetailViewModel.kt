package com.dmendanyo.mymovies.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmendanyo.domain.models.Movie
import com.dmendanyo.domain.usecases.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
) : ViewModel() {

    private val _movie = MutableStateFlow<MovieDetailUiModel?>(null)
    val movie: StateFlow<MovieDetailUiModel?> = _movie

    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            getMovieDetailUseCase.invoke(id)
                .collect {
                    _movie.value = it.toMovieDetailUiModel()
                }
        }
    }
}

data class MovieDetailUiModel(
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


fun Movie.toMovieDetailUiModel(): MovieDetailUiModel =
    MovieDetailUiModel(
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

