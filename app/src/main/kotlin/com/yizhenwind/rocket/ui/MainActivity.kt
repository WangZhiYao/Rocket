package com.yizhenwind.rocket.ui

import android.os.Bundle
import com.yizhenwind.rocket.core.framework.base.BaseActivity
import com.yizhenwind.rocket.core.framework.ext.viewBindings
import com.yizhenwind.rocket.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 主界面
 *
 * @author WangZhiYao
 * @since 2021/10/26
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding by viewBindings<ActivityMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}