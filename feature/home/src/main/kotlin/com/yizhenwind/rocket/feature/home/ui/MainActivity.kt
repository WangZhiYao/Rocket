package com.yizhenwind.rocket.feature.home.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yizhenwind.rocket.core.framework.ext.viewBindings
import com.yizhenwind.rocket.feature.home.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf

class MainActivity : AppCompatActivity() {

    private val binding by viewBindings<ActivityMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}