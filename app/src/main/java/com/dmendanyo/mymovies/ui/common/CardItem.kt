package com.dmendanyo.mymovies.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dmendanyo.mymovies.R
import com.dmendanyo.mymovies.ui.theme.black

@Composable
fun CardItem(item: CardItemUiModel, onClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
            .clickable { onClicked() },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            ItemImage(item.urlImage)
            ItemTitle(item.title)
            item.subtitle
                ?.let {
                    if (it.isBlank().not())
                        ItemSubtitle(item.subtitle)
                }
        }
    }
}

@Composable
private fun ItemImage(urlImage: String) {
    AsyncImage(
        modifier = Modifier.fillMaxWidth(),
        model = urlImage,
        contentDescription = "Image",
        contentScale = ContentScale.FillWidth,
        placeholder = painterResource(id = R.drawable.ic_launcher_background),
    )
}

@Composable
private fun ItemTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(16.dp),
        textAlign = TextAlign.Center,
        color = black,
        fontSize = 15.sp,
        fontWeight = FontWeight.Black,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun ItemSubtitle(subtitle: String) {
    Text(
        text = subtitle,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 16.dp),
        textAlign = TextAlign.Center,
        color = black,
        fontSize = 12.sp,
        maxLines = 10,
        overflow = TextOverflow.Ellipsis
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF, heightDp = 1000, widthDp = 500)
@Composable
private fun MyPreview() {
    val fakeModel = CardItemUiModel(
        0,
        "",
        "Cities",
        "A description for this category"
    )
    CardItem(fakeModel) { }
}
