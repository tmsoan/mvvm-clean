package com.anos.demo.view.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.anos.demo.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsListActivity : AppCompatActivity() {

    private val newsListViewModel: NewsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        findViewById<Button>(R.id.btn_trigger)?.setOnClickListener {
            newsListViewModel.fetchNews()

            lifecycleScope.launch {
                delay(1500)
                newsListViewModel.fetchNewsDetails("2")
            }
        }
    }
}