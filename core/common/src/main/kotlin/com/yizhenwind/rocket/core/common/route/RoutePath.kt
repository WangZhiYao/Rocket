package com.yizhenwind.rocket.core.common.route

import android.net.Uri

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/6
 */
class RoutePath private constructor(private val builder: Builder) {

    fun toPath(): String = StringBuilder(SCHEME)
        .append(PACKAGE)
        .append(PATH_DELIMITER)
        .append(MODULE_PREFIX)
        .append(PATH_DELIMITER)
        .append(builder.module)
        .append(PATH_DELIMITER)
        .append(builder.action)
        .toString()

    data class Builder(
        var module: String = "",
        var action: String = ""
    ) {

        fun setModule(module: String) = apply { this.module = module }

        fun setAction(action: String) = apply { this.action = action }

        fun build(): Uri = Uri.parse(RoutePath(this).toPath())
    }

    class Module {

        companion object {

            const val CLIENT = "client"

            const val CONTACT = "contact"
        }
    }

    class Action {

        companion object {

            const val ADD = "add"

            const val CREATE = "create"
        }

    }

    companion object {

        private const val SCHEME = "rocket://"

        private const val PATH_DELIMITER = "/"

        private const val PACKAGE = "com.yizhenwind.rocket"

        private const val MODULE_PREFIX = "feature"

    }
}