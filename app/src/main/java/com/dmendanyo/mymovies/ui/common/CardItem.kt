package com.dmendanyo.mymovies.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun CardItem(
    item: CardItemUiModel,
    onMovieClicked: (Int) -> Unit,
    onLikeClicked: (() -> Unit)? = null,
) {
    Card(
        Modifier
            .fillMaxWidth(0.5f)
            .aspectRatio(0.75f)
            .padding(8.dp)
            .clickable { onMovieClicked(item.id) },
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            ItemImage(item.urlImage)
            ItemDetails(item, onLikeClicked)
        }
    }
}

@Composable
fun ItemImage(urlImage: String) {
    AsyncImage(
        model = urlImage,
        contentDescription = "Image",
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun ItemDetails(movie: CardItemUiModel, onLikeClicked: (() -> Unit)?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.Transparent,
                        Color.Black
                    ),
                    startY = 300f
                )
            )
            .padding(bottom = 12.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (onLikeClicked != null) {
            MovieLike(movie, onLikeClicked)
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
        MovieTitle(movie)
    }
}

@Composable
private fun MovieLike(movie: CardItemUiModel, onLikeClicked: (() -> Unit)) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        val iconColor = ColorFilter.tint(if (movie.favorite) Color.Red else Color.White)
        Image(
            modifier = Modifier
                .padding(8.dp)
                .clickable { onLikeClicked() },
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favourite",
            colorFilter = iconColor,
            alignment = Alignment.Center,
        )
    }
}

@Composable
private fun MovieTitle(movie: CardItemUiModel) {
    Text(
        text = movie.title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        textAlign = TextAlign.Start,
        color = Color.White,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF, heightDp = 1000, widthDp = 500)
@Composable
private fun CardItemPreview() {
    Column {
        CardItem(fakeModel, {}, null)
        CardItem(fakeModel, {}) {}
    }
}

private val fakeModel = CardItemUiModel(
    0,
    "",
    "Spider-man",
    true
)
