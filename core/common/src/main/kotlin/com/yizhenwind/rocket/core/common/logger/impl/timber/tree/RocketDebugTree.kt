package com.yizhenwind.rocket.core.common.logger.impl.timber.tree

import com.yizhenwind.rocket.core.common.BuildConfig
import timber.log.Timber

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/2
 */
class RocketDebugTree: Timber.DebugTree() {

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return BuildConfig.DEBUG
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, LOG_TAG, message, t)
    }

    companion object {

        private const val LOG_TAG = "Rocket"

    }

}