package com.yizhenwind.rocket.core.model.simple

import com.yizhenwind.rocket.core.model.Sect
import com.yizhenwind.rocket.core.model.Server
import com.yizhenwind.rocket.core.model.Zone

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/9
 */
data class SimpleCharacter(
    val id: Long = 0,
    val simpleAccount: SimpleAccount = SimpleAccount(),
    val zone: Zone = Zone(),
    val server: Server = Server(),
    val name: String = "",
    val sect: Sect = Sect()
)
