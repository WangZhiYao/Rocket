package com.yizhenwind.rocket.core.common.ext

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/20
 */
inline fun <E> Collection<E>.pickFirstOrDefault(default: E, predicate: (E) -> Boolean): E =
    firstOrNull(predicate) ?: if (size == 1) first() else default
