package com.yizhenwind.rocket.core.framework.initializer

import android.content.Context
import androidx.startup.Initializer
import com.yizhenwind.rocket.core.logger.ILogger
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * 日志
 *
 * @author WangZhiYao
 * @since 2022/9/16
 */
class LoggerInitializer : Initializer<Unit> {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface LoggerInterface {
        fun logger(): ILogger
    }

    override fun create(context: Context) {
        EntryPoints.get(context, LoggerInterface::class.java).logger().init(LOG_TAG)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

    companion object {

        private const val LOG_TAG = "Rocket"

    }
}