package com.example.newsflash.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsflash.ui.theme.Purple80
import com.example.newsflash.ui.theme.PurpleGrey80
import com.example.newsflash.ui.theme.Typography

@Composable
fun SourceItemView(
    id: String,
    name: String,
    description: String,
    url: String,
    onClickSource: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onClickSource(id)
            },
        shape = RoundedCornerShape(8.dp),
        backgroundColor = PurpleGrey80,
        elevation = 4.dp,
    ) {
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = name,
                style = Typography.h5,
            )
            Text(
                text = description,
                style = Typography.body1,
                )
            Text(
                text = url,
                style = Typography.body1.copy(color = Color.Black, fontWeight = FontWeight.Bold))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SourceItemViewPreview() {
    SourceItemView(
        id = "abc-news",
        name = "ABC News",
        description = "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
        url = "https://abcnews.go.com",
        onClickSource = {}
    )
}
