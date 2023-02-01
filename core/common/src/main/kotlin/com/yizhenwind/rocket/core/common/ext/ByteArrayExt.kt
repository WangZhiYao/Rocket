package com.yizhenwind.rocket.core.common.ext

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/29
 */
fun ByteArray.toHex(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }