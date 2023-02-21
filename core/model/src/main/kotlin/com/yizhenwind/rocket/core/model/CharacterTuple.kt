package com.yizhenwind.rocket.core.model

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
data class CharacterTuple(
    val id: Long = 0,
    val accountTuple: AccountTuple = AccountTuple(),
    val zone: Zone = Zone(),
    val server: Server = Server(),
    val name: String = "",
    val sect: Sect = Sect()
)
