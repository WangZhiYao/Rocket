package com.yizhenwind.rocket.core.infra.logger

import timber.log.Timber
import javax.inject.Inject

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/11/20
 */
class Logger @Inject constructor() {

    fun d(message: String?, vararg args: Any?) {
        Timber.d(message, *args)
    }

    fun d(t: Throwable?) {
        Timber.d(t)
    }

    fun d(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.d(t, message, *args)
    }

    fun e(message: String?, vararg args: Any?) {
        Timber.e(message, *args)
    }

    fun e(t: Throwable?) {
        Timber.e(t)
    }

    fun e(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.e(t, message, *args)
    }

    fun i(message: String?, vararg args: Any?) {
        Timber.i(message, *args)
    }

    fun i(t: Throwable?) {
        Timber.i(t)
    }

    fun i(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.i(t, message, *args)
    }

    fun v(message: String?, vararg args: Any?) {
        Timber.v(message, *args)
    }

    fun v(t: Throwable?) {
        Timber.v(t)
    }

    fun v(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.v(t, message, *args)
    }

    fun w(message: String?, vararg args: Any?) {
        Timber.w(message, *args)
    }

    fun w(t: Throwable?) {
        Timber.w(t)
    }

    fun w(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.w(t, message, *args)
    }

    fun wtf(message: String?, vararg args: Any?) {
        Timber.wtf(message, *args)
    }

    fun wtf(t: Throwable?) {
        Timber.wtf(t)
    }

    fun wtf(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.wtf(t, message, *args)
    }
}