package com.yizhenwind.rocket.core.infra.initializer

import android.content.Context
import androidx.startup.Initializer
import com.yizhenwind.rocket.core.infra.logger.tree.RocketDebugTree
import timber.log.Timber

/**
 * 日志
 *
 * @author WangZhiYao
 * @since 2022/9/16
 */
class LoggerInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Timber.plant(RocketDebugTree())
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

}