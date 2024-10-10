package com.anos.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anos.ui.UiState
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    onMenuClick: (() -> Unit)? = null,
    onSearchClick: (() -> Unit)? = null,
    onProfileClick: (() -> Unit)? = null,
    onItemClick: ((Int) -> Unit)? = null,
) {
    HomeScreen(
        onMenuClick = onMenuClick,
        onSearchClick = onSearchClick,
        onProfileClick = onProfileClick,
        onItemClick = onItemClick,
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onMenuClick: (() -> Unit)?,
    onSearchClick: (() -> Unit)?,
    onProfileClick: (() -> Unit)?,
    onItemClick: ((Int) -> Unit)?,
) {
    val coroutineScope = rememberCoroutineScope()
    val homeViewModel: HomeViewModel = viewModel()

    val tabTitles = listOf("Nóng", "Mới", "Xe 360", "Độc & lạ", "Tình yêu", "Giải trí", "Thế giới", "Pháp luật", "Bóng đá")
    val pagerState = rememberPagerState(pageCount = { tabTitles.size })

    val uiState: UiState<List<String>> by homeViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        homeViewModel.fetchHotNews()
    }

    when (uiState) {
        is UiState.Loading -> {
            Log.e("HomeScreen", "Loading")
        }
        is UiState.Success -> {
            Log.e("HomeScreen", "Success: ${(uiState as UiState.Success<List<String>>).data}")
        }
        is UiState.Error -> {
            Log.e("HomeScreen", "Error: ${(uiState as UiState.Error).message}")
        }
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
        ) {
            HomeViewPager(selectedTabIndex = it, onItemClick = onItemClick)
        }
    }
}

@Composable
fun HomeViewPager(selectedTabIndex: Int, onItemClick: ((Int) -> Unit)?) {
    LaunchedEffect(selectedTabIndex) {
        // Do something when selectedTabIndex changes
        Log.w("HomeViewPager", "Selected Tab Index: $selectedTabIndex")
    }
    LazyColumn {
        items(
            count = 15,
            key = { it }
        ) {
            NewsItemList(
                selectedTabIndex = selectedTabIndex,
                itemIndex = it,
                onItemClick = onItemClick,
            )
        }
    }
}

@Composable
fun NewsItemList(selectedTabIndex: Int, itemIndex: Int, onItemClick: ((Int) -> Unit)?){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                if (itemIndex % 2 == 0) Color.LightGray
                else Color.White
            )
            .clickable {
                onItemClick?.invoke(itemIndex)
            }
    ) {
        Text(
            text = "Tab $selectedTabIndex, item $itemIndex",
            modifier = Modifier.padding(16.dp),
            color = Color.Black
        )
    }
}
