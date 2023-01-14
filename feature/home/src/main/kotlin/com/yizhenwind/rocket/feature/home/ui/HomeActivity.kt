package com.yizhenwind.rocket.feature.home.ui

import android.os.Bundle
import com.yizhenwind.rocket.core.framework.base.BaseActivity
import com.yizhenwind.rocket.core.framework.deeplink.RocketDeepLink
import com.yizhenwind.rocket.core.framework.ext.viewBindings
import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.feature.home.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 *
 * @author WangZhiYao
 * @since 2023/1/13
 */
@AndroidEntryPoint
@RocketDeepLink(value = ["home"])
class HomeActivity : BaseActivity() {

    private val binding by viewBindings<ActivityHomeBinding>()

    @Inject
    lateinit var logger: ILogger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        logger.d("onCreate: %s", intent.extras?.toString())
    }
}