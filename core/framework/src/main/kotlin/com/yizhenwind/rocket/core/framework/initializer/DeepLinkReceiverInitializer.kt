package com.yizhenwind.rocket.core.framework.initializer

import android.content.Context
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.startup.Initializer
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import com.yizhenwind.rocket.core.framework.deeplink.DeepLinkReceiver

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/13
 */
class DeepLinkReceiverInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        LocalBroadcastManager.getInstance(context)
            .registerReceiver(DeepLinkReceiver(), IntentFilter(DeepLinkHandler.ACTION))
    }

    override fun dependencies(): List<Class<out Initializer<*>>> =
        listOf(LoggerInitializer::class.java, ThreadInitializer::class.java)
}