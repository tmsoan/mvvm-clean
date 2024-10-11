package com.anos.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anos.model.Article
import com.anos.model.Feed
import com.anos.ui.NewsItemImage
import com.anos.ui.p_1
import com.anos.ui.p_2
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    onMenuClick: (() -> Unit)? = null,
    onSearchClick: (() -> Unit)? = null,
    onProfileClick: (() -> Unit)? = null,
    onItemClick: ((Article) -> Unit)? = null,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState: HomeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        homeViewModel.getRssChannels()
    }
    HomeScreen(
        onMenuClick = onMenuClick,
        onSearchClick = onSearchClick,
        onProfileClick = onProfileClick,
        onItemClick = onItemClick,
        onFetchFeedRequest = { channel ->
            homeViewModel.getFeedByChannel(channel)
        },
        uiState = uiState,
    )
}

@Composable
fun HomeScreen(
    onMenuClick: (() -> Unit)?,
    onSearchClick: (() -> Unit)?,
    onProfileClick: (() -> Unit)?,
    onItemClick: ((Article) -> Unit)?,
    onFetchFeedRequest: ((String) -> Unit)?,
    uiState: HomeUiState,
) {
    val coroutineScope = rememberCoroutineScope()
    val tabTitles = uiState.channels.map { it.value }
    val pagerState = rememberPagerState(pageCount = { tabTitles.size })
    if (pagerState.pageCount == 0) {
        // TODO Loading
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("Loading...")
        }
        return
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            IconButton(
                modifier = Modifier.size(40.dp),
                onClick = { coroutineScope.launch { onMenuClick?.invoke() } },
            ) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
            ScrollableTabRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                selectedTabIndex = pagerState.currentPage,
                contentColor = Color.Black,
                edgePadding = 0.dp,
                divider = { },
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        modifier = Modifier.height(40.dp),
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Text(
                                text = title,
                                modifier = Modifier.padding(vertical = 8.dp),
                            )
                        }
                    )
                }
            }
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    modifier = Modifier.size(40.dp),
                    onClick = { coroutineScope.launch { onSearchClick?.invoke() } },
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
                IconButton(
                    modifier = Modifier.size(40.dp),
                    onClick = { coroutineScope.launch { onProfileClick?.invoke() } },
                ) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = true,
        ) { index ->
            val channel = uiState.channels.keys.elementAtOrNull(index) ?: ""
            HomeViewPager(
                channel = channel,
                feed = uiState.feedMap[channel],
                selectedTabIndex = index,
                onItemClick = onItemClick,
                onFetchFeedRequest = onFetchFeedRequest,
            )
        }
    }
}

@Composable
fun HomeViewPager(
    channel: String,
    feed: Feed?,
    selectedTabIndex: Int,
    onItemClick: ((Article) -> Unit)?,
    onFetchFeedRequest: ((String) -> Unit)?,
) {
    LaunchedEffect(selectedTabIndex) {
        // Do something when selectedTabIndex changes
        Log.w("HomeViewPager", "Selected $channel: $selectedTabIndex")
        // Fetch feed by channel
        onFetchFeedRequest?.invoke(channel)
    }
    if (feed == null || feed.articles.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = feed?.channelTitle.orEmpty())
        }
        return
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        items(
            count = feed.articles.size,
            key = { it }
        ) { index ->
            NewsItemList(
                index = index,
                article = feed.articles[index],
                onItemClick = onItemClick,
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = p_2),
                color = Color.Gray.copy(alpha = 0.5f),
                thickness = 0.5.dp
            )
        }
    }
}

@Composable
fun NewsItemList(
    index: Int,
    article: Article,
    onItemClick: ((Article) -> Unit)?
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .height(intrinsicSize = IntrinsicSize.Max)
            .clickable { onItemClick?.invoke(article) },
        verticalAlignment = Alignment.Top
    ) {
        NewsItemImage(
            imageUrl = article.enclosure?.url,
            modifier = Modifier
                .weight(.35f)
                .aspectRatio(1.3f)
                .padding(start = p_2, top = p_2, bottom = p_2)
                .clip(MaterialTheme.shapes.extraSmall)
                .background(color = Color.Gray.copy(alpha = 0.25f)),
        )
        Column(
            modifier = Modifier
                .weight(.65f)
                .padding(p_2)
                .fillMaxHeight()
        ) {
            Text(
                text = "${article.title}",
                maxLines = 3,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal),
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = p_1)
                        .size(10.dp),
                )
                Text(
                    text = article.formatPubDate(),
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal),
                    maxLines = 1
                )
            }
        }
    }
}
