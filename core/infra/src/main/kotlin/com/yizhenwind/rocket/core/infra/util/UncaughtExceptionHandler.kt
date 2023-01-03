package com.yizhenwind.rocket.core.infra.util

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/2
 */
class UncaughtExceptionHandler : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(t: Thread, e: Throwable) {
        // TODO: Logging
        e.printStackTrace()
    }
}