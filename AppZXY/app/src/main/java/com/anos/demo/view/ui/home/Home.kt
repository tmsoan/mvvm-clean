package com.anos.demo.view.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.viewpager2.widget.ViewPager2


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val tabTitles = listOf("Tab 1", "Tab 2", "Tab 3", "Tab 4", "Tab 5", "Tab 6", "Tab 7", "Tab 8")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            ScrollableTabRow(
                modifier = Modifier.fillMaxWidth().weight(1f),
                selectedTabIndex = selectedTabIndex,
                contentColor = Color.Black,
                edgePadding = 0.dp,
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        modifier = Modifier
                            .height(48.dp)
                            .width(74.dp),
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index }
                    ) {
                        Text(
                            text = title,
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = Color.Black
                        )
                    }
                }
            }
            IconButton(
                modifier = Modifier.size(48.dp),
                onClick = { /*TODO*/ }
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        }
        HomeViewPager(selectedTabIndex = selectedTabIndex)
    }
}

@Composable
fun HomeViewPager(selectedTabIndex: Int) {
    LaunchedEffect(selectedTabIndex) {
        // Do something when selectedTabIndex changes
        Log.w("HomeViewPager", "Selected Tab Index: $selectedTabIndex")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Text(
            text = "Selected Tab Index: $selectedTabIndex",
            modifier = Modifier.padding(16.dp),
            color = Color.White
        )
    }

}