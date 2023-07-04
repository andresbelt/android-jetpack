package com.platzi.android.presentation.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.data.model.PicImageEntity
import com.platzi.android.presentation.ui.theme.PlatziBackgroundDark
import com.platzi.presentation.R
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ListContent(
    items: LazyPagingItems<PicImageEntity>,
    onItemClick: (String) -> Unit,
    onFavClick: (String, Boolean) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(PlatziBackgroundDark),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = items,
            key = { unsplashImage ->
                unsplashImage.id
            }
        ) { unsplashImage ->
            unsplashImage?.let { UnsplashItem(unsplashImage = it, onItemClick, onFavClick) }
        }
    }
}

@Composable
fun UnsplashItem(
    unsplashImage: PicImageEntity,
    onItemClick: (String) -> Unit,
    onFavClick: (String, Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable {
                onItemClick(unsplashImage.id)
            }
            .height(300.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        GlideImage(
            loading = {
                Box(modifier = Modifier.matchParentSize()) {
                    Image(
                        painterResource(R.drawable.ic_launcher_background),
                        contentDescription = "placeHolder",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            },
            imageModel = { unsplashImage.download_url },
            modifier = Modifier.fillMaxSize(),
            component = rememberImageComponent {
                +CircularRevealPlugin()
            },
        )
        Surface(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .alpha(ContentAlpha.medium),
            color = Color.Black
        ) {}
        Row(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Photo by ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                        append(unsplashImage.author)
                    }
                },
                color = Color.White,
                fontSize = MaterialTheme.typography.caption.fontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {

            }
            LikeCounter(
                modifier = Modifier.size(60.dp),
                unsplashImage,
                onFavClick
            )
        }
    }
}


@Composable
@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
fun UnsplashImagePreview() {
    UnsplashItem(
        unsplashImage = PicImageEntity(
            id = "1",
            author = "1",
            width = 1,
            height = 1,
            url = "",
            download_url = "",
            fav = false,
        ),
        onItemClick = {},
        onFavClick = {_ , _ -> }
    )
}
