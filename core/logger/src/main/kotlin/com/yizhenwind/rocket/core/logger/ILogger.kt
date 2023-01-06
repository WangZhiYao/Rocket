package com.yizhenwind.rocket.core.logger

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/6
 */
interface ILogger {

    fun init(tag: String)

    fun d(message: String?, vararg args: Any?)

    fun d(t: Throwable?)

    fun d(t: Throwable?, message: String?, vararg args: Any?)

    fun e(message: String?, vararg args: Any?)

    fun e(t: Throwable?)

    fun e(t: Throwable?, message: String?, vararg args: Any?)

    fun i(message: String?, vararg args: Any?)

    fun i(t: Throwable?)

    fun i(t: Throwable?, message: String?, vararg args: Any?)

    fun v(message: String?, vararg args: Any?)

    fun v(t: Throwable?)

    fun v(t: Throwable?, message: String?, vararg args: Any?)

    fun w(message: String?, vararg args: Any?)

    fun w(t: Throwable?)

    fun w(t: Throwable?, message: String?, vararg args: Any?)

    fun wtf(message: String?, vararg args: Any?)

    fun wtf(t: Throwable?)

    fun wtf(t: Throwable?, message: String?, vararg args: Any?)
}