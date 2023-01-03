package com.yizhenwind.rocket.core.infra.initializer

import android.content.Context
import androidx.startup.Initializer
import com.yizhenwind.rocket.core.infra.util.UncaughtExceptionHandler

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/2
 */
class ThreadInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Thread.setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler())
    }

    override fun dependencies(): List<Class<out Initializer<*>>> =
        listOf(LoggerInitializer::class.java)

}