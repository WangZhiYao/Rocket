package com.yizhenwind.rocket.core.model

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
data class SectInternal(
    val sect: Sect = Sect(),
    val internalList: List<Internal> = emptyList()
)