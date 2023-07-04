package com.platzi.android.presentation.ui.screens.itemdetail

import android.content.res.Configuration
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import com.example.data.model.PicImageEntity
import com.platzi.android.presentation.ui.components.ItemDivider
import com.platzi.android.presentation.ui.components.LikeCounter
import com.platzi.android.presentation.ui.components.UnsplashItem
import com.platzi.android.presentation.ui.states.collectAsStateLifecycleAware
import com.platzi.android.presentation.ui.screens.itemdetail.ItemDetailScreenViewEvent.FavClick
import com.platzi.android.presentation.ui.screens.itemdetail.ItemDetailScreenViewEvent.InitEvent
import com.platzi.android.presentation.ui.theme.DemoTheme
import com.platzi.android.presentation.ui.theme.PlatziBackgroundDark
import com.platzi.android.presentation.ui.theme.TextDetail
import com.platzi.presentation.R
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import kotlin.math.max
import kotlin.math.min

private val TitleHeight = 128.dp
private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val MinTitleOffset = 56.dp
private val MinImageOffset = 12.dp
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val ExpandedImageSize = 300.dp
private val CollapsedImageSize = 150.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@OptIn(ExperimentalPagingApi::class)
@Composable
fun ItemDetailScreen(
    picId: String,
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    val itemDetailViewModel = hiltViewModel<ItemDetailModel>()
    val viewState: ItemDetailScreenViewState by itemDetailViewModel.stateStream.collectAsStateLifecycleAware()
    itemDetailViewModel.uiEvent(InitEvent(picId))

    Box(Modifier.fillMaxSize()) {
        val scroll = rememberScrollState(0)
        Header()
        Body(viewState.picInfo, scroll)
        Title(viewState.picInfo) { scroll.value }
        ImageCustom(viewState.picInfo.download_url) { scroll.value }
        Up(navController)
        LikeCounter(
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.TopEnd),
            viewState.picInfo
        ) { id, fav ->
            itemDetailViewModel.uiEvent(FavClick(id, fav))
        }
    }
}

@Composable
private fun Header() {
    Spacer(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
    )
}

@Composable
private fun Up(navController: NavHostController) {
    IconButton(
        onClick = {
            navController.navigateUp()
        },
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            tint = TextDetail,
            contentDescription = stringResource(R.string.label_back)
        )
    }
}

@Composable
private fun Body(
    imageInfo: PicImageEntity,
    scroll: ScrollState
) {

    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(MinTitleOffset)
                .background(PlatziBackgroundDark)
        )
        Column(
            modifier = Modifier
                .verticalScroll(scroll)
                .background(PlatziBackgroundDark)
        ) {
            Spacer(Modifier.height(GradientScroll))
            Column {
                Spacer(Modifier.height(ImageOverlap))
                Spacer(Modifier.height(TitleHeight))
                Spacer(Modifier.height(16.dp))
                var seeMore by remember { mutableStateOf(true) }
                Text(
                    text = stringResource(R.string.detail_placeholder),
                    style = MaterialTheme.typography.body1,
                    color = Color.White,
                    maxLines = if (seeMore) 10 else Int.MAX_VALUE,
                    overflow = TextOverflow.Ellipsis,
                    modifier = HzPadding
                )
                val textButton = if (seeMore) {
                    stringResource(id = R.string.see_more)
                } else {
                    stringResource(id = R.string.see_less)
                }
                Text(
                    text = textButton,
                    style = MaterialTheme.typography.button,
                    color = TextDetail,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .heightIn(20.dp)
                        .fillMaxWidth()
                        .padding(top = 15.dp)
                        .clickable {
                            seeMore = !seeMore
                        }
                )
                Spacer(Modifier.height(40.dp))
                Text(
                    text = imageInfo.url,
                    style = MaterialTheme.typography.body1,
                    maxLines = if (seeMore) 10 else Int.MAX_VALUE,
                    color = TextDetail,
                    overflow = TextOverflow.Ellipsis,
                    modifier = HzPadding
                )
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun Title(snack: PicImageEntity, scrollProvider: () -> Int) {
    val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx() }
    val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx() }
    val author = snack.author.replace(" ", "\n")

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .heightIn(min = TitleHeight)
            .statusBarsPadding()
            .offset {
                val scroll = scrollProvider()
                val offset = (maxOffset - scroll).coerceAtLeast(minOffset)
                IntOffset(x = 0, y = offset.toInt())
            }
            .background(color = PlatziBackgroundDark)
    ) {
        Spacer(Modifier.height(40.dp))
        Text(
            text = author,
            style = MaterialTheme.typography.h4,
            color = Color.White,
            modifier = HzPadding
        )
        Spacer(Modifier.height(8.dp))
        ItemDivider()
    }
}

@Composable
private fun ImageCustom(
    imageUrl: String,
    scrollProvider: () -> Int
) {
    val collapseRange = with(LocalDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
    val collapseFractionProvider = {
        (scrollProvider() / collapseRange).coerceIn(0f, 1f)
    }

    CollapsingImageLayout(
        collapseFractionProvider = collapseFractionProvider,
        modifier = HzPadding.then(Modifier.statusBarsPadding())
    ) {
        GlideImage(
            loading = {
                Box(modifier = Modifier.matchParentSize()) {
                    androidx.compose.foundation.Image(
                        painterResource(R.drawable.ic_launcher_background),
                        contentDescription = "placeHolder",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            },
            imageModel = { imageUrl },
            modifier = Modifier.fillMaxSize(),
            component = rememberImageComponent {
                +CircularRevealPlugin()
            },
        )
    }
}

@Composable
private fun CollapsingImageLayout(
    collapseFractionProvider: () -> Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 1)
        val collapseFraction = collapseFractionProvider()
        val imageMaxSize = min(ExpandedImageSize.roundToPx(), constraints.maxWidth)
        val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth)
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction)
        val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))
        val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).roundToPx()
        val imageX = lerp(
            (constraints.maxWidth - imageWidth) / 2, // centered when expanded
            constraints.maxWidth - imageWidth.plus(60), // right aligned when collapsed
            collapseFraction
        )
        layout(
            width = constraints.maxWidth,
            height = imageY + imageWidth
        ) {
            imagePlaceable.placeRelative(imageX, imageY)
        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
private fun SnackDetailPreview() {
    DemoTheme {
        UnsplashItem(
            unsplashImage = PicImageEntity(
                id = "1",
                author = "1",
                width = 1,
                height = 1,
                url = "",
                download_url = "",
                fav = false,
            ), onItemClick = object : (String) -> Unit {
                override fun invoke(p1: String) {}
            }, onFavClick  = object : (String, Boolean) -> Unit {
                override fun invoke(p1: String, p2: Boolean) {}
            }
        )
    }
}
