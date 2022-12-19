package com.yizhenwind.rocket.core.common.route

import android.net.Uri

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/11
 */
class RoutePathBuilder {

    private lateinit var _module: RouteModule
    private lateinit var _action: RouteAction

    fun module(module: RouteModule) {
        this._module = module
    }

    fun action(action: RouteAction) {
        this._action = action
    }

    fun build(): String {
        if (!this::_module.isInitialized) {
            throw IllegalArgumentException("Failed to build - module is undefined.")
        }
        return StringBuilder(SCHEME)
            .append(PACKAGE)
            .append(PATH_DELIMITER)
            .append(MODULE_PREFIX)
            .append(PATH_DELIMITER)
            .append(_module.value)
            .apply {
                if (::_action.isInitialized) {
                    append(PATH_DELIMITER)
                        .append(_action.value)
                }
            }
            .toString()
    }

    fun buildUri(): Uri = Uri.parse(build())

    companion object {

        private const val SCHEME = "rocket://"

        private const val PATH_DELIMITER = "/"

        private const val PACKAGE = "com.yizhenwind.rocket"

        private const val MODULE_PREFIX = "feature"
    }
}

fun route(initializer: RoutePathBuilder.() -> Unit): Uri {
    return RoutePathBuilder().apply(initializer).buildUri()
}