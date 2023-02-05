package com.yizhenwind.rocket.core.model

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/4
 */
data class ZoneServer(
    val zone: Zone = Zone(),
    val serverList: List<Server> = emptyList()
)