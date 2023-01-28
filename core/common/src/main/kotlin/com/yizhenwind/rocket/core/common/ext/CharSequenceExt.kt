package com.yizhenwind.rocket.core.common.ext

/**
 *
 * @author WangZhiYao
 * @since 2023/1/24
 */
inline fun <T> String.map(transform: (String) -> T?): T? = transform(this)