package com.yizhenwind.rocket.core.model

/**
 * 角色
 *
 * @author WangZhiYao
 * @since 2022/4/22
 */
data class Character(
    val id: Long = 0,
    val client: Client = Client(),
    val zone: Zone = Zone(),
    val server: Server = Server(),
    val account: Account = Account(),
    val name: String = "",
    val sect: Sect = Sect(),
    val internal: Internal = Internal(),
    val securityLock: String = "",
    val remark: String = "",
    val enable: Boolean = true,
    val createTime: Long = System.currentTimeMillis()
)