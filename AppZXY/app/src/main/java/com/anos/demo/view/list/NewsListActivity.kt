package com.anos.demo.view.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.anos.demo.R
import com.anos.demo.databinding.ActivityNewsListBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class NewsListActivity : AppCompatActivity() {

    private val newsListViewModel: NewsListViewModel by viewModels()
    private lateinit var _binding: ActivityNewsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        newsListViewModel.lstNewsLive.observe(this) { lst ->
            _binding.btnTrigger.isEnabled = lst.isNotEmpty()
            lst.forEach {
                Log.w(TAG, ">>> ${it.id} | ${it.title}")
            }
        }

        newsListViewModel.fetchNews()

        _binding.btnTrigger.setOnClickListener {
            newsListViewModel.fetchNewsDetails("2")
        }
    }

    private companion object {
        private const val TAG = "NewsListActivity"
    }
}