package com.example.newsflash.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsflash.R
import com.example.newsflash.ui.theme.Typography

@Composable
fun CategoryItemView(
    key: String,
    onClickCategory: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .clickable {
                onClickCategory()
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier.size(150.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val iconId = when (key) {
                "Business" -> R.drawable.ic_category_business
                "Entertainment" -> R.drawable.ic_category_entertainment
                "General" -> R.drawable.ic_category_general
                "Health" -> R.drawable.ic_category_health
                "Science" -> R.drawable.ic_category_science
                "Sports" -> R.drawable.ic_category_sports
                "Technology" -> R.drawable.ic_category_technology
                else -> R.drawable.ic_category_all
            }
            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(id = iconId),
                contentDescription = "",
            )
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = key,
                style = Typography.h6.copy(fontWeight = FontWeight.ExtraBold, color = Color.Black),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryItemViewPreview() {
    CategoryItemView(
        key = "Business",
        onClickCategory = {},
    )
}
