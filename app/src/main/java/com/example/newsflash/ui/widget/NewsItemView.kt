package com.example.newsflash.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsflash.R
import com.example.newsflash.ui.theme.Typography

@Composable
fun NewsItemView(
    author: String,
    title: String,
    description: String,
    url: String,
    urlToImage: String,
    publishedAt: String,
    onClickOpenNews: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClickOpenNews() },
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            AsyncImage(
                modifier = Modifier.height(150.dp),
                model = urlToImage,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.img_placeholder),
                error = painterResource(id = R.drawable.img_placeholder),
                contentDescription = "",
            )
        }
        Text(
            text = title,
            style = Typography.h5,
        )
        Text(
            text = description,
            style = Typography.body1,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = author,
                style = Typography.body1.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                ),
            )
            Text(
                text = publishedAt,
                style = Typography.body1,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsItemViewPreview() {
    NewsItemView(
        author = "Jane Doe",
        title = "How the Government Overcomes Duck-Eating Goose",
        description = "The world has recently been overrun by a new type of goose that preys on ducks. " +
                "The phenomenon has caused significant damage for duck farms all over the world. " +
                "Read how different government deals with this pressing issue.",
        url = "crazynews.com/duck-eating-goose",
        urlToImage = "crazynews.com/duck-eating-goose.jpg",
        publishedAt = "22 September 2023",
        onClickOpenNews = {},
    )
}
