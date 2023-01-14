package com.yizhenwind.rocket.core.framework.deeplink

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.yizhenwind.rocket.core.logger.ILogger
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/13
 */
@AndroidEntryPoint
class DeepLinkReceiver : BroadcastReceiver() {

    @Inject
    lateinit var logger: ILogger

    override fun onReceive(context: Context?, intent: Intent?) {
        logger.d("onDeepLinkReceive: %s", intent?.extras?.toString())
    }
}