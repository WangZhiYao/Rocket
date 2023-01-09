package com.yizhenwind.rocket.core.common.initializer

import android.content.Context
import androidx.startup.Initializer
import com.yizhenwind.rocket.core.common.logger.ILogger
import com.yizhenwind.rocket.core.common.util.UncaughtExceptionHandler
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/2
 */
class ThreadInitializer : Initializer<Unit> {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface LoggerInterface {
        fun logger(): ILogger
    }

    override fun create(context: Context) {
        Thread.setDefaultUncaughtExceptionHandler(
            UncaughtExceptionHandler(
                EntryPoints.get(
                    context,
                    LoggerInterface::class.java
                ).logger()
            )
        )
    }

    override fun dependencies(): List<Class<out Initializer<*>>> =
        listOf(LoggerInitializer::class.java)

}