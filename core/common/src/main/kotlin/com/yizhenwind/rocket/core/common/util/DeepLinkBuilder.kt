package com.yizhenwind.rocket.core.common.util

import android.net.Uri

/**
 *
 * @author WangZhiYao
 * @since 2023/2/23
 */
class DeepLinkBuilder {

    private var module: String = ""
    private var path: String = ""
    private val argumentsMap = HashMap<String, String>()

    fun module(module: String) {
        this.module = module
    }

    fun path(path: String) {
        this.path = path
    }

    fun arguments(key: String, value: String) {
        argumentsMap[key] = value
    }

    fun build(): Uri {
        if (module.isBlank()) {
            throw IllegalArgumentException("module can not be empty.")
        }
        if (path.isBlank()) {
            throw IllegalArgumentException("path can not be empty.")
        }
        return Uri.Builder()
            .scheme(SCHEMA)
            .authority(module)
            .appendPath(path)
            .apply {
                argumentsMap.forEach { (key, value) ->
                    appendQueryParameter(key, value)
                }
            }
            .build()
    }

    companion object {

        private const val SCHEMA = "rocket"

    }
}

fun deepLink(initializer: DeepLinkBuilder.() -> Unit): Uri =
    DeepLinkBuilder().apply(initializer).build()