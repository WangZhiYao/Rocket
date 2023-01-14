package com.yizhenwind.rocket.core.logger.impl.timber.tree

import com.yizhenwind.rocket.core.logger.BuildConfig
import com.yizhenwind.rocket.core.logger.impl.timber.TimberLogger
import timber.log.Timber
import java.util.regex.Pattern

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/2
 */
class RocketDebugTree(private val logTag: String) : Timber.DebugTree() {

    private val fqcnIgnore = listOf(
        Timber::class.java.name,
        Timber.Forest::class.java.name,
        Timber.Tree::class.java.name,
        Timber.DebugTree::class.java.name,
        RocketDebugTree::class.java.name,
        TimberLogger::class.java.name
    )

    private val messagePrefix: String
        get() = Throwable().stackTrace
            .first {
                it.className !in fqcnIgnore
            }
            .let {
                var prefix = it.className.substringAfterLast('.')
                val m = ANONYMOUS_CLASS.matcher(prefix)
                if (m.find()) {
                    prefix = m.replaceAll("")
                }
                "$prefix | ${it.methodName}"
            }

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return BuildConfig.DEBUG
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, logTag, "$messagePrefix - $message", t)
    }

    companion object {

        private val ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$")

    }
}