package com.yizhenwind.rocket.core.logger.impl.timber

import com.yizhenwind.rocket.core.logger.ILogger
import com.yizhenwind.rocket.core.logger.impl.timber.tree.RocketDebugTree
import timber.log.Timber

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/20
 */
class TimberLogger : ILogger {

    override fun init(tag: String) {
        Timber.plant(RocketDebugTree(tag))
    }

    override fun d(message: String?, vararg args: Any?) {
        Timber.d(message, *args)
    }

    override fun d(t: Throwable?) {
        Timber.d(t)
    }

    override fun d(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.d(t, message, *args)
    }

    override fun e(message: String?, vararg args: Any?) {
        Timber.e(message, *args)
    }

    override fun e(t: Throwable?) {
        Timber.e(t)
    }

    override fun e(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.e(t, message, *args)
    }

    override fun i(message: String?, vararg args: Any?) {
        Timber.i(message, *args)
    }

    override fun i(t: Throwable?) {
        Timber.i(t)
    }

    override fun i(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.i(t, message, *args)
    }

    override fun v(message: String?, vararg args: Any?) {
        Timber.v(message, *args)
    }

    override fun v(t: Throwable?) {
        Timber.v(t)
    }

    override fun v(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.v(t, message, *args)
    }

    override fun w(message: String?, vararg args: Any?) {
        Timber.w(message, *args)
    }

    override fun w(t: Throwable?) {
        Timber.w(t)
    }

    override fun w(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.w(t, message, *args)
    }

    override fun wtf(message: String?, vararg args: Any?) {
        Timber.wtf(message, *args)
    }

    override fun wtf(t: Throwable?) {
        Timber.wtf(t)
    }

    override fun wtf(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.wtf(t, message, *args)
    }
}