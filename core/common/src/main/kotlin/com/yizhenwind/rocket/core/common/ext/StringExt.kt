package com.yizhenwind.rocket.core.common.ext

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/29
 */
fun String.hexStringToByteArray(): ByteArray =
    chunked(2).map { it.uppercase().toInt(16).toByte() }.toByteArray()
