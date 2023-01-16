package com.yizhenwind.rocket.core.model

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/16
 */
data class UserCharacter(
    val id: Long = 0,
    val zone: Zone = Zone(),
    val server: Server = Server(),
    val account: Account = Account(),
    val securityLock: String? = null,
    val name: String = "",
    val sect: Sect = Sect(),
    val internal: Internal = Internal(),
    val remark: String? = null,
    val createTime: Long = 0
)