package com.yizhenwind.rocket.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.airbnb.deeplinkdispatch.DeepLinkDispatch
import com.airbnb.deeplinkdispatch.DeepLinkUri
import com.yizhenwind.rocket.core.framework.base.BaseActivity
import com.yizhenwind.rocket.core.framework.deeplink.RocketDeepLink
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
@RocketDeepLink(value = ["main"])
class MainActivity : BaseActivity() {

    private val binding by viewBindings<ActivityMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.test.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("rocket://home")))
        }
    }
}