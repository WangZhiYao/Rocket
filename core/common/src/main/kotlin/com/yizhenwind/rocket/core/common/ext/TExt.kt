package com.yizhenwind.rocket.core.common.ext

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/6/30
 */
inline fun <T, R> T?.ifNull(defaultValue: () -> R): R where T : R = this ?: defaultValue()