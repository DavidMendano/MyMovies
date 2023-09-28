package com.dmendanyo.mymovies.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmendanyo.domain.models.Movie
import com.dmendanyo.domain.usecases.GetMovieDetailUseCase
import com.dmendanyo.domain.usecases.SwitchLikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val switchLikeUseCase: SwitchLikeUseCase,
) : ViewModel() {

    private val _movie = MutableStateFlow<MovieDetailUiModel?>(null)
    val movie: StateFlow<MovieDetailUiModel?> = _movie

    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            getMovieDetailUseCase.invoke(id)
                .collect {
                    if (it == null) {
                        getMovieDetailFromServer(id)
                    } else {
                        _movie.value = it.toMovieDetailUiModel()
                    }
                }
        }
    }

    private suspend fun getMovieDetailFromServer(id: Int) =
        getMovieDetailUseCase.getMovieFromServer(id).collect {
            if (it != null) {
                _movie.value = it.toMovieDetailUiModel()
            } else {
                _movie.value = MovieDetailUiModel(
                    id = 0,
                    title = "title",
                    overview = "overview",
                    releaseDate = "",
                    urlImage = "",
                    backdropPath = "",
                    originalLanguage = "",
                    originalTitle = "",
                    popularity = 0.0,
                    voteAverage = 0.0,
                    favorite = false,
                )
            }
        }

    fun switchLike(id: Int) {
        viewModelScope.launch {
            switchLikeUseCase.invoke(id)
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
        favorite = favorite,
    )

