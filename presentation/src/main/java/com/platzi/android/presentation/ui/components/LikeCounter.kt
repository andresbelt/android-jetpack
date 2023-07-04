package com.platzi.android.presentation.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.data.model.PicImageEntity
import com.platzi.android.presentation.ui.theme.DemoTheme
import com.platzi.presentation.R

@Composable
fun LikeCounter(
    modifier: Modifier,
    item: PicImageEntity,
    onFavClick: (String, Boolean) -> Unit
) {
    var isPlay by remember { mutableStateOf(false) }

    val compositionNoLike by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.nolike))
    val compositionLike by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.like))
    val progressNoLike by animateLottieCompositionAsState(compositionNoLike, isPlaying = isPlay)
    val progressLike by animateLottieCompositionAsState(compositionLike, isPlaying = isPlay)

    LaunchedEffect(key1 = progressNoLike, key2 = progressLike) {
        if (progressNoLike == 0f || progressLike == 0f) {
            isPlay = true
        }
        if (progressNoLike == 1f || progressLike == 1f) {
            isPlay = false
        }
    }

    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        if (item.fav) {
            LottieAnimation(
                modifier = modifier.clickable {
                    isPlay = true
                    onFavClick(item.id, false)
                },
                composition = compositionLike,
                progress = { progressLike },
            )
        } else {
            LottieAnimation(
                modifier = modifier.clickable {
                    isPlay = true
                    onFavClick(item.id, true)
                },
                composition = compositionNoLike,
                progress = { progressNoLike },
            )
        }
    }
}

@Preview("default", showBackground = true)
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun DividerPreview() {
    DemoTheme {
        LikeCounter(
            modifier = Modifier.size(60.dp),
            PicImageEntity()
        ) { _, _ ->

        }
    }
}

