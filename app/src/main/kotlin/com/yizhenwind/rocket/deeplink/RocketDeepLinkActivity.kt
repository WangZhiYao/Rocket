package com.yizhenwind.rocket.deeplink

import android.os.Bundle
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import com.yizhenwind.rocket.core.framework.base.BaseActivity
import com.yizhenwind.rocket.deeplink.module.MainDeepLinkModule
import com.yizhenwind.rocket.deeplink.module.MainDeepLinkModuleRegistry
import com.yizhenwind.rocket.feature.home.deeplink.HomeDeepLinkModule
import com.yizhenwind.rocket.feature.home.deeplink.HomeDeepLinkModuleRegistry

/**
 *
 * @author WangZhiYao
 * @since 2023/1/13
 */
@DeepLinkHandler(value = [MainDeepLinkModule::class, HomeDeepLinkModule::class])
class RocketDeepLinkActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val deepLinkDelegate = DeepLinkDelegate(
            MainDeepLinkModuleRegistry(),
            HomeDeepLinkModuleRegistry()
        )
        deepLinkDelegate.dispatchFrom(this@RocketDeepLinkActivity)
        finish()
    }
}