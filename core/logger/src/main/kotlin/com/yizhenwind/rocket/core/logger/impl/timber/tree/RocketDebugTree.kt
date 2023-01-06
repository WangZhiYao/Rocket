package com.yizhenwind.rocket.core.logger.impl.timber.tree

import com.yizhenwind.rocket.core.logger.BuildConfig
import timber.log.Timber

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/2
 */
class RocketDebugTree(private val logTag: String) : Timber.DebugTree() {

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return BuildConfig.DEBUG
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, logTag, message, t)
    }

}