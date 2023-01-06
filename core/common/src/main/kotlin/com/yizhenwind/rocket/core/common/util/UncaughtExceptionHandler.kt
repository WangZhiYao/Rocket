package com.yizhenwind.rocket.core.common.util

import com.yizhenwind.rocket.core.logger.ILogger

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/2
 */
class UncaughtExceptionHandler constructor(
    private val logger: ILogger
) : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(t: Thread, e: Throwable) {
        logger.e(e)
    }
}