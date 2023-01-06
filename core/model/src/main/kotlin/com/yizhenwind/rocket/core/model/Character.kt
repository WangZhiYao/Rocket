package com.yizhenwind.rocket.core.model

/**
 * 角色
 *
 * @author WangZhiYao
 * @since 2022/4/22
 */
data class Character(
    val id: Long = 0,
    val clientId: Long = 0,
    val zone: Zone = Zone(),
    val server: Server = Server(),
    val account: String = "",
    var password: String = "",
    var securityLock: String? = null,
    val name: String = "",
    val sect: Sect = Sect(),
    val internal: Internal = Internal(),
    var remark: String? = null,
    val createTime: Long = 0
)