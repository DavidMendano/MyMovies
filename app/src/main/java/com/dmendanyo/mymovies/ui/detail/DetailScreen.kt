package com.dmendanyo.mymovies.ui.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.dmendanyo.mymovies.R
import com.dmendanyo.mymovies.ui.common.Title

@Composable
fun DetailScreen(
    id: Int,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val movie by viewModel.movie.collectAsState()
    val isFromServer by viewModel.isFromServer.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getMovieDetail(id)
    }


    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Box {
            Image(movie?.urlImage ?: "")
            if (isFromServer.not()) {
                MovieLike(isLiked = movie?.favorite ?: false) {
                    viewModel.switchLike(movie?.id ?: 0)
                }
            }
        }
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Title(Modifier, "${stringResource(id = R.string.title)}: ${movie?.title}")
            Average(movie?.voteAverage ?: 0.0)
            ReleaseDate(movie?.releaseDate ?: "")
            OriginalLanguage(movie?.originalLanguage ?: "")
            Overview(movie?.overview ?: "")
        }
    }
}

@Composable
private fun MovieLike(isLiked: Boolean, onLikeClicked: () -> Unit) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        val iconColor = ColorFilter.tint(if (isLiked) Color.Red else Color.White)
        androidx.compose.foundation.Image(
            modifier = Modifier
                .padding(8.dp)
                .clickable(onClick = onLikeClicked),
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favourite",
            colorFilter = iconColor,
            alignment = Alignment.Center,
        )
    }
}


@Composable
private fun Image(urlImage: String) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f),
        model = urlImage,
        contentDescription = "Image",
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun Average(average: Double) {
    Text(
        text = buildBoldAndRegularText(
            bold = "${stringResource(id = R.string.average)} :",
            regular = average.toString(),
        )
    )
}

@Composable
private fun ReleaseDate(releaseDate: String) {
    Text(
        text = buildBoldAndRegularText(
            bold = "${stringResource(id = R.string.release_date)} :",
            regular = releaseDate,
        )
    )
}

@Composable
private fun OriginalLanguage(originalLanguage: String) {
    Text(
        text = buildBoldAndRegularText(
            bold = "${stringResource(id = R.string.original_language)} :",
            regular = originalLanguage,
        )
    )
}

@Composable
private fun Overview(overview: String) {
    Text(
        text = buildBoldAndRegularText(
            bold = "${stringResource(id = R.string.overview)} :",
            regular = overview,
        )
    )
}

private fun buildBoldAndRegularText(bold: String, regular: String): AnnotatedString =
    buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)) {
            append(bold)
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Medium, fontSize = 16.sp)) {
            append(regular)
        }
    }
