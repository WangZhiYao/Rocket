package com.yizhenwind.rocket.core.model

/**
 *
 * @author WangZhiYao
 * @since 2023/1/20
 */
data class CharacterProfile(
    val id: Long = 0,
    val zone: Zone = Zone(),
    val server: Server = Server(),
    val name: String = "",
    val sect: Sect = Sect(),
    val remark: String = "",
    val createTime: Long = System.currentTimeMillis()
)